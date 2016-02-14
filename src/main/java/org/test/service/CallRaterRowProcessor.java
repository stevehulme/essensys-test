package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.domain.CallDestination;
import org.test.factory.CallDestinationFactory;

@Component
public class CallRaterRowProcessor {

    @Autowired
    private CallDestinationFactory callDestinationFactory;

    public String processRow(String rowFromFile) {

        CallDestination callDestination = callDestinationFactory.create(rowFromFile);

        return null;

    }
}
