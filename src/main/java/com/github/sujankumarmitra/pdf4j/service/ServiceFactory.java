package com.github.sujankumarmitra.pdf4j.service;

import com.github.sujankumarmitra.pdf4j.service.PdfServiceFacade;
import com.github.sujankumarmitra.pdf4j.service.impl.DefaultImageToPdfConverter;
import com.github.sujankumarmitra.pdf4j.service.impl.DefaultPdfMerger;
import com.github.sujankumarmitra.pdf4j.service.impl.DefaultPdfUtils;
import com.github.sujankumarmitra.pdf4j.service.impl.DelegatingPdfServiceFacade;

public class ServiceFactory {

    public static PdfServiceFacade pdfServiceFacade() {
        return new DelegatingPdfServiceFacade(
                new DefaultImageToPdfConverter(),
                new DefaultPdfMerger(),
                new DefaultPdfUtils()
        );
    }
}
