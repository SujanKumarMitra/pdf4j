package com.github.sujankumarmitra.pdfforj.model.builder.impl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.sujankumarmitra.pdfforj.model.PdfFile;
import com.github.sujankumarmitra.pdfforj.model.builder.PdfFileBuilder;
import com.github.sujankumarmitra.pdfforj.util.FileUtils;

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
    public PdfFile build() throws IllegalArgumentException {
        Path path = Paths.get(absolutePath);
        checkForValidity(path);

        return new DefaultPdfFile(path);
    }

    private void checkForValidity(Path path) {
        if(!FileUtils.isPdfFile(path)) {
            throw new IllegalArgumentException("Not a PDF file");
        }
    }

    private static class DefaultPdfFile implements PdfFile {
        private final Path path;

        public DefaultPdfFile(Path path) {
            this.path = path;
        }

        @Override
        public Path getLocation() {
            return path;
        }
    }
}
