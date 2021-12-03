package com.twodigits.debuggable.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twodigits.debuggable.exceptions.LocalDataReadException;
import com.twodigits.debuggable.exceptions.LocalDataWriteException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FileReaderWriter {

    private static final String FILE_EXTENSION = ".json";
    private static final String WRITE_DIRECTORY = "./src/main/resources";
    private static final String READ_DIRECTORY = "./src/main/resources";

    public File writeFile(String prefix, List<Integer> ids, Object data) {
        File file = getFileHandle(prefix, WRITE_DIRECTORY, ids);

        try {
            new ObjectMapper().writeValue(file, data);

            return file;
        } catch (IOException e) {
            throw new LocalDataWriteException(file.getAbsolutePath(), ids, e);
        }
    }

    public <T> T readFile(String prefix, List<Integer> ids, Class<T> model) {
        File file = getFileHandle(prefix, READ_DIRECTORY, ids);

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
