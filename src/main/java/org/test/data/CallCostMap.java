package org.test.data;

import org.springframework.stereotype.Component;
import org.test.domain.CallDestination;
import org.test.domain.TimeBand;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CallCostMap {

    Map<CallDestination, Map<TimeBand, Double>> callCostMap = new HashMap<>();

    @PostConstruct
    private void populateMap() {
        Arrays.asList(CallDestination.values()).forEach(this::createMapEntry);
    }

    private void createMapEntry(CallDestination callDestination) {
        callCostMap.put(callDestination, getTimeBandToPriceMap(callDestination.getPeak(), callDestination.getOffPeakDiscount(), callDestination.getWeekendDiscount()));
    }

    private Map<TimeBand, Double> getTimeBandToPriceMap(double peak, double offpeak, double weekend) {
        return Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>(TimeBand.Peak, peak),
                new AbstractMap.SimpleEntry<>(TimeBand.Offpeak, offpeak),
                new AbstractMap.SimpleEntry<>(TimeBand.Weekend, weekend))
               .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));
    }

    public double getCostForCallDestinationAndTimeBand(CallDestination callDestination, TimeBand timeBand) {
       return callCostMap.get(callDestination).get(timeBand);
    }
}
