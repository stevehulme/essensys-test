package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class CallRaterService {

    @Autowired
    private FilesReaderWrapper filesReaderWrapper;

    @Autowired
    private CallRaterProcessor callRaterProcessor;

    public void rateData(Path filePath) {

        try {
            Stream<String> lines = filesReaderWrapper.getLines(filePath);
            callRaterProcessor.processStream(lines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
