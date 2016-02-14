package org.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.factory.CallInformationFactory;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CallRaterRowProcessorTest {

    @Mock
    private CallInformationFactory callInformationFactory;

    @InjectMocks
    private CallRaterRowProcessor callRaterRowProcessor;

    @Test
    public void testProcessRow() {

        String testString = "testString";
        callRaterRowProcessor.processRow(testString);
        verify(callInformationFactory, times(1)).create(testString);
    }

}