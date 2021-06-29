package com.github.sujankumarmitra.pdfforj.service;

import java.util.List;

import com.github.sujankumarmitra.pdfforj.exception.PdfCreationException;
import com.github.sujankumarmitra.pdfforj.model.PdfFile;

public interface PdfMerger {
    PdfFile mergePdfs(List<PdfFile> pdfFiles, PdfCreateOptions options) throws PdfCreationException;

}
