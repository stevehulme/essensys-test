package org.test.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class FilesWriterWrapper {

    public void writeFile(List<String> contents, Path filePath) throws IOException {
        Files.deleteIfExists(filePath.getFileName());
        Path file = Files.createFile(filePath.getFileName());
        Files.write(file,contents, Charset.defaultCharset());
    }
}
