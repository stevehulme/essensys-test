package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.domain.CallInformation;
import org.test.domain.CallInformationWithCost;
import org.test.factory.CallInformationFactory;
import org.test.factory.CallInformationWIthCostFactory;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class CallRaterRowProcessor {

    @Autowired
    private CallInformationFactory callInformationFactory;

    @Autowired
    private CallCostCalculator callCostCalculator;

    @Autowired
    private CallInformationWIthCostFactory callInformationWIthCostFactory;

    @Autowired
    private CallInformationWIthCostSerialiser callInformationWIthCostSerialiser;

    public Optional<String> processRow(String rowFromFile) {

        Optional<CallInformation> callInformation = callInformationFactory.create(rowFromFile);

        if (callInformation.isPresent()) {
            BigDecimal cost = callCostCalculator.calculateCost(callInformation.get());

            CallInformationWithCost callInformationWithCost = callInformationWIthCostFactory.create(callInformation.get(), cost);

            return Optional.of(callInformationWIthCostSerialiser.toString(callInformationWithCost));
        }
        return Optional.empty();
    }
}
