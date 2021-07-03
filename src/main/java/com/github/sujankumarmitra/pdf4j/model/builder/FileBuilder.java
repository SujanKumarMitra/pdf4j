package com.github.sujankumarmitra.pdf4j.model.builder;

import java.io.File;
import java.nio.file.Path;

public interface FileBuilder<B extends FileBuilder<B,F>, F> {

    B withLocation(Path location);

    B withLocation(String path);

    B withLocation(File file);

    F build() throws IllegalArgumentException;
}
