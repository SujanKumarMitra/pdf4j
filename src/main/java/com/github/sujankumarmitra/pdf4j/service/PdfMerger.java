package com.github.sujankumarmitra.pdf4j.service;

import java.util.List;

import com.github.sujankumarmitra.pdf4j.exception.PdfCreationException;
import com.github.sujankumarmitra.pdf4j.model.PdfFile;

public interface PdfMerger {
    PdfFile mergePdfs(List<PdfFile> pdfFiles, PdfCreateOptions options) throws PdfCreationException;

}
