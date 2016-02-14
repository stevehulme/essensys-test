package org.test.factory;

import org.springframework.stereotype.Component;
import org.test.domain.CallDestination;
import org.test.domain.CallInformation;
import org.test.domain.TimeBand;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class CallInformationFactory {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CallInformation create(String rowFromFile) {

        String[] parameters = rowFromFile.split(",");

        CallDestination callDestination = CallDestination.valueOf(parameters[5].replace(" ", "_"));
        TimeBand timeBand = TimeBand.valueOf(parameters[6]);
        
        return new CallInformation(parameters[0], parameters[1], LocalDate.parse(parameters[2], DATE_FORMAT), LocalTime.parse(parameters[3]),
                getLengthFromString(parameters[4]), callDestination, timeBand);
    }

    private int getLengthFromString(String parameter) {
        return Integer.valueOf(parameter.replace(":", ""));
    }
}

