package com.github.sujankumarmitra.model.builder.impl;

import com.github.sujankumarmitra.exception.InvalidPdfFileException;
import com.github.sujankumarmitra.exception.PdfException;
import com.github.sujankumarmitra.model.PdfFile;
import com.github.sujankumarmitra.model.builder.PdfFileBuilder;
import com.github.sujankumarmitra.model.impl.DefaultPdfFile;
import com.github.sujankumarmitra.util.FileUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        Path path = Paths.get(absolutePath);
        checkForValidity(path);
        return new DefaultPdfFile(path);
    }

    private void checkForValidity(Path path) {
        if(!FileUtils.isPdfFile(path)) {
            throw new InvalidPdfFileException("Not a PDF file");
        }
    }
}
