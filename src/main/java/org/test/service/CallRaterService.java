package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CallRaterService {

    @Autowired
    private FilesWrapper filesWrapper;

    @Autowired
    private CallRaterRowProcessor callRaterRowProcessor;

    public void rateData(Path filePath) {

        try {
            Stream<String> lines = filesWrapper.getLines(filePath);
            lines.map(callRaterRowProcessor::processRow).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
