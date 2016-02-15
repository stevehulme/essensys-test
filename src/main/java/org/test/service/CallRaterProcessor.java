package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.domain.CallInformation;
import org.test.factory.CallInformationFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CallRaterProcessor {


    @Autowired
    private CallRaterRowProcessor callRaterRowProcessor;

    @Autowired
    private FilesWriterWrapper filesWriterWrapper;

    @Autowired
    private CallInformationFactory callInformationFactory;


    public void processStream(Stream<String> lines) throws IOException {

        List<String> success = new ArrayList<>();
        List<String> failure = new ArrayList<>();

        lines.forEach(row -> processRow(row, success, failure));

        filesWriterWrapper.writeFile(success, FileSystems.getDefault().getPath("output.csv"));
        filesWriterWrapper.writeFile(failure, FileSystems.getDefault().getPath("unmatched.csv"));

    }

    private void processRow(String rowFromFile, List<String> success, List<String> failure) {

        Optional<CallInformation> callInformation = callInformationFactory.create(rowFromFile);

        if (callInformation.isPresent()) {
            success.add(callRaterRowProcessor.processRow(callInformation.get()));
        }
        else {
            if (rowFromFile.startsWith("Customer")) {
                success.add(rowFromFile  + ", Cost");
            }
            failure.add(rowFromFile);
        }
    }
}
