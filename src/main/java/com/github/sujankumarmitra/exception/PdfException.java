package com.github.sujankumarmitra.exception;

public class PdfException extends RuntimeException {
    public PdfException(Throwable cause) {
        super(cause);
    }

    public PdfException(String message) {
        super(message);
    }
}
