package com.twodigits.debuggable.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class LocalDataException extends RuntimeException {
    
    public LocalDataException(String mode, String prefix, List<String> ids, String filename, Throwable cause) {
        log.error("There was a problem with mode {} to file {} with given data {}", mode, filename, ids);

        cause.printStackTrace();
    }
}
