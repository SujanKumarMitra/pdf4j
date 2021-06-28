package com.github.sujankumarmitra.service.impl;

import com.github.sujankumarmitra.model.PdfFile;
import com.github.sujankumarmitra.model.builder.FileBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPdfUtilsTest {

    private static final String PDF_NAME = "multi-page.pdf";
    private static final String PDF_REV_NAME = "multi-page-reverse.pdf";

    private DefaultPdfUtils pdfUtils;

    @BeforeEach
    void setUp() {
        pdfUtils = new DefaultPdfUtils();
    }

    @Test
    void givenValidPdfFile_whenReverse_shouldReverse() {
        PdfFile pdf = FileBuilders.newPdfFileBuilder()
                .withLocation(getPath(PDF_NAME))
                .build();

        pdfUtils.reversePages(pdf);

        Path path = pdf.getLocation();

        assertNotNull(path);
        assertTrue(Files.exists(path));

        System.out.println(path);
    }

    private Path getPath(String filename) {
        return Paths.get(ClassLoader.getSystemClassLoader()
                .getResource(filename).getPath());
    }
}