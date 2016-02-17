package org.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.domain.CallInformation;
import org.test.domain.CallInformationWithCost;
import org.test.domain.CustomerCLIByDay;
import org.test.factory.CallInformationFactory;
import org.test.service.CLI.CustomerCLIByDayOutputGenerator;
import org.test.service.CLI.CustomerCLIByDayProcessor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    @Mock
    private CustomerCLIByDayProcessor customerCLIByDayProcessor;

    @Mock
    private CallInformationWIthCostSerialiser callInformationWIthCostSerialiser;

    @Mock
    private CustomerCLIByDayOutputGenerator customerCLIByDayOutputGenerator;

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
        String string3 = "string3";

        Stream<String> streamOfLines = Arrays.asList(string1, string2).stream();

        CallInformation callInformation = mock(CallInformation.class);
        when(callInformationFactory.create(string1)).thenReturn(Optional.of(callInformation));
        when(callInformationFactory.create(string2)).thenReturn(Optional.empty());
        CallInformationWithCost callInformationWithCost = mock(CallInformationWithCost.class);
        when(callRaterRowProcessor.processCallInformation(callInformation)).thenReturn(callInformationWithCost);
        when(callInformationWIthCostSerialiser.toString(callInformationWithCost)).thenReturn(string1PlusCost);
        Map<String, Map<String, CustomerCLIByDay>> infoMap = mock(Map.class);
        when(customerCLIByDayProcessor.getCustomerCLIToCallInformationMap()).thenReturn(infoMap);
        when(customerCLIByDayOutputGenerator.getResults(infoMap)).thenReturn(Arrays.asList(string3));

        callRaterProcessor.processStream(streamOfLines);

        verify(filesWriterWrapper, times(3)).writeFile(output.capture(), outputPath.capture());

        assertEquals(output.getAllValues().get(0), Arrays.asList(string1PlusCost));
        assertEquals(outputPath.getAllValues().get(0).toFile().getName(), "output.csv");

        assertEquals(output.getAllValues().get(1), Arrays.asList(string2));
        assertEquals(outputPath.getAllValues().get(1).toFile().getName(), "unmatched.csv");

        assertEquals(output.getAllValues().get(2), Arrays.asList(string3));
        assertEquals(outputPath.getAllValues().get(2).toFile().getName(), "customerCLI.csv");
    }


    @Test
    public void testWriteHeader() throws IOException {
        String fileHeader = "Customer+more";
        String fileHeaderPlusCost = fileHeader + ", Cost";

        Stream<String> streamOfLines = Arrays.asList(fileHeader).stream();

        when(callInformationFactory.create(fileHeader)).thenReturn(Optional.empty());

        callRaterProcessor.processStream(streamOfLines);

        verify(filesWriterWrapper, times(3)).writeFile(output.capture(), outputPath.capture());

        assertEquals(output.getAllValues().get(0), Arrays.asList(fileHeaderPlusCost));
        assertEquals(outputPath.getAllValues().get(0).toFile().getName(), "output.csv");

        assertEquals(output.getAllValues().get(1), Arrays.asList(fileHeader));
        assertEquals(outputPath.getAllValues().get(1).toFile().getName(), "unmatched.csv");

    }


}