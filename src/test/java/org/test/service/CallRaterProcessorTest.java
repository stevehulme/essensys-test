package org.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.domain.CallInformation;
import org.test.factory.CallInformationFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CallRaterProcessorTest {

    @Mock
    private CallRaterRowProcessor callRaterRowProcessor;

    @Mock
    private FilesWriterWrapper filesWriterWrapper;

    @Mock
    private CallInformationFactory callInformationFactory;

    @Captor
    private ArgumentCaptor<List<String>> output;

    @Captor
    private ArgumentCaptor<Path> outputPath;

    @InjectMocks
    private CallRaterProcessor callRaterProcessor;


    @Test
    public void testRateData() throws IOException {
        String string1 = "string1";
        String string1PlusCost = string1 + "+cost";
        String string2 = "string2";

        Stream<String> streamOfLines = Arrays.asList(string1, string2).stream();

        CallInformation callInformation = mock(CallInformation.class);
        when(callInformationFactory.create(string1)).thenReturn(Optional.of(callInformation));
        when(callInformationFactory.create(string2)).thenReturn(Optional.empty());
        when(callRaterRowProcessor.processRow(callInformation)).thenReturn(string1PlusCost);

        callRaterProcessor.processStream(streamOfLines);

        verify(filesWriterWrapper, times(2)).writeFile(output.capture(), outputPath.capture());

        assertEquals(output.getAllValues().get(0), Arrays.asList(string1PlusCost));
        assertEquals(outputPath.getAllValues().get(0).toFile().getName(), "output.csv");

        assertEquals(output.getAllValues().get(1), Arrays.asList(string2));
        assertEquals(outputPath.getAllValues().get(1).toFile().getName(), "unmatched.csv");
    }


    @Test
    public void testWriteHeader() throws IOException {
        String fileHeader = "Customer+more";
        String fileHeaderPlusCost = fileHeader + ", Cost";

        Stream<String> streamOfLines = Arrays.asList(fileHeader).stream();

        when(callInformationFactory.create(fileHeader)).thenReturn(Optional.empty());

        callRaterProcessor.processStream(streamOfLines);

        verify(filesWriterWrapper, times(2)).writeFile(output.capture(), outputPath.capture());

        assertEquals(output.getAllValues().get(0), Arrays.asList(fileHeaderPlusCost));
        assertEquals(outputPath.getAllValues().get(0).toFile().getName(), "output.csv");

        assertEquals(output.getAllValues().get(1), Arrays.asList(fileHeader));
        assertEquals(outputPath.getAllValues().get(1).toFile().getName(), "unmatched.csv");
    }


}