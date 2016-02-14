package org.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.data.CallCostMap;
import org.test.domain.CallDestination;
import org.test.domain.CallInformation;
import org.test.domain.TimeBand;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CallCostCalculatorTest {

    @Mock
    private CallCostMap callCostMap;

    @InjectMocks
    private CallCostCalculator callCostCalculator;

    @Test
    public void testCalculatePeakCostOverMinimum() {

        CallInformation callInformation = mock(CallInformation.class);
        when(callInformation.getDuration()).thenReturn(90);
        when(callInformation.getCallDestination()).thenReturn(CallDestination.CANADA);
        when(callInformation.getTimeBand()).thenReturn(TimeBand.Peak);
        when(callCostMap.getCostForCallDestinationAndTimeBand(callInformation.getCallDestination(), callInformation.getTimeBand())).thenReturn(10.0);

        BigDecimal cost = callCostCalculator.calculateCost(callInformation);
        assertEquals(cost, BigDecimal.valueOf(15.0));
    }

    @Test
    public void testCalculateZeroCost() {

        CallInformation callInformation = mock(CallInformation.class);
        when(callInformation.getDuration()).thenReturn(60);
        when(callInformation.getCallDestination()).thenReturn(CallDestination.CANADA);
        when(callInformation.getTimeBand()).thenReturn(TimeBand.Peak);
        when(callCostMap.getCostForCallDestinationAndTimeBand(callInformation.getCallDestination(), callInformation.getTimeBand())).thenReturn(0.0);

        BigDecimal cost = callCostCalculator.calculateCost(callInformation);
        assertEquals(cost, BigDecimal.valueOf(5.0));
    }

    @Test
    public void testCalculateBelowMinimumCost() {

        CallInformation callInformation = mock(CallInformation.class);
        when(callInformation.getDuration()).thenReturn(60);
        when(callInformation.getCallDestination()).thenReturn(CallDestination.CANADA);
        when(callInformation.getTimeBand()).thenReturn(TimeBand.Peak);
        when(callCostMap.getCostForCallDestinationAndTimeBand(callInformation.getCallDestination(), callInformation.getTimeBand())).thenReturn(3.0);

        BigDecimal cost = callCostCalculator.calculateCost(callInformation);
        assertEquals(cost, BigDecimal.valueOf(5.0));
    }

    @Test
    public void testCalculateCostWithDiscount() {

        CallInformation callInformation = mock(CallInformation.class);
        when(callInformation.getDuration()).thenReturn(60);
        when(callInformation.getCallDestination()).thenReturn(CallDestination.CANADA);
        when(callInformation.getTimeBand()).thenReturn(TimeBand.Offpeak);
        when(callCostMap.getCostForCallDestinationAndTimeBand(callInformation.getCallDestination(), callInformation.getTimeBand())).thenReturn(-0.1);
        when(callCostMap.getCostForCallDestinationAndTimeBand(callInformation.getCallDestination(), TimeBand.Peak)).thenReturn(10.0);

        BigDecimal cost = callCostCalculator.calculateCost(callInformation);
        assertEquals(cost, BigDecimal.valueOf(9.0));
    }

}