package com.github.sujankumarmitra.pdf4j.service.impl;

import com.github.sujankumarmitra.pdf4j.model.ImageFile;
import com.github.sujankumarmitra.pdf4j.model.PdfFile;
import com.github.sujankumarmitra.pdf4j.model.builder.FileBuilders;
import com.github.sujankumarmitra.pdf4j.service.PdfCreateOptions;

import org.apache.pdfbox.Loader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.ClassLoader.getSystemClassLoader;
import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;

class DefaultImageToPdfConverterTest {
    private static String IMAGE_NAME = "image.png";
    private static String IMAGES_DIR_NAME = "images";

    private DefaultImageToPdfConverter converter;

    @BeforeEach
    void setUp() {
        converter = new DefaultImageToPdfConverter();
    }

    @Test
    void givenValidImage_whenConverted_shouldConvert() throws IOException {
        ImageFile imageFile = getImageFile();
        PdfCreateOptions createOptions = getCreateOptions();

        PdfFile pdfFile = converter.convert(imageFile, createOptions);

        assertNotNull(pdfFile);
        Path location = pdfFile.getLocation();

        assertNotNull(location);
        assertTrue(Files.exists(location));

        Assertions.assertEquals(1,
                Loader.loadPDF(location.toFile()).getNumberOfPages());

        System.out.println(location);
    }

    @Test
    void givenValidImageList_whenConvertAndMerge_shouldConvertAndMerge() throws Throwable {
        List<ImageFile> images = getImages();
        PdfCreateOptions options = getOptions();

        int expectedPageCount = images.size();

        PdfFile pdfFile = converter.convertAndMerge(images, options);

        assertNotNull(pdfFile);
        Path location = pdfFile.getLocation();

        assertNotNull(location);

        assertEquals(expectedPageCount,
                Loader.loadPDF(location.toFile()).getNumberOfPages());

        System.out.println(location);
    }

    private DefaultPdfCreateOptions getOptions() throws URISyntaxException {
        return new DefaultPdfCreateOptions(
                get(getSystemClassLoader()
                        .getResource(IMAGES_DIR_NAME)
                        .toURI()).resolve("merged.pdf"), true);
    }

    private List<ImageFile> getImages() throws IOException, URISyntaxException {
        Path images = get(getSystemClassLoader()
                .getResource(IMAGES_DIR_NAME)
                .toURI());

        return Files.list(images)
                .map(path -> FileBuilders.newImageFileBuilder().withLocation(path).build())
                .collect(Collectors.toList());
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