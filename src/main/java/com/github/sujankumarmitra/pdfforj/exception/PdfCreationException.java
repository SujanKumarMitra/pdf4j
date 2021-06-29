package com.github.sujankumarmitra.pdfforj.exception;

public class PdfCreationException extends RuntimeException {
    public PdfCreationException(Throwable cause) {
        super(cause);
    }

    public PdfCreationException(String message) {
        super(message);
    }
}
