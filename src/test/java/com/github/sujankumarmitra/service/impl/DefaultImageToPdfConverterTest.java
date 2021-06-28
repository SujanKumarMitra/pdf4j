package com.github.sujankumarmitra.service.impl;

import com.github.sujankumarmitra.model.ImageFile;
import com.github.sujankumarmitra.model.PdfFile;
import com.github.sujankumarmitra.model.builder.FileBuilders;
import com.github.sujankumarmitra.service.PdfCreateOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.ClassLoader.getSystemClassLoader;
import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultImageToPdfConverterTest {
    private static String IMAGE_NAME = "image.png";
    private static String PDF_NAME = "pdf-of-image.png";

    private DefaultImageToPdfConverter converter;

    @BeforeEach
    void setUp() {
        converter = new DefaultImageToPdfConverter();
    }

    @Test
    void givenValidImage_whenConverted_shouldConvert() {
        ImageFile imageFile = getImageFile();
        PdfCreateOptions createOptions = getCreateOptions();

        PdfFile pdfFile = converter.convert(imageFile, createOptions);

        assertNotNull(pdfFile);
        Path location = pdfFile.getLocation();

        assertNotNull(location);
        assertTrue(Files.exists(location));

        System.out.println(location);
    }

    private DefaultPdfCreateOptions getCreateOptions() {
        return new DefaultPdfCreateOptions(
                Paths.get(
                        getSystemClassLoader()
                                .getResource("pdf-of-image.pdf")
                                .getPath()), true);
    }

    private ImageFile getImageFile() {
        return FileBuilders
                .newImageFileBuilder()
                .withLocation(get(
                        getSystemClassLoader()
                                .getResource(IMAGE_NAME)
                                .getPath()))
                .build();
    }
}