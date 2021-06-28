package com.github.sujankumarmitra.exception;

public class FileAlreadyExistsException extends PdfException {
    public FileAlreadyExistsException() {
        super("File already exists on specified path");
    }
}
