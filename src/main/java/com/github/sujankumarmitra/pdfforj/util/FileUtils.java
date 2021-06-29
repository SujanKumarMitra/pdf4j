package com.github.sujankumarmitra.pdfforj.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public abstract class FileUtils {

    public static boolean isPdfFile(Path path) {
        return isValidFile(path) &&
                path.toString().endsWith(".pdf");
    }

    public static boolean isImageFile(Path path) {
        return isValidFile(path) &&
                Stream.of(".jpg",
                        ".jpeg",
                        ".tif",
                        ".tiff",
                        ".gif",
                        ".bmp",
                        ".png")
                        .anyMatch(extension -> path.toString().endsWith(extension));
    }

    public static boolean isValidFile(Path path) {
        return Files.exists(path) &&
                !Files.isDirectory(path);
    }
}
