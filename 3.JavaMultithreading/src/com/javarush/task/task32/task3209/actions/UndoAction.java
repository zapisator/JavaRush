package com.javarush.task.task32.task3209.actions;

import com.javarush.task.task32.task3209.View;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class UndoAction extends AbstractAction {

    private final View view;

    public UndoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.undo();
    }
}
