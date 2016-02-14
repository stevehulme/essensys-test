package org.test.domain;


public enum CallDestination {

    CANADA(10, -0.1, -0.1),
    DENMARK(4.9, -0.08, -0.08),
    FRANCE(3.23, -0.08, -0.08),
    IRELAND(3.22,-0.1, -0.05),
    SPAIN(9.33, -0.1, -0.05),
    UK_Mobile(2.1, -0.12, -1),
    UK_National(0.73, -0.03, -0.03),
    USA(12.22, -0.03, -0.05);

    private final double peak;
    private final double offPeakDiscount;
    private final double weekendDiscount;

    CallDestination(double peak, double offPeakDiscount, double weekendDiscount) {
        this.peak = peak;
        this.offPeakDiscount = offPeakDiscount;
        this.weekendDiscount = weekendDiscount;
    }

    public double getPeak() {
        return peak;
    }

    public double getOffPeakDiscount() {
        return offPeakDiscount;
    }

    public double getWeekendDiscount() {
        return weekendDiscount;
    }
}
