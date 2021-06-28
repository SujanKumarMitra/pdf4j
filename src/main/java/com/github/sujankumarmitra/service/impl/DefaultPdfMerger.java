package com.github.sujankumarmitra.service.impl;

import com.github.sujankumarmitra.exception.InvalidPdfFileException;
import com.github.sujankumarmitra.exception.PdfException;
import com.github.sujankumarmitra.exception.PdfFileAlreadyExistsException;
import com.github.sujankumarmitra.model.PdfFile;
import com.github.sujankumarmitra.model.builder.FileBuilders;
import com.github.sujankumarmitra.service.PdfCreateOptions;
import com.github.sujankumarmitra.service.PdfMerger;
import com.github.sujankumarmitra.util.PdfFileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DefaultPdfMerger implements PdfMerger {

    @Override
    public PdfFile mergePdfs(List<PdfFile> pdfFiles, PdfCreateOptions options) throws PdfException {
        assertValidOptions(options);

        PDDocument mergedDocument = pdfFiles
                .stream()
                .sequential()
                .map(this::fileToPDDoc)
                .map(pdfDoc -> pdfDoc.getPages())
                .flatMap(this::pageTreeToPageStream)
                .collect(PDDocument::new,
                        (pdfDoc, pdfPage) -> pdfDoc.addPage(pdfPage),
                        this::mergePDDDocs);
        try {
            mergedDocument.save(options.getDestination().toFile());
        } catch (Throwable th) {
            throw new InvalidPdfFileException(th.getMessage());
        }

        return FileBuilders.newPdfFileBuilder()
                .withLocation(options.getDestination())
                .build();
    }

    private void assertValidOptions(PdfCreateOptions options) {
        Path path = options.getDestination();
        if (!PdfFileUtils.isPdfFile(path)) {
            throw new InvalidPdfFileException("Not a PDF file");
        }

        if (Files.exists(path) && !options.overwriteIfExists()) {
            throw new PdfFileAlreadyExistsException();
        }
    }


    private  Stream<? extends PDPage> pageTreeToPageStream(PDPageTree pdfPageTree) {
        return StreamSupport.stream(pdfPageTree.spliterator(), false);
    }

    private PDDocument fileToPDDoc(PdfFile pdfFile) {
        try {
            return PDDocument.load(pdfFile.getLocation().toFile());
        } catch (Throwable th) {
            throw new InvalidPdfFileException(th.getMessage());
        }
    }

    private void mergePDDDocs(PDDocument doc1, PDDocument doc2) {
        doc2.getPages().forEach(doc1::addPage);
    }
}
