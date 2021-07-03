package com.github.sujankumarmitra.pdf4j.exception;

public class FileAlreadyExistsException extends PdfCreationException {
    public FileAlreadyExistsException() {
        super("File already exists on specified path");
    }
}
