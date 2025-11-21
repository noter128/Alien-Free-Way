package com.tg04.alienfreeway.viewer.menu;

import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.menu.Menu;

public class MainMenuViewer extends MenuViewer {
    public MainMenuViewer(Menu menu) {
        super(menu, "[ ALIEN FREE WAY ]", new Position(30, 7), "#FFFFFF", "#FF0000");
    }

    @Override
    protected int getInitialY() {
        return 10;
    }

    @Override
    protected int getOptionsX() {
        return 34;
    }
}
