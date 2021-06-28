package com.github.sujankumarmitra.exception;

public class PdfFileNotFoundException extends PdfException {
    private final String path;

    public PdfFileNotFoundException(String path) {
        this.path = path;
    }

    @Override
    public String getMessage() {
        return "Pdf file does not exist on the specified path: '" + path + "'";
    }
}
