package com.github.sujankumarmitra.pdfforj.service.impl;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.github.sujankumarmitra.pdfforj.exception.PdfCreationException;
import com.github.sujankumarmitra.pdfforj.model.ImageFile;
import com.github.sujankumarmitra.pdfforj.model.PdfFile;
import com.github.sujankumarmitra.pdfforj.model.builder.FileBuilders;
import com.github.sujankumarmitra.pdfforj.service.ImageToPdfConverter;
import com.github.sujankumarmitra.pdfforj.service.PdfCreateOptions;
import com.github.sujankumarmitra.pdfforj.util.PdfAssertions;

import java.nio.file.Files;
import java.util.List;

public class DefaultImageToPdfConverter implements ImageToPdfConverter {

    @Override
    public PdfFile convert(ImageFile file, PdfCreateOptions options) throws PdfCreationException {
        PdfAssertions.assertLegalOptions(options);

        PDDocument doc = new PDDocument();
        appendImageToDoc(doc, file);

        try {
            doc.save(Files.newOutputStream(options.getDestination()));
            doc.close();
        } catch (Throwable th) {
            throw new PdfCreationException(th);
        }

        return FileBuilders.newPdfFileBuilder()
                .withLocation(options.getDestination())
                .build();
    }

    @Override
    public PdfFile convertAndMerge(List<ImageFile> files, PdfCreateOptions options) throws PdfCreationException {
        PDDocument document = files.stream()
                .sequential()
                .collect(PDDocument::new,
                        this::appendImageToDoc,
                        this::mergeDocs);
        try {
            document.save(Files.newOutputStream(options.getDestination()));
            document.close();
        } catch (Throwable th) {
            throw new PdfCreationException(th);
        }

        return FileBuilders.newPdfFileBuilder()
                .withLocation(options.getDestination())
                .build();
    }

    private void appendImageToDoc(PDDocument doc, ImageFile file) {
        PDImageXObject image;

        try {
            image = PDImageXObject.createFromFileByContent(file.getLocation().toFile(), doc);
        } catch (Throwable th) {
            throw new PdfCreationException(th);
        }
        PDRectangle pageSize = PDRectangle.A4;

        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        float pageWidth = pageSize.getWidth();
        float pageHeight = pageSize.getHeight();
        float ratio = Math.min(pageWidth / originalWidth, pageHeight / originalHeight);
        float scaledWidth = originalWidth * ratio;
        float scaledHeight = originalHeight * ratio;
        float x = (pageWidth - scaledWidth) / 2;
        float y = (pageHeight - scaledHeight) / 2;

        PDPage page = new PDPage(pageSize);
        doc.addPage(page);
        try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
            contents.drawImage(image, x, y, scaledWidth, scaledHeight);
        } catch (Throwable th) {
            throw new PdfCreationException(th);
        }
    }

    private void mergeDocs(PDDocument doc1, PDDocument doc2) {
        doc2.getPages().forEach(doc1::addPage);
    }
}
