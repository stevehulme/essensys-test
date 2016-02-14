package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.domain.CallInformation;
import org.test.factory.CallInformationFactory;

import java.math.BigDecimal;

@Component
public class CallRaterRowProcessor {

    @Autowired
    private CallInformationFactory callInformationFactory;

    @Autowired
    private CallCostCalculator callCostCalculator;

    public String processRow(String rowFromFile) {

        CallInformation callInformation = callInformationFactory.create(rowFromFile);

        BigDecimal cost = callCostCalculator.calculateCost(callInformation);

        return null;

    }
}
