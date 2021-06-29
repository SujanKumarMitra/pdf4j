package com.github.sujankumarmitra.pdfforj.model.builder;

import com.github.sujankumarmitra.pdfforj.model.builder.impl.DefaultImageFileBuilder;
import com.github.sujankumarmitra.pdfforj.model.builder.impl.DefaultPdfFileBuilder;

public abstract class FileBuilders {
    public static PdfFileBuilder newPdfFileBuilder() {
        return new DefaultPdfFileBuilder();
    }

    public static ImageFileBuilder newImageFileBuilder() {
        return new DefaultImageFileBuilder();
    }
}
