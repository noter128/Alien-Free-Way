package com.tg04.alienfreeway.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.viewer.Viewer;

public class PlayerViewer extends Viewer<Player> {
    public PlayerViewer(Player player) {
        super(player);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawPlayer(getModel().getPosition());
    }
}