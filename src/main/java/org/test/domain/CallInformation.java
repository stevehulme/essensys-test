package org.test.domain;

public class CallInformation {

    private final String customerCLI;
    private final String telephoneNumber;
    private final String callDate;
    private final String callTime;
    private final String duration;
    private final CallDestination callDestination;
    private final TimeBand timeBand;

    public CallInformation(String customerCLI, String telephoneNumber, String callDate, String callTime, String duration, CallDestination callDestination, TimeBand timeBand) {
        this.customerCLI = customerCLI;
        this.telephoneNumber = telephoneNumber;
        this.callDate = callDate;
        this.callTime = callTime;
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

    public String getCallDate() {
        return callDate;
    }

    public String getCallTime() {
        return callTime;
    }

    public String getDuration() {
        return duration;
    }

    public CallDestination getCallDestination() {
        return callDestination;
    }

    public TimeBand getTimeBand() {
        return timeBand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallInformation that = (CallInformation) o;

        if (customerCLI != null ? !customerCLI.equals(that.customerCLI) : that.customerCLI != null) return false;
        if (telephoneNumber != null ? !telephoneNumber.equals(that.telephoneNumber) : that.telephoneNumber != null)
            return false;
        if (callDate != null ? !callDate.equals(that.callDate) : that.callDate != null) return false;
        if (callTime != null ? !callTime.equals(that.callTime) : that.callTime != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (callDestination != that.callDestination) return false;
        return timeBand == that.timeBand;

    }

    @Override
    public int hashCode() {
        int result = customerCLI != null ? customerCLI.hashCode() : 0;
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        result = 31 * result + (callDate != null ? callDate.hashCode() : 0);
        result = 31 * result + (callTime != null ? callTime.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (callDestination != null ? callDestination.hashCode() : 0);
        result = 31 * result + (timeBand != null ? timeBand.hashCode() : 0);
        return result;
    }

}
