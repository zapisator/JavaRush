package com.javarush.task.task32.task3209;

import java.io.File;
import java.util.Locale;
import javax.swing.filechooser.FileFilter;

public class HTMLFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        return file.isDirectory() || hasHtmlExtension(file);
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }

    private boolean hasHtmlExtension(final File file) {
        final String extension = file.getName().substring(file.getName().indexOf(".")).toLowerCase(
                Locale.ROOT);

        return extension.equals(".html") || extension.equals(".htm");
    }

}
