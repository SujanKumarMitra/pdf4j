package com.github.sujankumarmitra.pdf4j.service.impl;

import java.nio.file.Path;

import com.github.sujankumarmitra.pdf4j.service.PdfCreateOptions;

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
