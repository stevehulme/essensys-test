package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.data.CallCostMap;
import org.test.domain.CallInformation;
import org.test.domain.TimeBand;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class CallCostCalculator {

    @Autowired
    private CallCostMap callCostMap;

    private static double lowestPrice = 5.0;

    public BigDecimal calculateCost(CallInformation callInformation) {

        BigDecimal finalCost;

        double costPerMinute = getCostPerMinute(callInformation);

        if (costPerMinute == 0) {
            finalCost = BigDecimal.valueOf(lowestPrice);
        }
        else {
            double calculatedCost = costPerMinute / 60 * callInformation.getDuration();

            finalCost = calculatedCost < lowestPrice ? BigDecimal.valueOf(lowestPrice) : BigDecimal.valueOf(calculatedCost);
        }
        return finalCost;
    }

    private double getCostPerMinute(CallInformation callInformation) {
        double costForCallDestinationAndTimeBand = callCostMap.getCostForCallDestinationAndTimeBand(callInformation.getCallDestination(), callInformation.getTimeBand());
        if (callInformation.getTimeBand().equals(TimeBand.Peak)) {
            return costForCallDestinationAndTimeBand;
        }
        double peakCost = callCostMap.getCostForCallDestinationAndTimeBand(callInformation.getCallDestination(), TimeBand.Peak);
        return peakCost * (1 + costForCallDestinationAndTimeBand);

    }
}
