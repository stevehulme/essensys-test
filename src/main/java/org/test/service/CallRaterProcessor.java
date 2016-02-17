package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.domain.CallInformation;
import org.test.domain.CallInformationWithCost;
import org.test.factory.CallInformationFactory;
import org.test.service.CLI.CustomerCLIByDayOutputGenerator;
import org.test.service.CLI.CustomerCLIByDayProcessor;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class CallRaterProcessor {

    @Autowired
    private CallRaterRowProcessor callRaterRowProcessor;

    @Autowired
    private FilesWriterWrapper filesWriterWrapper;

    @Autowired
    private CallInformationFactory callInformationFactory;

    @Autowired
    private CallInformationWIthCostSerialiser callInformationWIthCostSerialiser;

    @Autowired
    private CustomerCLIByDayProcessor customerCLIByDayProcessor;

    @Autowired
    CustomerCLIByDayOutputGenerator customerCLIByDayOutputGenerator;

    private List<String> success = new ArrayList<>();
    private List<String> failure = new ArrayList<>();

    public void processStream(Stream<String> lines) throws IOException {

        lines.forEach(row -> processRow(row));
        outputToFiles();
    }

    private void processRow(String rowFromFile) {

        Optional<CallInformation> callInformation = callInformationFactory.create(rowFromFile);

        if (callInformation.isPresent()) {
            processSuccessfulRow(callInformation);
        } else {
            if (rowFromFile.startsWith("Customer")) {
                success.add(rowFromFile + ", Cost");
            }
            failure.add(rowFromFile);
        }
    }

    private void processSuccessfulRow(Optional<CallInformation> callInformation) {
        CallInformationWithCost callInformationWithCost = callRaterRowProcessor.processCallInformation(callInformation.get());
        success.add(callInformationWIthCostSerialiser.toString(callInformationWithCost));
        customerCLIByDayProcessor.processCallInformationWithCost(callInformationWithCost);
    }

    private void outputToFiles() throws IOException {
        filesWriterWrapper.writeFile(success, FileSystems.getDefault().getPath("output.csv"));
        filesWriterWrapper.writeFile(failure, FileSystems.getDefault().getPath("unmatched.csv"));
        List<String> customerCLIOutput = customerCLIByDayOutputGenerator.getResults(customerCLIByDayProcessor.getCustomerCLIToCallInformationMap());
        filesWriterWrapper.writeFile(customerCLIOutput, FileSystems.getDefault().getPath("customerCLI.csv"));
    }
}
