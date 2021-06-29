package com.github.sujankumarmitra.service;

import com.github.sujankumarmitra.service.PdfServiceFacade;
import com.github.sujankumarmitra.service.impl.DefaultImageToPdfConverter;
import com.github.sujankumarmitra.service.impl.DefaultPdfMerger;
import com.github.sujankumarmitra.service.impl.DefaultPdfUtils;
import com.github.sujankumarmitra.service.impl.DelegatingPdfServiceFacade;

public class ServiceFactory {

    public static PdfServiceFacade pdfServiceFacade() {
        return new DelegatingPdfServiceFacade(
                new DefaultImageToPdfConverter(),
                new DefaultPdfMerger(),
                new DefaultPdfUtils()
        );
    }
}
