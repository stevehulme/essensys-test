package org.test.domain;

import java.math.BigDecimal;


public class CallInformationWithCost {

    private final CallInformation callInformation;

    private final BigDecimal cost;

    public CallInformationWithCost(CallInformation callInformation, BigDecimal cost) {
        this.callInformation = callInformation;
        this.cost = cost;
    }

    public CallInformation getCallInformation() {
        return callInformation;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
