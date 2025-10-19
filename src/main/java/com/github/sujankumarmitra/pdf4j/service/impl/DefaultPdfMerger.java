package com.github.sujankumarmitra.pdf4j.service.impl;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;

import com.github.sujankumarmitra.pdf4j.exception.PdfCreationException;
import com.github.sujankumarmitra.pdf4j.model.PdfFile;
import com.github.sujankumarmitra.pdf4j.model.builder.FileBuilders;
import com.github.sujankumarmitra.pdf4j.service.PdfCreateOptions;
import com.github.sujankumarmitra.pdf4j.service.PdfMerger;
import com.github.sujankumarmitra.pdf4j.util.PdfAssertions;

import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DefaultPdfMerger implements PdfMerger {

    @Override
    public PdfFile mergePdfs(List<PdfFile> pdfFiles, PdfCreateOptions options) throws PdfCreationException {
        PdfAssertions.assertLegalOptions(options);

        PDDocument mergedDocument = pdfFiles
                .stream()
                .sequential()
                .map(this::fileToPDDoc)
                .map(PDDocument::getPages)
                .flatMap(this::pageTreeToPageStream)
                .collect(PDDocument::new,
                        PDDocument::addPage,
                        this::mergePDDocs);
        try {
            mergedDocument.save(Files.newOutputStream(options.getDestination()));
            mergedDocument.close();
        } catch (Throwable th) {
            throw new PdfCreationException(th.getMessage());
        }


        return FileBuilders.newPdfFileBuilder()
                .withLocation(options.getDestination())
                .build();
    }


    private  Stream<? extends PDPage> pageTreeToPageStream(PDPageTree pdfPageTree) {
        return StreamSupport.stream(pdfPageTree.spliterator(), false);
    }

    private PDDocument fileToPDDoc(PdfFile pdfFile) {
        try {
            return Loader.loadPDF(new RandomAccessReadBufferedFile(pdfFile.getLocation()));
        } catch (Throwable th) {
            throw new PdfCreationException(th.getMessage());
        }
    }

    private void mergePDDocs(PDDocument doc1, PDDocument doc2) {
        doc2.getPages().forEach(doc1::addPage);
    }
}
