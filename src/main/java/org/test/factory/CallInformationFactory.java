package org.test.factory;

import org.springframework.stereotype.Component;
import org.test.domain.CallDestination;
import org.test.domain.CallInformation;
import org.test.domain.TimeBand;

import java.util.Optional;

@Component
public class CallInformationFactory {

    public Optional<CallInformation> create(String rowFromFile) {

        String[] parameters = rowFromFile.split(",");

        if (parameters.length != 7) {
            return Optional.empty();
        }

        CallDestination callDestination;
        TimeBand timeBand;
        try {
            callDestination = CallDestination.valueOf(parameters[5].replace(" ", "_"));
            timeBand = TimeBand.valueOf(parameters[6]);
        }
        catch (IllegalArgumentException e) {
            return Optional.empty();
        }

        CallInformation callInformation = new CallInformation(parameters[0], parameters[1], parameters[2], parameters[3],
                parameters[4], callDestination, timeBand);
        return Optional.of(callInformation);
    }

}

