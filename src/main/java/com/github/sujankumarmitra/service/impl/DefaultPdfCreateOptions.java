package com.github.sujankumarmitra.service.impl;

import com.github.sujankumarmitra.service.PdfCreateOptions;

import java.nio.file.Path;

public class DefaultPdfCreateOptions implements PdfCreateOptions {

    private final Path destination;
    private final boolean overwrite;

    public DefaultPdfCreateOptions(Path destination, boolean overwrite) {
        this.destination = destination;
        this.overwrite = overwrite;
    }

    @Override
    public Path getDestination() {
        return destination;
    }

    @Override
    public boolean overwriteIfExists() {
        return overwrite;
    }
}
