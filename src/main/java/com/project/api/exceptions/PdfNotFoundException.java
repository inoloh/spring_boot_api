package com.project.api.exceptions;

public class PdfNotFoundException extends RuntimeException {
    public PdfNotFoundException() {
        super("Could not find pdf to download");
    }

    public PdfNotFoundException(String fileName) {
        super("Could not find pdf " + fileName);
    }
}
