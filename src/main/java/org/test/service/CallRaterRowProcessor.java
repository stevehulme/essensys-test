package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.domain.CallInformation;
import org.test.factory.CallInformationFactory;

@Component
public class CallRaterRowProcessor {

    @Autowired
    private CallInformationFactory callInformationFactory;

    public String processRow(String rowFromFile) {

        CallInformation callInformation = callInformationFactory.create(rowFromFile);

        return null;

    }
}
