package com.github.sujankumarmitra.model.builder.impl;

import com.github.sujankumarmitra.model.ImageFile;
import com.github.sujankumarmitra.model.builder.ImageFileBuilder;
import com.github.sujankumarmitra.util.FileUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultImageFileBuilder implements ImageFileBuilder {

    private Path path;

    @Override
    public ImageFileBuilder withLocation(Path location) {
        this.path = location.toAbsolutePath();
        return this;
    }

    @Override
    public ImageFileBuilder withLocation(String path) {
        this.path = Paths.get(path);
        return this;
    }

    @Override
    public ImageFileBuilder withLocation(File file) {
        this.path = file.toPath();
        return this;
    }

    @Override
    public ImageFile build() throws IllegalArgumentException {
        checkValidity(path);
        return new DefaultImageFile(path);
    }

    private void checkValidity(Path path) {
        if(!FileUtils.isImageFile(path))
            throw new IllegalArgumentException("Not a image file");
    }

    private static class DefaultImageFile implements ImageFile {

        private final Path location;

        public DefaultImageFile(Path location) {
            this.location = location;
        }

        @Override
        public Path getLocation() {
            return location;
        }
    }
}
