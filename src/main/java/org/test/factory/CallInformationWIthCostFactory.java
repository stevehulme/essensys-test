package org.test.factory;

import org.test.domain.CallInformation;
import org.test.domain.CallInformationWithCost;

import java.math.BigDecimal;

public class CallInformationWIthCostFactory {

    public CallInformationWithCost create(CallInformation callInformation, BigDecimal cost) {
        return new CallInformationWithCost(callInformation, cost);
    }
}
