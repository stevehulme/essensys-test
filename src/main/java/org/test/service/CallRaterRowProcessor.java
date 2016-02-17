package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.domain.CallInformation;
import org.test.domain.CallInformationWithCost;
import org.test.factory.CallInformationWIthCostFactory;

import java.math.BigDecimal;

@Component
public class CallRaterRowProcessor {

    @Autowired
    private CallCostCalculator callCostCalculator;

    @Autowired
    private CallInformationWIthCostFactory callInformationWIthCostFactory;

    public CallInformationWithCost processCallInformation(CallInformation callInformation) {

        BigDecimal cost = callCostCalculator.calculateCost(callInformation);

        return callInformationWIthCostFactory.create(callInformation, cost);



    }
}
