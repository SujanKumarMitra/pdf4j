package com.github.sujankumarmitra.service.impl;

import com.github.sujankumarmitra.model.PdfFile;
import com.github.sujankumarmitra.model.impl.DefaultPdfFile;
import com.github.sujankumarmitra.service.PdfCreateOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.ClassLoader.getSystemClassLoader;

class DefaultPdfMergerTest {

    private static final String DOC_1_NAME = "doc1.pdf";
    private static final String DOC_2_NAME = "doc2.pdf";
    private static final String MERGED_DOC_NAME = "merged-doc.pdf";

    private DefaultPdfMerger pdfMerger;

    @BeforeEach
    void setUp() {
        pdfMerger = new DefaultPdfMerger();
    }

    @Test
    void givenTwoPdfs_whenMerged_shouldMerge() {
        List<PdfFile> pdfFiles = getPdfFiles();
        PdfCreateOptions options = getPdfCreateOptions();

        PdfFile pdfFile = pdfMerger.mergePdfs(pdfFiles, options);

        Assertions.assertNotNull(pdfFile);

        Path path = pdfFile.getLocation();

        Assertions.assertNotNull(path);
        Assertions.assertTrue(Files.exists(path));

        System.out.println(path);
    }

    private DefaultPdfCreateOptions getPdfCreateOptions() {
        return new DefaultPdfCreateOptions(
                Paths.get(getSystemClassLoader()
                        .getResource(MERGED_DOC_NAME)
                        .getPath()), true);
    }

    private List<PdfFile> getPdfFiles() {
        return Stream.of(DOC_1_NAME, DOC_2_NAME)
                .map(this::nameToPath)
                .map(DefaultPdfFile::new)
                .collect(Collectors.toList());
    }

    private Path nameToPath(String docName) {
        return Paths
                .get(getSystemClassLoader()
                        .getResource(docName)
                        .getPath());
    }
}