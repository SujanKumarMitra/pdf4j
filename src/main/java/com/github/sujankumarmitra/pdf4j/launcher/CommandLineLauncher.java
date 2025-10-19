package com.github.sujankumarmitra.pdf4j.launcher;

import com.github.sujankumarmitra.pdf4j.model.ImageFile;
import com.github.sujankumarmitra.pdf4j.model.PdfFile;
import com.github.sujankumarmitra.pdf4j.model.builder.FileBuilders;
import com.github.sujankumarmitra.pdf4j.service.PdfCreateOptions;
import com.github.sujankumarmitra.pdf4j.service.ServiceFactory;
import com.github.sujankumarmitra.pdf4j.service.impl.DefaultPdfCreateOptions;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.help.HelpFormatter;

public class CommandLineLauncher {
    public static void main(String[] args) throws Exception {
        CommandLineParser parser = new DefaultParser();
        Options options = OptionsFactory.getOptions();
        CommandLine parseResult = parser.parse(options, args);

        if (parseResult.hasOption("m")) {
            String destination = args[1];

            List<PdfFile> pdfFiles = getPdfFiles(args);
            PdfCreateOptions createOptions = getCreateOptions(destination);

            PdfFile mergePdfs = ServiceFactory.pdfServiceFacade()
                    .mergePdfs(pdfFiles, createOptions);

            System.out.println("PDF saved in "+ mergePdfs.getLocation());
        } else if (parseResult.hasOption("c")) {
            String imageFileName = args[1];
            String destination = args[2];

            ImageFile imageFile = getImageFile(imageFileName);
            PdfCreateOptions createOptions = getCreateOptions(destination);

            PdfFile pdfFile = ServiceFactory.pdfServiceFacade()
                    .convert(imageFile, createOptions);

            System.out.println("PDF saved in "+ pdfFile.getLocation());
        } else if (parseResult.hasOption("cm")) {
            String destination = args[1];

            List<ImageFile> imageFiles = getImageFiles(args);
            PdfCreateOptions createOptions = getCreateOptions(destination);

            PdfFile mergedPdf = ServiceFactory.pdfServiceFacade()
                    .convertAndMerge(imageFiles, createOptions);

            System.out.println("PDF saved in " + mergedPdf.getLocation());
        } else if(parseResult.hasOption("r")) {
            String destination = args[1];
            PdfFile pdfFile = FileBuilders.newPdfFileBuilder()
                    .withLocation(destination)
                    .build();
            ServiceFactory.pdfServiceFacade()
                    .reversePages(pdfFile);

            System.out.println("PDF pages are reversed");
        } else if(parseResult.hasOption("s")) {
            String inputFile = args[1];
            String outputDirectory = args[2];
            
            PdfFile pdfFile = FileBuilders.newPdfFileBuilder()
                    .withLocation(inputFile)
                    .build();
            PdfCreateOptions createOptions = getCreateOptions(outputDirectory);
            
            ServiceFactory.pdfServiceFacade()
                    .splitPdf(pdfFile, createOptions);
                    
            System.out.println("PDF split into individual pages in " + outputDirectory);
        }

        else {
            HelpFormatter formatter = HelpFormatter.builder().get();
            formatter.printOptions(options);
        }

    }

    private static List<ImageFile> getImageFiles(String[] args) {
        List<ImageFile> imageFiles = new ArrayList<>();

        for (int i = 2; i < args.length; i++) {
            imageFiles.add(FileBuilders
                    .newImageFileBuilder()
                    .withLocation(args[i])
                    .build());
        }

        return imageFiles;
    }

    private static PdfCreateOptions getCreateOptions(String destination) {
        return new DefaultPdfCreateOptions(
                Paths.get(destination), true);
    }

    private static ImageFile getImageFile(String imageFileName) {
        return FileBuilders
                .newImageFileBuilder()
                .withLocation(imageFileName)
                .build();
    }

    private static List<PdfFile> getPdfFiles(String[] args) {
        List<PdfFile> pdfFiles = new ArrayList<>();

        for (int i = 2; i < args.length; i++) {
            pdfFiles.add(FileBuilders
                    .newPdfFileBuilder()
                    .withLocation((String) args[i])
                    .build());
        }
        return pdfFiles;
    }
}
