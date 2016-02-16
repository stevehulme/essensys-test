package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.domain.CallInformation;
import org.test.factory.CallInformationFactory;
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
    private CustomerCLIByDayProcessor customerCLIByDayProcessor;

    private List<String> success = new ArrayList<>();
    private List<String> failure = new ArrayList<>();

    public void processStream(Stream<String> lines) throws IOException {

        lines.forEach(row -> processRow(row));
        outputToFiles();
    }

    private void processRow(String rowFromFile) {

        Optional<CallInformation> callInformation = callInformationFactory.create(rowFromFile);

        if (callInformation.isPresent()) {
            success.add(callRaterRowProcessor.processRow(callInformation.get()));
            customerCLIByDayProcessor.processRow(callInformation.get());
        } else {
            if (rowFromFile.startsWith("Customer")) {
                success.add(rowFromFile + ", Cost");
            }
            failure.add(rowFromFile);
        }
    }

    private void outputToFiles() throws IOException {
        filesWriterWrapper.writeFile(success, FileSystems.getDefault().getPath("output.csv"));
        filesWriterWrapper.writeFile(failure, FileSystems.getDefault().getPath("unmatched.csv"));
        filesWriterWrapper.writeFile(customerCLIByDayProcessor.getResults(), FileSystems.getDefault().getPath("customerCLI.csv"));
    }
}
