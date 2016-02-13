package org.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.test.service.CallRaterService;

import java.nio.file.FileSystems;
import java.nio.file.Path;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CallRaterService callRaterService;

    public static void main(String... args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        Path filePath = getFilePath(strings);
        callRaterService.rateData(filePath);
    }

    private Path getFilePath(String[] args) {
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("please enter the file location as a path");
        }
        return FileSystems.getDefault().getPath(args[0]);

    }
}