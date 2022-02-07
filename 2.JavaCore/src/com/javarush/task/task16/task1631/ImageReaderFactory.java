package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.BmpReader;
import com.javarush.task.task16.task1631.common.ImageReader;
import com.javarush.task.task16.task1631.common.ImageTypes;
import com.javarush.task.task16.task1631.common.JpgReader;
import com.javarush.task.task16.task1631.common.PngReader;

public class ImageReaderFactory {

    public static ImageReader getImageReader(final ImageTypes imageType) {
        ImageReader reader = null;

        if (imageType == null) {
            throw new IllegalArgumentException("Неизвестный тип картинки");
        }

        switch (imageType) {
            case PNG:
                reader = new PngReader();
                break;
            case BMP:
                reader = new BmpReader();
                break;
            case JPG:
                reader = new JpgReader();
                break;
        }
        return reader;
    }

}
