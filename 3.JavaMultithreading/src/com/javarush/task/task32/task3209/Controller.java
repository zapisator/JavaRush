package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class Controller {

    private final View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        final View view = new View();
        final Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public void init() {
        createNewDocument();
    }

    public void exit() {
        System.exit(0);
    }

    public void resetDocument() {
        final UndoListener undoListener = view.getUndoListener();

        if (document != null) {
            document.removeUndoableEditListener(undoListener);
        }
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(undoListener);
        view.update();
    }

    public void setPlainText(String text) {
        final StringReader reader = new StringReader(text);

        resetDocument();
        try {
            new HTMLEditorKit().read(reader, (Document) document, 0);
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        final StringWriter writer = new StringWriter();

        try {
            new HTMLEditorKit().write(writer, document, 0, document.getLength());
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();

        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());

        final int choice = fileChooser.showOpenDialog(view);
        if (choice == JFileChooser.APPROVE_OPTION) {
            final File file = fileChooser.getSelectedFile();

            currentFile = file;
            resetDocument();
            view.setTitle(file.getName());


            try (final FileReader reader = new FileReader(currentFile)){
                new HTMLEditorKit().read(reader, document, 0);
                view.resetUndo();
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument() {
        view.selectHtmlTab();

        if (currentFile != null) {
            try (final FileWriter writer = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        } else {
            saveDocumentAs();
        }
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();

        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());

        final int choice = fileChooser.showSaveDialog(view);
        if (choice == JFileChooser.APPROVE_OPTION) {
            final File file = fileChooser.getSelectedFile();

            view.setTitle(file.getName());
            currentFile = file;

            try (final FileWriter writer = new FileWriter(currentFile)){
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public HTMLDocument getDocument() {
        return document;
    }
}
