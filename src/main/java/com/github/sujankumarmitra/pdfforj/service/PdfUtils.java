package com.github.sujankumarmitra.pdfforj.service;

import com.github.sujankumarmitra.pdfforj.exception.PdfCreationException;
import com.github.sujankumarmitra.pdfforj.model.PdfFile;

public interface PdfUtils {
    void reversePages(PdfFile file) throws PdfCreationException;
}
