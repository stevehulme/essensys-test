package org.test.service.CLI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.domain.CallInformation;
import org.test.domain.CallInformationWithCost;
import org.test.domain.CustomerCLIByDay;
import org.test.factory.CustomerCLIByDayFactory;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerCLIByDayProcessorTest {

    @Mock
    private CustomerCLIByDayFactory customerCLIByDayFactory;

    @InjectMocks
    private CustomerCLIByDayProcessor customerCLIByDayProcessor;

    @Test
    public void testProcessCallInformation() {

        CallInformationWithCost callInformationWithCost1 = mock(CallInformationWithCost.class);
        CallInformation callInformation1 = mock(CallInformation.class);
        when(callInformation1.getCustomerCLI()).thenReturn("1");
        when(callInformation1.getCallDate()).thenReturn("01-01-01");
        when(callInformationWithCost1.getCallInformation()).thenReturn(callInformation1);
        CustomerCLIByDay customerCLIByDay1 = mock(CustomerCLIByDay.class);
        when(customerCLIByDay1.getTotalCost()).thenReturn(BigDecimal.ONE);
        when(customerCLIByDayFactory.createFromCallInformationWithCost(callInformationWithCost1)).thenReturn(customerCLIByDay1);


        CallInformationWithCost callInformationWithCost2 = mock(CallInformationWithCost.class);
        CallInformation callInformation2 = mock(CallInformation.class);
        when(callInformation2.getCustomerCLI()).thenReturn("1");
        when(callInformation2.getCallDate()).thenReturn("01-01-02");
        when(callInformationWithCost2.getCallInformation()).thenReturn(callInformation2);
        CustomerCLIByDay customerCLIByDay2 = mock(CustomerCLIByDay.class);
        when(customerCLIByDay2.getTotalCost()).thenReturn(BigDecimal.TEN);
        when(customerCLIByDayFactory.createFromCallInformationWithCost(callInformationWithCost2)).thenReturn(customerCLIByDay2);

        CallInformationWithCost callInformationWithCost3 = mock(CallInformationWithCost.class);
        CallInformation callInformation3 = mock(CallInformation.class);
        when(callInformation3.getCustomerCLI()).thenReturn("3");
        when(callInformation3.getCallDate()).thenReturn("01-01-01");
        when(callInformationWithCost3.getCallInformation()).thenReturn(callInformation3);
        CustomerCLIByDay customerCLIByDay3 = mock(CustomerCLIByDay.class);
        when(customerCLIByDay3.getTotalCost()).thenReturn(BigDecimal.ZERO);
        when(customerCLIByDayFactory.createFromCallInformationWithCost(callInformationWithCost3)).thenReturn(customerCLIByDay3);

        customerCLIByDayProcessor.processCallInformationWithCost(callInformationWithCost1);
        customerCLIByDayProcessor.processCallInformationWithCost(callInformationWithCost2);
        customerCLIByDayProcessor.processCallInformationWithCost(callInformationWithCost3);

        Map<String, Map<String, CustomerCLIByDay>> customerCLIToCallInformationMap = customerCLIByDayProcessor.getCustomerCLIToCallInformationMap();

        assertEquals(customerCLIToCallInformationMap.size(), 2);

        assertEquals(customerCLIToCallInformationMap.get("01-01-01").size(), 2);
        assertEquals(customerCLIToCallInformationMap.get("01-01-01").get("1").getTotalCost(), BigDecimal.ONE);
        assertEquals(customerCLIToCallInformationMap.get("01-01-01").get("3").getTotalCost(), BigDecimal.ZERO);
        assertEquals(customerCLIToCallInformationMap.get("01-01-02").size(), 1);
        assertEquals(customerCLIToCallInformationMap.get("01-01-02").get("1").getTotalCost(), BigDecimal.TEN);


    }

}