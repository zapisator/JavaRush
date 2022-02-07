package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class TextEditMenuListener implements MenuListener {

    private final View view;

    public TextEditMenuListener(View view) {
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent menuEvent) {
        final JMenu menu = (JMenu)menuEvent.getSource();

        Arrays.stream(menu.getMenuComponents())
                .forEach(component -> component.setEnabled(view.isHtmlTabSelected()));
    }

    @Override
    public void menuDeselected(MenuEvent menuEvent) {

    }

    @Override
    public void menuCanceled(MenuEvent menuEvent) {

    }
}
