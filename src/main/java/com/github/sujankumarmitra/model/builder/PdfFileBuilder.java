package com.github.sujankumarmitra.model.builder;

import com.github.sujankumarmitra.exception.PdfException;
import com.github.sujankumarmitra.model.PdfFile;
import com.github.sujankumarmitra.model.builder.impl.DefaultPdfFileBuilder;

import java.io.File;
import java.nio.file.Path;

public interface PdfFileBuilder {

    PdfFileBuilder withLocation(Path location);

    PdfFileBuilder withLocation(String path);

    PdfFileBuilder withLocation(File file);

    PdfFile build() throws PdfException;

    static PdfFileBuilder newBuilder() {
        return new DefaultPdfFileBuilder();
    }
}
