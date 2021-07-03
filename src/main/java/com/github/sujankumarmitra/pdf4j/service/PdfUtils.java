package com.github.sujankumarmitra.pdf4j.service;

import com.github.sujankumarmitra.pdf4j.exception.PdfCreationException;
import com.github.sujankumarmitra.pdf4j.model.PdfFile;

public interface PdfUtils {
    void reversePages(PdfFile file) throws PdfCreationException;
}
