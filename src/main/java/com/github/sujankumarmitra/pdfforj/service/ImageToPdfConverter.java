package com.github.sujankumarmitra.pdfforj.service;

import java.util.List;

import com.github.sujankumarmitra.pdfforj.exception.PdfCreationException;
import com.github.sujankumarmitra.pdfforj.model.ImageFile;
import com.github.sujankumarmitra.pdfforj.model.PdfFile;

public interface ImageToPdfConverter {
    PdfFile convert(ImageFile file, PdfCreateOptions options) throws PdfCreationException;

    PdfFile convertAndMerge(List<ImageFile> files, PdfCreateOptions options) throws PdfCreationException;
}
