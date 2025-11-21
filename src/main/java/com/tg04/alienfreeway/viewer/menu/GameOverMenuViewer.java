package com.tg04.alienfreeway.viewer.menu;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.menu.Menu;

public class GameOverMenuViewer extends MenuViewer {
    private final Player player;

    public GameOverMenuViewer(Menu menu, Player player) {
        super(menu, "!!! GAME OVER !!!", new Position(30, 7), "#FF0000", "#FFFFFF");
        this.player = player;
    }

    @Override
    protected int getInitialY() {
        return 10;
    }

    @Override
    protected int getOptionsX() {
        return 30;
    }

    @Override
    public void drawElements(GUI gui) {
        super.drawElements(gui);
        gui.drawText(new Position(30, 17), "High Score: " + player.getScore(), "#00FF00");
    }
}
