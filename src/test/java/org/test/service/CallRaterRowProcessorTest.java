package org.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.factory.CallDestinationFactory;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CallRaterRowProcessorTest {

    @Mock
    private CallDestinationFactory callDestinationFactory;

    @InjectMocks
    private CallRaterRowProcessor callRaterRowProcessor;

    @Test
    public void testProcessRow() {

        String testString = "testString";
        callRaterRowProcessor.processRow(testString);
        verify(callDestinationFactory, times(1)).create(testString);
    }

}