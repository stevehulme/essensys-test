package org.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.service.CallRaterService;

import java.nio.file.Path;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @Mock
    private CallRaterService callRaterService;

    @InjectMocks
    private Application application;

    @Test
    public void testUsesCallRaterService() throws Exception {

        String argument1 = "argument1";
        application.run(argument1);
        verify(callRaterService, times(1)).rateData(any(Path.class));
    }


}