package org.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.domain.CallDestination;
import org.test.domain.CallInformation;
import org.test.domain.CallInformationWithCost;
import org.test.domain.TimeBand;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CallInformationWIthCostSerialiserTest {

    @InjectMocks
    private CallInformationWIthCostSerialiser callInformationWIthCostSerialiser;

    @Test

    public void testSerialisation() {
        String outputString = "_02031705557,_01789470476,25/11/2015,12:18:50,00:00:07,UK National,Peak,12.0";

        CallInformationWithCost callInformationWIthCost = mock(CallInformationWithCost.class);
        CallInformation callInformation = mock(CallInformation.class);
        when(callInformation.getCustomerCLI()).thenReturn("_02031705557");
        when(callInformation.getTelephoneNumber()).thenReturn("_01789470476");
        when(callInformation.getCallDate()).thenReturn("25/11/2015");
        when(callInformation.getCallTime()).thenReturn("12:18:50");
        when(callInformation.getDuration()).thenReturn("00:00:07");
        when(callInformation.getCallDestination()).thenReturn(CallDestination.UK_National);
        when(callInformation.getTimeBand()).thenReturn(TimeBand.Peak);
        when(callInformationWIthCost.getCallInformation()).thenReturn(callInformation);
        BigDecimal cost = mock(BigDecimal.class);
        when(cost.setScale(2, RoundingMode.CEILING)).thenReturn(cost);
        when(cost.toString()).thenReturn("12.0");

        when(callInformationWIthCost.getCost()).thenReturn(cost);

        String responseString = callInformationWIthCostSerialiser.toString(callInformationWIthCost);

        assertEquals(outputString, responseString);
    }

}