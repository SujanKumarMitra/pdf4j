package com.github.sujankumarmitra.pdf4j.service.impl;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;

import com.github.sujankumarmitra.pdf4j.exception.PdfCreationException;
import com.github.sujankumarmitra.pdf4j.model.PdfFile;
import com.github.sujankumarmitra.pdf4j.service.PdfUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class DefaultPdfUtils implements PdfUtils {
    @Override
    public void reversePages(PdfFile file) throws PdfCreationException {
        PDDocument pdf;
        try {
            pdf = Loader.loadPDF(new RandomAccessReadBufferedFile(file.getLocation()));
        } catch (IOException e) {
            throw new PdfCreationException(e);
        }

        PDPageTree pages = pdf.getPages();
        int left = 0;
        int right = pages.getCount() - 1;

        while(left < right) {
            PDPage first = pages.get(left);
            PDPage last = pages.get(right);

            pages.remove(first);
            pages.remove(last);

           int newLeft = left;
           int newRight = right-1;

           pages.insertBefore(last,pages.get(newLeft));
           pages.insertAfter(first,pages.get(newRight));

            left++;
            right--;

        }

        try {
            Path tempFile = Files.createTempFile("pdfbox", ".pdf");
            pdf.save(Files.newOutputStream(tempFile, StandardOpenOption.WRITE));
            pdf.close();
            Files.move(tempFile, file.getLocation(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Throwable th) {
            throw new PdfCreationException(th);
        }


    }
}
