package com.github.sujankumarmitra.launcher;

import com.github.sujankumarmitra.model.ImageFile;
import com.github.sujankumarmitra.model.PdfFile;
import com.github.sujankumarmitra.model.builder.FileBuilders;
import com.github.sujankumarmitra.service.PdfCreateOptions;
import com.github.sujankumarmitra.service.ServiceFactory;
import com.github.sujankumarmitra.service.impl.DefaultPdfCreateOptions;
import org.apache.commons.cli.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CommandLineLauncher {
    public static void main(String[] args) throws ParseException {
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
        }

        else {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar PdfForJ.jar", options);
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
