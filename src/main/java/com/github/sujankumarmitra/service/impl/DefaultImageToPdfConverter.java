package com.github.sujankumarmitra.service.impl;

import com.github.sujankumarmitra.exception.PdfCreationException;
import com.github.sujankumarmitra.model.ImageFile;
import com.github.sujankumarmitra.model.PdfFile;
import com.github.sujankumarmitra.model.builder.FileBuilders;
import com.github.sujankumarmitra.service.ImageToPdfConverter;
import com.github.sujankumarmitra.service.PdfCreateOptions;
import com.github.sujankumarmitra.util.PdfAssertions;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.nio.file.Files;

public class DefaultImageToPdfConverter implements ImageToPdfConverter {

    @Override
    public PdfFile convert(ImageFile file, PdfCreateOptions options) throws PdfCreationException {
        PdfAssertions.assertLegalOptions(options);

        PDDocument doc = new PDDocument();
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

        try {
            doc.save(Files.newOutputStream(options.getDestination()));
        } catch (Throwable th) {
            throw new PdfCreationException(th);
        }

        return FileBuilders.newPdfFileBuilder()
                .withLocation(options.getDestination())
                .build();
    }
}
