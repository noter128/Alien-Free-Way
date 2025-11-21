package com.tg04.alienfreeway.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.viewer.Viewer;

import java.io.IOException;

public class RoadViewer extends Viewer<Road> {
    private final BackgroundViewer backgroundViewer;

    public RoadViewer(Road road) {
        super(road);
        this.backgroundViewer = new BackgroundViewer(road);

    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        backgroundViewer.draw(gui);
    }
}

