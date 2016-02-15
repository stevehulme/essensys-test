package org.test.service;


import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Component
public class FilesReaderWrapper {

    public Stream<String> getLines(Path filePath) throws IOException {
        return Files.lines(filePath);
    }


}
