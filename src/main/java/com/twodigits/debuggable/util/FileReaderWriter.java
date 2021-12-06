package com.twodigits.debuggable.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twodigits.debuggable.exceptions.LocalDataReadException;
import com.twodigits.debuggable.exceptions.LocalDataWriteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileReaderWriter {

    private static final String FILE_EXTENSION = ".json";

    private final String writeDirectory;
    private final String readDirectory;

    public FileReaderWriter(
            @Value("${twodigits.debuggable.local-file.write-dir}") String writeDirectory,
            @Value("${twodigits.debuggable.local-file.read-dir}") String readDirectory
    ) {
        this.writeDirectory = writeDirectory;
        this.readDirectory = readDirectory;
    }

    public File writeFile(String prefix, List<Integer> ids, Object data) {
        File file = getFileHandle(prefix, writeDirectory, ids);

        log.debug("Trying to write file to {}", file.getAbsolutePath());

        try {
            new ObjectMapper().writeValue(file, data);

            return file;
        } catch (IOException e) {
            throw new LocalDataWriteException(file.getAbsolutePath(), ids, e);
        }
    }

    public <T> T readFile(String prefix, List<Integer> ids, Class<T> model) {
        File file = getFileHandle(prefix, readDirectory, ids);

        try {
            return new ObjectMapper().readValue(file, model);
        } catch (IOException e) {
            throw new LocalDataReadException(file.getAbsolutePath(), ids, e);
        }
    }

    private File getFileHandle(String prefix, String directory, List<Integer> ids) {
        String idsSeparatedByUnderscores = ids.stream().map(String::valueOf).collect(Collectors.joining("_"));

        directory = directory.endsWith("/") ? directory : directory + "/";

        String filename = String.format("%s%s_%s%s", directory, prefix, idsSeparatedByUnderscores, FILE_EXTENSION);

        return new File(filename);
    }
}
