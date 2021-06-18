package com.github.sujankumarmitra.service;

import com.github.sujankumarmitra.model.PdfFile;

import java.util.List;

public interface PdfService {

    PdfFile mergePdfs(List<PdfFile> pdfFiles, PdfCreateOptions options);

    void reversePages(PdfFile file);
}
