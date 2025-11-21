package com.tg04.alienfreeway.viewer.menu;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.menu.Menu;
import com.tg04.alienfreeway.viewer.Viewer;

public abstract class MenuViewer extends Viewer<Menu> {
    private final String title;
    private final Position titlePosition;
    private final String titleColor;
    private final String highlightColor;

    public MenuViewer(Menu menu, String title, Position titlePosition, String titleColor, String highlightColor) {
        super(menu);
        this.title = title;
        this.titlePosition = titlePosition;
        this.titleColor = titleColor;
        this.highlightColor = highlightColor;
    }

    @Override
    public void drawElements(GUI gui) {
        Menu menu = getModel();
        drawTitle(gui);
        if(menu.getNumberEntries() == 0) {
            return;
        }
        drawOptions(gui);
    }

    protected void drawTitle(GUI gui) {
        gui.drawTitleWithHighlight(titlePosition, title, titleColor, highlightColor);
    }

    private void drawOptions(GUI gui) {
        Menu menu = getModel();
        int y = getInitialY();
        for (int i = 0; i < menu.getNumberEntries(); i++) {
            String option = menu.getEntry(i);
            String color = menu.isSelected(i) ? "#FFDD00" : "#AAAAAA";
            gui.drawText(new Position(getOptionsX(), y), (menu.isSelected(i) ? "-> " : "   ") + option, color);
            y += 2;
        }
    }

    protected abstract int getInitialY();
    protected abstract int getOptionsX();
}






