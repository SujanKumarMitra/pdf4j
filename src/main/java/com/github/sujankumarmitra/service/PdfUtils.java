package com.github.sujankumarmitra.service;

import com.github.sujankumarmitra.exception.PdfCreationException;
import com.github.sujankumarmitra.model.PdfFile;

public interface PdfUtils {
    void reversePages(PdfFile file) throws PdfCreationException;
}
