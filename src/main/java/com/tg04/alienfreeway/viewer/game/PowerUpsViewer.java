package com.tg04.alienfreeway.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.PowerUps;
import com.tg04.alienfreeway.viewer.Viewer;

import java.util.List;

public class PowerUpsViewer extends Viewer<List<PowerUps>> {
    private static final int HUD_HEIGHT = 1;
    private static final int LAST_LINE = 22;

    public PowerUpsViewer(List<PowerUps> powerUps) {
        super(powerUps);
    }

    @Override
    public void drawElements(GUI gui) {
        for (PowerUps powerUp : getModel()) {
            int y = powerUp.getPosition().getY();
            if (y > HUD_HEIGHT && y < LAST_LINE) {
                gui.drawPowerUp(powerUp.getPosition(), powerUp.getType());
            }
        }
    }
}

