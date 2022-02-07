package com.javarush.task.task35.task3513;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
        final Controller controller = new Controller(new Model());
        final JFrame game = new JFrame();

        game.setTitle("2048");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(450, 500);
        game.setResizable(false);
        game.add(controller.getView());
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
