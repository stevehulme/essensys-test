package org.test.service;

import org.test.domain.CallInformationWithCost;

import java.math.RoundingMode;

public class CallInformationWIthCostSerialiser {

    public static final String COMMA_SEPARATOR = ",";

    public String toString(CallInformationWithCost callInformationWithCost) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(callInformationWithCost.getCallInformation().getCustomerCLI())
                .append(COMMA_SEPARATOR)
                .append(callInformationWithCost.getCallInformation().getTelephoneNumber())
                .append(COMMA_SEPARATOR)
                .append(callInformationWithCost.getCallInformation().getCallDate())
                .append(COMMA_SEPARATOR)
                .append(callInformationWithCost.getCallInformation().getCallTime())
                .append(COMMA_SEPARATOR)
                .append(callInformationWithCost.getCallInformation().getDuration())
                .append(COMMA_SEPARATOR)
                .append(callInformationWithCost.getCallInformation().getCallDestination().name().replace("_", " "))
                .append(COMMA_SEPARATOR)
                .append(callInformationWithCost.getCallInformation().getTimeBand())
                .append(COMMA_SEPARATOR)
                .append(callInformationWithCost.getCost().setScale(2, RoundingMode.CEILING));

        return stringBuilder.toString();
    }
}
