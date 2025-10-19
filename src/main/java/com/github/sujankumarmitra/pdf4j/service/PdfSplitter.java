package com.github.sujankumarmitra.pdf4j.service;

import com.github.sujankumarmitra.pdf4j.exception.PdfCreationException;
import com.github.sujankumarmitra.pdf4j.model.PdfFile;

public interface PdfSplitter {
    void splitPdf(PdfFile inputFile, PdfCreateOptions options) throws PdfCreationException;
}
