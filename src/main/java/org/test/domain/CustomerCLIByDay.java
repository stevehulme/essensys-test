package org.test.domain;

import java.math.BigDecimal;

/**
 * Created by Steve on 16/02/2016.
 */
public class CustomerCLIByDay {

    private final String customerCLI;
    private final String callDate;

    private BigDecimal totalCost;

    public CustomerCLIByDay(String customerCLI, String callDate, BigDecimal totalCost) {
        this.customerCLI = customerCLI;
        this.callDate = callDate;
        this.totalCost = totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getCustomerCLI() {
        return customerCLI;
    }

    public String getCallDate() {
        return callDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }


}
