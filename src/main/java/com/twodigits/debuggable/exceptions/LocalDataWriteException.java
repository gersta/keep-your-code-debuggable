package com.twodigits.debuggable.exceptions;

import java.io.IOException;
import java.util.List;

public class LocalDataWriteException extends RuntimeException {

    public LocalDataWriteException(String filepath, List<Integer> ids, IOException cause) {
        System.out.printf("Problem writing query results to file %s for the given ids %s%n", filepath, ids);

        cause.printStackTrace();
    }
}
