package org.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.domain.CallInformation;
import org.test.factory.CallInformationFactory;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CallRaterRowProcessorTest {

    @Mock
    private CallInformationFactory callInformationFactory;

    @Mock
    private CallCostCalculator callCostCalculator;

    @InjectMocks
    private CallRaterRowProcessor callRaterRowProcessor;

    @Test
    public void testProcessRow() {

        String testString = "testString";
        CallInformation callInformation = mock(CallInformation.class);
        when(callInformationFactory.create(testString)).thenReturn(callInformation);

        callRaterRowProcessor.processRow(testString);

        verify(callInformationFactory, times(1)).create(testString);
        verify(callCostCalculator, times(1)).calculateCost(callInformation);
    }

}