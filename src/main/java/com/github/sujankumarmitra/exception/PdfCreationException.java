package com.github.sujankumarmitra.exception;

public class PdfCreationException extends RuntimeException {
    public PdfCreationException(Throwable cause) {
        super(cause);
    }

    public PdfCreationException(String message) {
        super(message);
    }
}
