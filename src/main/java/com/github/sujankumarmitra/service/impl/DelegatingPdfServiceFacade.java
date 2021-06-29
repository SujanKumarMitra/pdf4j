package com.github.sujankumarmitra.service.impl;

import com.github.sujankumarmitra.service.ImageToPdfConverter;
import com.github.sujankumarmitra.service.PdfMerger;
import com.github.sujankumarmitra.service.PdfServiceFacade;
import com.github.sujankumarmitra.service.PdfUtils;
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
