package com.project.api;

import com.project.api.exceptions.DuplicatesException;
import com.project.api.exceptions.PdfNotFoundException;
import com.project.api.exceptions.WrongTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(PdfNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pdfNotFoundHandler(PdfNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(WrongTypeException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String wrongTypeHandler(WrongTypeException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DuplicatesException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicatesHandler(DuplicatesException ex) {
        return ex.getMessage();
    }
}
