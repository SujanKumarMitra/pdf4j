package com.github.sujankumarmitra.pdf4j.service.impl;

import com.github.sujankumarmitra.pdf4j.model.PdfFile;
import com.github.sujankumarmitra.pdf4j.model.builder.FileBuilders;
import com.github.sujankumarmitra.pdf4j.service.PdfCreateOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultPdfSplitterTest {

    private static final String MULTI_PAGE_PDF = "multi-page.pdf";
    
    private DefaultPdfSplitter pdfSplitter;
    
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        pdfSplitter = new DefaultPdfSplitter();
    }

    @Test
    void givenMultiPagePdf_whenSplit_shouldCreateIndividualPages() {
        PdfFile inputPdf = FileBuilders.newPdfFileBuilder()
                .withLocation(getResourcePath(MULTI_PAGE_PDF))
                .build();
        
        PdfCreateOptions options = new DefaultPdfCreateOptions(tempDir, true);
        
        pdfSplitter.splitPdf(inputPdf, options);
        
        assertTrue(Files.exists(tempDir.resolve("1.pdf")));
        assertTrue(Files.exists(tempDir.resolve("2.pdf")));
    }

    private Path getResourcePath(String filename) {
        return Paths.get(ClassLoader.getSystemClassLoader()
                .getResource(filename).getPath());
    }
}
