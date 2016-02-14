package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
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
            List<String> collect = lines.map(this::processRow).collect(Collectors.toList());
            filesWrapper.writeFile(collect, FileSystems.getDefault().getPath("output.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processRow(String rowFromFile) {
        if (rowFromFile.startsWith("Customer")) {
            return rowFromFile  + ", Cost";
        }
        return callRaterRowProcessor.processRow(rowFromFile).orElse("Invalid Row");
    }
}
