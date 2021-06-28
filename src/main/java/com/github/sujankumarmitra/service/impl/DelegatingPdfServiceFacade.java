package com.github.sujankumarmitra.service.impl;

import com.github.sujankumarmitra.exception.PdfCreationException;
import com.github.sujankumarmitra.model.ImageFile;
import com.github.sujankumarmitra.model.PdfFile;
import com.github.sujankumarmitra.service.*;

import java.util.List;

public class DelegatingPdfServiceFacade implements PdfServiceFacade {

    private final ImageToPdfConverter imageToPdfConverter;
    private final PdfMerger pdfMerger;
    private final PdfUtils pdfUtils;

    public DelegatingPdfServiceFacade(ImageToPdfConverter imageToPdfConverter, PdfMerger pdfMerger, PdfUtils pdfUtils) {
        this.imageToPdfConverter = imageToPdfConverter;
        this.pdfMerger = pdfMerger;
        this.pdfUtils = pdfUtils;
    }

    @Override
    public PdfFile convert(ImageFile file, PdfCreateOptions options) throws PdfCreationException {
        return imageToPdfConverter.convert(file, options);
    }

    @Override
    public PdfFile mergePdfs(List<PdfFile> pdfFiles, PdfCreateOptions options) throws PdfCreationException {
        return pdfMerger.mergePdfs(pdfFiles, options);
    }

    @Override
    public void reversePages(PdfFile file) throws PdfCreationException {
        pdfUtils.reversePages(file);
    }
}
