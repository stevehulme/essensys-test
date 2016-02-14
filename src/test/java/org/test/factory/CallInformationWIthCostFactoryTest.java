package org.test.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.domain.CallInformation;
import org.test.domain.CallInformationWithCost;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CallInformationWIthCostFactoryTest {

    @InjectMocks
    private CallInformationWIthCostFactory callInformationWIthCostFactory;

    @Test
    public void testCreate(){

        CallInformation callInformation = mock(CallInformation.class);
        BigDecimal cost = mock(BigDecimal.class);
        CallInformationWithCost callInformationWithCost = callInformationWIthCostFactory.create(callInformation, cost);

        assertEquals(callInformation, callInformationWithCost.getCallInformation());
        assertEquals(cost, callInformationWithCost.getCost());
    }



}