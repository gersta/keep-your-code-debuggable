package com.twodigits.debuggable.exceptions;

import java.io.IOException;
import java.util.List;

public class LocalDataReadException extends RuntimeException {

    public LocalDataReadException(String filepath, List<Integer> ids, IOException cause) {
        System.out.printf("Problem reading query results from file %s for the given ids %s%n", filepath, ids);

        cause.printStackTrace();
    }
}
