package com.project.api.exceptions;

public class DuplicatesException extends RuntimeException {

    public DuplicatesException(String fileName) {
        super(fileName + " is already uploaded");
    }
}
