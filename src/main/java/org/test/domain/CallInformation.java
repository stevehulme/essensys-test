package org.test.domain;

import java.time.LocalDateTime;

public class CallInformation {

    private final String customerCLI;
    private final String telephoneNumber;
    private final LocalDateTime callDateAndTime;
    private final int duration;
    private final CallDestination callDestination;
    private final TimeBand timeBand;

    public CallInformation(String customerCLI, String telephoneNumber, LocalDateTime callDateAndTime, int duration, CallDestination callDestination, TimeBand timeBand) {
        this.customerCLI = customerCLI;
        this.telephoneNumber = telephoneNumber;
        this.callDateAndTime = callDateAndTime;
        this.duration = duration;
        this.callDestination = callDestination;
        this.timeBand = timeBand;
    }

    public String getCustomerCLI() {
        return customerCLI;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public LocalDateTime getCallDateAndTime() {
        return callDateAndTime;
    }

    public int getDuration() {
        return duration;
    }

    public CallDestination getCallDestination() {
        return callDestination;
    }

    public TimeBand getTimeBand() {
        return timeBand;
    }
}
