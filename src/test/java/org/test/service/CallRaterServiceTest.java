package org.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.nio.file.Path;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CallRaterServiceTest {


    @InjectMocks
    private CallRaterService callRaterService;

    @Test
    public void testRateData() {
        Path path = mock(Path.class);
        callRaterService.rateData(path);

    }

}