package com.project.api.exceptions;

public class WrongTypeException extends RuntimeException {

    public WrongTypeException(String fileName) {
        super(fileName + "is not of type application/pdf. Please select a pdf file to download");
    }
}
