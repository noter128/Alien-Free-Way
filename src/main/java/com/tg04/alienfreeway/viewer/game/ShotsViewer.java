package com.tg04.alienfreeway.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Shots;
import com.tg04.alienfreeway.viewer.Viewer;

import java.util.List;

public class ShotsViewer extends Viewer<List<Shots>> {
    public ShotsViewer(List<Shots> shots) {
        super(shots);
    }

    @Override
    public void drawElements(GUI gui) {
        for (Shots shot : getModel()) {
            gui.drawShot(shot.getPosition(), shot.getType());
        }
    }
}
