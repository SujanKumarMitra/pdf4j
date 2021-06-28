package com.github.sujankumarmitra.model.impl;

import com.github.sujankumarmitra.model.PdfFile;

import java.nio.file.Path;

public class DefaultPdfFile implements PdfFile {

    private final Path location;

    public DefaultPdfFile(Path location) {
        this.location = location;
    }

    @Override
    public Path getLocation() {
        return location;
    }


}
