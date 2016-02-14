package org.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CallRaterServiceTest {

    @Mock
    private CallRaterRowProcessor callRaterRowProcessor;

    @Mock
    private FilesWrapper filesWrapper;

    @InjectMocks
    private CallRaterService callRaterService;

    @Test
    public void testRateData() throws IOException {
        Path path = mock(Path.class);
        Stream<String> streamOfLines = Arrays.asList("string1", "string2", "string3").stream();
        when(filesWrapper.getLines(path)).thenReturn(streamOfLines);
        callRaterService.rateData(path);
        verify(callRaterRowProcessor, times(1)).processRow("string1");
        verify(callRaterRowProcessor, times(1)).processRow("string2");
        verify(callRaterRowProcessor, times(1)).processRow("string3");

    }

}