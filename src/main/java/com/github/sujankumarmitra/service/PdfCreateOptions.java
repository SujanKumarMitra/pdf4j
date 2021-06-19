package com.github.sujankumarmitra.service;

import java.nio.file.Path;

public interface PdfCreateOptions {

    Path getDestination();

    boolean overwriteIfExists();
}
