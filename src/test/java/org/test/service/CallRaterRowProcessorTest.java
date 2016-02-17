package org.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.domain.CallInformation;
import org.test.domain.CallInformationWithCost;
import org.test.factory.CallInformationWIthCostFactory;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CallRaterRowProcessorTest {


    @Mock
    private CallCostCalculator callCostCalculator;

    @Mock
    private CallInformationWIthCostFactory callInformationWIthCostFactory;


    @InjectMocks
    private CallRaterRowProcessor callRaterRowProcessor;

    @Test
    public void testProcessRow() {

        CallInformation callInformation = mock(CallInformation.class);
        BigDecimal cost = mock(BigDecimal.class);
        when(callCostCalculator.calculateCost(callInformation)).thenReturn(cost);
        CallInformationWithCost callInformationWithCost = mock(CallInformationWithCost.class);
        when(callInformationWIthCostFactory.create(callInformation, cost)).thenReturn(callInformationWithCost);

        CallInformationWithCost returnedCallInformationWithCost = callRaterRowProcessor.processCallInformation(callInformation);

        assertEquals(callInformationWithCost, returnedCallInformationWithCost);

        verify(callCostCalculator, times(1)).calculateCost(callInformation);
        verify(callInformationWIthCostFactory, times(1)).create(callInformation, cost);
    }

}