package com.github.sujankumarmitra.pdfforj.service;

import java.nio.file.Path;

public interface PdfCreateOptions {

    Path getDestination();

    boolean overwriteIfExists();
}
