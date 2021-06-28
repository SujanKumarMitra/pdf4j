package com.github.sujankumarmitra.util;

import com.github.sujankumarmitra.exception.FileAlreadyExistsException;
import com.github.sujankumarmitra.exception.InvalidFileFormatException;
import com.github.sujankumarmitra.service.PdfCreateOptions;

import java.nio.file.Files;
import java.nio.file.Path;

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
