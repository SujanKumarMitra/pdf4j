package com.github.sujankumarmitra.service;

import com.github.sujankumarmitra.exception.PdfCreationException;
import com.github.sujankumarmitra.model.ImageFile;
import com.github.sujankumarmitra.model.PdfFile;

public interface ImageToPdfConverter {
    PdfFile convert(ImageFile file, PdfCreateOptions options) throws PdfCreationException;
}
