package com.github.sujankumarmitra.pdf4j.service.impl;

import com.github.sujankumarmitra.pdf4j.exception.PdfCreationException;
import com.github.sujankumarmitra.pdf4j.model.PdfFile;
import com.github.sujankumarmitra.pdf4j.service.PdfCreateOptions;
import com.github.sujankumarmitra.pdf4j.service.PdfSplitter;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DefaultPdfSplitter implements PdfSplitter {
    
    @Override
    public void splitPdf(PdfFile inputFile, PdfCreateOptions options) throws PdfCreationException {
        try {
            PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile(inputFile.getLocation()));
            
            Path outputDir = options.getDestination();
            Files.createDirectories(outputDir);
            
            int pageCount = document.getNumberOfPages();
            
            for (int i = 0; i < pageCount; i++) {
                PDDocument singlePageDoc = new PDDocument();
                singlePageDoc.addPage(document.getPage(i));
                
                Path outputFile = outputDir.resolve((i + 1) + ".pdf");
                singlePageDoc.save(outputFile.toFile());
                singlePageDoc.close();
                
                System.out.println("Created " + outputFile.getFileName());
            }
            
            document.close();
            
        } catch (IOException e) {
            throw new PdfCreationException(e);
        }
    }
}
