package com.github.sujankumarmitra.model.builder.impl;

import com.github.sujankumarmitra.exception.InvalidPdfFileException;
import com.github.sujankumarmitra.exception.PdfException;
import com.github.sujankumarmitra.exception.PdfFileNotFoundException;
import com.github.sujankumarmitra.model.PdfFile;
import com.github.sujankumarmitra.model.builder.PdfFileBuilder;
import com.github.sujankumarmitra.model.impl.DefaultPdfFile;

import java.io.File;
import java.nio.file.Path;

public class DefaultPdfFileBuilder implements PdfFileBuilder {

    private String absolutePath;

    @Override
    public PdfFileBuilder withLocation(Path location) {
        this.absolutePath = location.toAbsolutePath().toString();
        return this;
    }

    @Override
    public PdfFileBuilder withLocation(String path) {
        this.absolutePath = path;
        return this;
    }

    @Override
    public PdfFileBuilder withLocation(File file) {
        this.absolutePath = file.getAbsolutePath();
        return this;
    }

    @Override
    public PdfFile build() throws PdfException {
        File file = new File(absolutePath);
        if(!file.exists()) {
            throw new PdfFileNotFoundException(absolutePath);
        }
        checkForValidity(file);
        return new DefaultPdfFile(file.toPath());
    }

    private void checkForValidity(File file) {
        if(file.isDirectory()) {
            throw new InvalidPdfFileException("given path points to a directory, not a PDF file");
        }
        if(!file.getName().endsWith(".pdf")) {
            throw new InvalidPdfFileException("not a pdf file");
        }
    }
}
