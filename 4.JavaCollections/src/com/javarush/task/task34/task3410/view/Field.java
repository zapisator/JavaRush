package com.javarush.task.task34.task3410.view;

import static com.javarush.task.task34.task3410.model.Direction.DOWN;
import static com.javarush.task.task34.task3410.model.Direction.LEFT;
import static com.javarush.task.task34.task3410.model.Direction.RIGHT;
import static com.javarush.task.task34.task3410.model.Direction.UP;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_R;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import static javafx.scene.input.KeyCode.R;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class Field extends JPanel {

    private final View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        final KeyHandler handler = new KeyHandler();

        addKeyListener(handler);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        view.getGameObjects().getAll()
                .forEach(gameObject -> gameObject.draw(g));
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent key) {

            switch (key.getKeyCode()) {
                case VK_LEFT:
                    eventListener.move(LEFT);
                    break;
                case VK_RIGHT:
                    eventListener.move(RIGHT);
                    break;
                case VK_UP:
                    eventListener.move(UP);
                    break;
                case VK_DOWN:
                    eventListener.move(DOWN);
                    break;
                case VK_R:
                    eventListener.restart();
                    break;
                default:
            }
        }
    }

}
