package com.github.sujankumarmitra.pdfforj.service.impl;

import com.github.sujankumarmitra.pdfforj.service.ImageToPdfConverter;
import com.github.sujankumarmitra.pdfforj.service.PdfMerger;
import com.github.sujankumarmitra.pdfforj.service.PdfServiceFacade;
import com.github.sujankumarmitra.pdfforj.service.PdfUtils;

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

}
