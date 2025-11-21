package com.tg04.alienfreeway.viewer.menu;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.menu.Menu;

public class ShopMenuViewer extends MenuViewer {
    private final Player player;

    public ShopMenuViewer(Menu menu, Player player) {
        super(menu, "SHOP MENU", new Position(34, 7), "#00FF00", "#FF0000");
        this.player = player;
    }

    @Override
    protected int getInitialY() {
        return 9;
    }

    @Override
    protected int getOptionsX() {
        return 25;
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawBox(new Position(19, 7), 40, 11, "#FFFFFF");
        super.drawElements(gui);
        gui.drawText(new Position(34, 18), "Wallet: " + player.getWallet(), "#00FF00");
    }
}








