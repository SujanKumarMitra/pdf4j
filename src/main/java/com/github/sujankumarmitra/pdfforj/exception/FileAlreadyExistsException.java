package com.github.sujankumarmitra.pdfforj.exception;

public class FileAlreadyExistsException extends PdfCreationException {
    public FileAlreadyExistsException() {
        super("File already exists on specified path");
    }
}
