package com.github.sujankumarmitra.pdf4j.service.impl;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.sujankumarmitra.pdf4j.exception.PdfCreationException;
import com.github.sujankumarmitra.pdf4j.model.PdfFile;
import com.github.sujankumarmitra.pdf4j.service.PdfCreateOptions;
import com.github.sujankumarmitra.pdf4j.service.impl.DefaultPdfCreateOptions;
import com.github.sujankumarmitra.pdf4j.service.impl.DefaultPdfMerger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.ClassLoader.getSystemClassLoader;
import static org.junit.jupiter.api.Assertions.fail;

class DefaultPdfMergerTest {

    private static final String DOC_1_NAME = "doc1.pdf";
    private static final String DOC_2_NAME = "doc2.pdf";
    private static final String MERGED_DOC_NAME = "merged-doc.pdf";

    private DefaultPdfMerger pdfMerger;

    private PDDocument toPDDoc(InputStream input) {
        try {
            return PDDocument.load(input);
        } catch (IOException e) {
            throw new PdfCreationException(e);
        }
    }

    private InputStream toInputStream(Path path) {
        try {
            return Files.newInputStream(path);
        } catch (IOException e) {
            throw new PdfCreationException(e);
        }
    }

    private PdfFile pathToPdfFile(Path path) {
        return () -> path;
    }

    @BeforeEach
    void setUp() {
        pdfMerger = new DefaultPdfMerger();
    }

    @Test
    void givenTwoPdfs_whenMerged_shouldMerge() {
        List<PdfFile> pdfFiles = getPdfFiles();
        int pageCountSum = getPageCountSum(pdfFiles);
        PdfCreateOptions options = getPdfCreateOptions();

        PdfFile pdfFile = pdfMerger.mergePdfs(pdfFiles, options);

        Assertions.assertNotNull(pdfFile);

        Path path = pdfFile.getLocation();
        System.out.println(path);

        Assertions.assertNotNull(path);
        Assertions.assertTrue(Files.exists(path));

        try {
            Assertions.assertEquals(pageCountSum,
                    PDDocument.load(Files.newInputStream(path)).getNumberOfPages());
        } catch (IOException e) {
            fail(e);
        }
    }

    private int getPageCountSum(List<PdfFile> pdfFiles) {
        return pdfFiles.stream()
                .map(PdfFile::getLocation)
                .map(this::toInputStream)
                .map(this::toPDDoc)
                .mapToInt(PDDocument::getNumberOfPages)
                .sum();
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
                .map(this::pathToPdfFile)
                .collect(Collectors.toList());
    }

    private Path nameToPath(String docName) {
        return Paths
                .get(getSystemClassLoader()
                        .getResource(docName)
                        .getPath());
    }
}