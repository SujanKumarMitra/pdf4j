package com.github.sujankumarmitra.pdfforj.service;

import com.github.sujankumarmitra.pdfforj.service.PdfServiceFacade;
import com.github.sujankumarmitra.pdfforj.service.impl.DefaultImageToPdfConverter;
import com.github.sujankumarmitra.pdfforj.service.impl.DefaultPdfMerger;
import com.github.sujankumarmitra.pdfforj.service.impl.DefaultPdfUtils;
import com.github.sujankumarmitra.pdfforj.service.impl.DelegatingPdfServiceFacade;

public class ServiceFactory {

    public static PdfServiceFacade pdfServiceFacade() {
        return new DelegatingPdfServiceFacade(
                new DefaultImageToPdfConverter(),
                new DefaultPdfMerger(),
                new DefaultPdfUtils()
        );
    }
}
