package com.github.sujankumarmitra.pdf4j.service.impl;

import com.github.sujankumarmitra.pdf4j.service.ImageToPdfConverter;
import com.github.sujankumarmitra.pdf4j.service.PdfMerger;
import com.github.sujankumarmitra.pdf4j.service.PdfServiceFacade;
import com.github.sujankumarmitra.pdf4j.service.PdfSplitter;
import com.github.sujankumarmitra.pdf4j.service.PdfUtils;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

@AllArgsConstructor
public class DelegatingPdfServiceFacade implements PdfServiceFacade {

    @Delegate
    private final ImageToPdfConverter imageToPdfConverter;
    @Delegate
    private final PdfMerger pdfMerger;
    @Delegate
    private final PdfUtils pdfUtils;
    @Delegate
    private final PdfSplitter pdfSplitter;

}
