package com.github.sujankumarmitra.pdf4j.service;

import java.nio.file.Path;

public interface PdfCreateOptions {

    Path getDestination();

    boolean overwriteIfExists();
}
