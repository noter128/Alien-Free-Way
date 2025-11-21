package com.tg04.alienfreeway.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.viewer.Viewer;

public class BackgroundViewer extends Viewer<Road> {
    public BackgroundViewer(Road road) {
        super(road);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawBackground(
                80,
                24,
                getModel().getPlayer().getHealth(),
                getModel().getPlayer().getWallet(),
                getModel().getPlayer().getCurrentShotType(),
                getModel().getPlayer().getScore(),
                getModel().getPlayer().hasExtraLife()
        );
    }
}

