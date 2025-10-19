package com.github.sujankumarmitra.pdf4j.service.impl;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;

import com.github.sujankumarmitra.pdf4j.exception.PdfCreationException;
import com.github.sujankumarmitra.pdf4j.model.PdfFile;
import com.github.sujankumarmitra.pdf4j.service.PdfUtils;

import java.io.IOException;
import java.nio.file.Files;

public class DefaultPdfUtils implements PdfUtils {
    @Override
    public void reversePages(PdfFile file) throws PdfCreationException {
        PDDocument pdf;
        try {
            pdf = Loader.loadPDF(file.getLocation().toFile());
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
            pdf.save(Files.newOutputStream(file.getLocation()));
            pdf.close();
        } catch (Throwable th) {
            throw new PdfCreationException(th);
        }


    }
}
