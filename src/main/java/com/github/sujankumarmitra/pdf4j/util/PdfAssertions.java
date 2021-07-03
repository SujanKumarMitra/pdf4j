package com.github.sujankumarmitra.pdf4j.util;

import java.nio.file.Files;
import java.nio.file.Path;

import com.github.sujankumarmitra.pdf4j.exception.FileAlreadyExistsException;
import com.github.sujankumarmitra.pdf4j.exception.InvalidFileFormatException;
import com.github.sujankumarmitra.pdf4j.service.PdfCreateOptions;

public abstract class PdfAssertions {
    public static void assertLegalOptions(PdfCreateOptions options) {
        Path path = options.getDestination();
        if (!path.toString().endsWith(".pdf")) {
            throw new InvalidFileFormatException("Not a PDF file");
        }

        if (!options.overwriteIfExists() && Files.exists(path)) {
            throw new FileAlreadyExistsException();
        }
    }
}
