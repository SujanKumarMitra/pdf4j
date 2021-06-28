package com.github.sujankumarmitra.exception;

public class InvalidPdfFileException extends PdfException {
    private final String message;

    public InvalidPdfFileException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
