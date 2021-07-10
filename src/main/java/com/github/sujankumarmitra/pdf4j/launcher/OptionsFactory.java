package com.github.sujankumarmitra.pdf4j.launcher;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsFactory {
    public static Options getOptions() {
        Options options = new Options();

        options.addOption(getMergeOption());
        options.addOption(getImageConvertOption());
        options.addOption(getConvertAndMergeImageOption());
        options.addOption(getReversePagesOption());

        return options;
    }

    private static Option getImageConvertOption() {
        return Option.builder("c")
                .longOpt("convert")
                .numberOfArgs(2)
                .argName("sourcePath destinationPath")
                .desc("Convert an image to PDF")
                .build();
    }

    private static Option getMergeOption() {
        return Option.builder("m")
                .longOpt("merge")
                .numberOfArgs(1)
                .argName("destinationPath filePaths...")
                .desc("Merge PDFs into a single PDF")
                .build();
    }

    private static Option getConvertAndMergeImageOption() {
        return Option.builder("cm")
               .longOpt("convert-and-merge")
               .desc("Convert images to PDF and merge them")
               .argName("destinationPath filePath...")
               .numberOfArgs(1)
               .build();

    }

    private static Option getReversePagesOption() {
        return Option.builder("r")
                .longOpt("reverse")
                .desc("Reverse the order of page in PDF")
                .numberOfArgs(1)
                .argName("sourcePath")
                .build();
    }
}
