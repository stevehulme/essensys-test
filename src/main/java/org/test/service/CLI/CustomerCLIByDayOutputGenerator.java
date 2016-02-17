package org.test.service.CLI;

import org.springframework.stereotype.Component;
import org.test.domain.CustomerCLIByDay;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CustomerCLIByDayOutputGenerator {

    public List<String> getResults(Map<String, Map<String, CustomerCLIByDay>> customerCLIToCallInformationMap) {
        List<String> allResults = new ArrayList<>();
        allResults.add("Date,CLI,TotalCost");

        customerCLIToCallInformationMap.forEach((date, stringCustomerCLIByDayMap) -> addCLIData(date, stringCustomerCLIByDayMap, allResults));

        return allResults;
    }

    private void addCLIData(String date, Map<String, CustomerCLIByDay> stringCustomerCLIByDayMap, List<String> allResults) {

        stringCustomerCLIByDayMap.forEach((customerCLI, customerCLIByDay) -> addResultsToMap(customerCLI, date, customerCLIByDay, allResults));

    }

    private void addResultsToMap(String customerCLI, String date, CustomerCLIByDay customerCLIByDay, List<String> allResults) {
        allResults.add(date + "," + customerCLI + "," + customerCLIByDay.getTotalCost().setScale(2, RoundingMode.CEILING));
    }
}
