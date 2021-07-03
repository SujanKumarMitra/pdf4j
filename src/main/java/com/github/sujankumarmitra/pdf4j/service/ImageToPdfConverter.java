package com.github.sujankumarmitra.pdf4j.service;

import java.util.List;

import com.github.sujankumarmitra.pdf4j.exception.PdfCreationException;
import com.github.sujankumarmitra.pdf4j.model.ImageFile;
import com.github.sujankumarmitra.pdf4j.model.PdfFile;

public interface ImageToPdfConverter {
    PdfFile convert(ImageFile file, PdfCreateOptions options) throws PdfCreationException;

    PdfFile convertAndMerge(List<ImageFile> files, PdfCreateOptions options) throws PdfCreationException;
}
