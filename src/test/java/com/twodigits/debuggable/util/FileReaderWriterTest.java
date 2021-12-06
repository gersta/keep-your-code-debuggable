package com.twodigits.debuggable.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderWriterTest {

    private static final String WRITE_DIR = "./src/main/resources";

    FileReaderWriter readerWriter;

    @BeforeEach
    void setup() {
        readerWriter = new FileReaderWriter(WRITE_DIR);
    }

    @AfterEach
    void tearDown() {
        for (File file : Objects.requireNonNull(new File("./src/main/resources/").listFiles())) {
            file.delete();
        }
    }


    @Test
    void file_exists() {
        File result = readerWriter.writeFile("ABC", Arrays.asList(1, 2, 3), "Hello World");

        assertTrue(result.exists());
    }

    @Test
    void file_exists_at_expected_location() {
        File result = readerWriter.writeFile("ABC", Arrays.asList(1, 2, 3), "Hello World");

        // TODO: quick solution, needs to be overthought
        String resultForwardSlashes = result.getPath().replace("\\", "/");


        assertEquals("./src/main/resources/ABC_1_2_3.json", resultForwardSlashes);
    }


    @Test
    void file_contains_given_data() throws IOException {
        File result = readerWriter.writeFile("ABC", Arrays.asList(1, 2, 3), "Hello World");

        String content = Files.lines(Path.of(result.getPath())).collect(Collectors.joining("\n"));

        assertEquals("\"Hello World\"", content);
    }

    @Test
    void file_has_given_name() {
        File result = readerWriter.writeFile("ABC", Arrays.asList(1, 2, 3), "Hello World");

        assertEquals("ABC_1_2_3.json", result.getName());
    }

    @Test
    void read_file_from_expected_location() throws IOException {
        new ObjectMapper().writeValue(new File("./src/main/resources/READ_7_8_9.json"), "Hello World");

        String result = readerWriter.readFile("READ", Arrays.asList(7, 8, 9), String.class);

        assertEquals("Hello World", result);
    }
}