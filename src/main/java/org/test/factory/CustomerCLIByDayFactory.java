package org.test.factory;

import org.springframework.stereotype.Component;
import org.test.domain.CallInformationWithCost;
import org.test.domain.CustomerCLIByDay;

@Component
public class CustomerCLIByDayFactory {
    public CustomerCLIByDay createFromCallInformationWithCost(CallInformationWithCost callInformationWithCost) {
        return new CustomerCLIByDay(callInformationWithCost.getCallInformation().getCustomerCLI(),
                                    callInformationWithCost.getCallInformation().getCallDate(),
                                    callInformationWithCost.getCost());
    }
}
