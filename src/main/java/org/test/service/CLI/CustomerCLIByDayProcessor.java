package org.test.service.CLI;

import org.springframework.beans.factory.annotation.Autowired;
import org.test.domain.CallInformationWithCost;
import org.test.domain.CustomerCLIByDay;
import org.test.factory.CustomerCLIByDayFactory;

import java.util.HashMap;
import java.util.Map;

public class CustomerCLIByDayProcessor {

    @Autowired
    private CustomerCLIByDayFactory customerCLIByDayFactory;

    private Map<String, Map<String, CustomerCLIByDay>> customerCLIToCallInformationMap = new HashMap<>();

    public void processCallInformationWithCost(CallInformationWithCost callInformationWithCost) {

        String customerCLI = callInformationWithCost.getCallInformation().getCustomerCLI();
        String callDate = callInformationWithCost.getCallInformation().getCallDate();

        if (customerCLIToCallInformationMap.containsKey(callDate)) {
            Map<String, CustomerCLIByDay> customerCLIByDayForDay = customerCLIToCallInformationMap.get(callDate);
            if (customerCLIByDayForDay.containsKey(customerCLI)) {
                CustomerCLIByDay customerCLIByDay = customerCLIByDayForDay.get(customerCLI);
                customerCLIByDay.setTotalCost(customerCLIByDay.getTotalCost().add(callInformationWithCost.getCost()));

            } else {
                CustomerCLIByDay fromCallInformationWithCost = customerCLIByDayFactory.createFromCallInformationWithCost(callInformationWithCost);
                customerCLIByDayForDay.put(customerCLI, fromCallInformationWithCost);
            }
        } else {
            CustomerCLIByDay fromCallInformationWithCost = customerCLIByDayFactory.createFromCallInformationWithCost(callInformationWithCost);
            Map<String, CustomerCLIByDay> customerCLIByDayForDay = new HashMap<>();
            customerCLIByDayForDay.put(customerCLI, fromCallInformationWithCost);
            customerCLIToCallInformationMap.put(callDate, customerCLIByDayForDay);
        }
    }

    public Map<String, Map<String, CustomerCLIByDay>> getCustomerCLIToCallInformationMap() {
        return customerCLIToCallInformationMap;
    }
}
