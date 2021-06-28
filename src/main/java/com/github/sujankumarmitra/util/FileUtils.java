package com.github.sujankumarmitra.util;

import java.nio.file.Files;
import java.nio.file.Path;

public abstract class FileUtils {

    public static boolean isPdfFile(Path path) {
        return Files.exists(path) &&
                !Files.isDirectory(path) &&
                path.toString().endsWith(".pdf");
    }
}
