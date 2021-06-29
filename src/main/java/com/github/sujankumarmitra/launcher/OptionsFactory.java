package com.github.sujankumarmitra.launcher;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsFactory {
    public static Options getOptions() {
        Options options = new Options();

        options.addOption(getMergeOption());
        options.addOption(getImageConvertOption());
        options.addOption(getConvertAndMergeImageOption());

        return options;
    }

    private static Option getImageConvertOption() {
        return Option.builder("c")
                .longOpt("convert")
                .numberOfArgs(2)
                .argName("source destination")
                .desc("Convert an image to PDF")
                .build();
    }

    private static Option getMergeOption() {
        return Option.builder("m")
                .longOpt("merge")
                .numberOfArgs(1)
                .argName("destination files...")
                .desc("Merge PDFs into a single PDF")
                .build();
    }

    private static Option getConvertAndMergeImageOption() {
        return Option.builder("cm")
               .longOpt("convert-and-merge")
               .desc("Convert images to PDF and merge them")
               .argName("destination files..")
               .numberOfArgs(1)
               .build();

    }
}
