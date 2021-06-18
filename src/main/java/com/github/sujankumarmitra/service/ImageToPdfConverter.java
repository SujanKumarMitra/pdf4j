package com.github.sujankumarmitra.service;

import com.github.sujankumarmitra.model.ImageFile;
import com.github.sujankumarmitra.model.PdfFile;

import java.util.List;

public interface ImageToPdfConverter {

    PdfFile convert(ImageFile file, PdfCreateOptions options);

    PdfFile convertAndMerge(List<ImageFile> files, PdfCreateOptions options);
}
