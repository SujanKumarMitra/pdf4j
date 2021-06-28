package com.github.sujankumarmitra.service;

import com.github.sujankumarmitra.exception.PdfCreationException;
import com.github.sujankumarmitra.model.PdfFile;

import java.util.List;

public interface PdfMerger {
    PdfFile mergePdfs(List<PdfFile> pdfFiles, PdfCreateOptions options) throws PdfCreationException;

}
