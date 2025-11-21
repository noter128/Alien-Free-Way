package com.tg04.alienfreeway.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.viewer.Viewer;

import java.io.IOException;

public class RoadElementsViewer extends Viewer<Road> {
    private final PlayerViewer playerViewer;
    private final EnemyViewer enemyViewer;
    private final PowerUpsViewer powerUpsViewer;
    private final ShotsViewer shotsViewer;

    public RoadElementsViewer(Road road) {
        super(road);
        this.playerViewer = new PlayerViewer(road.getPlayer());
        this.enemyViewer = new EnemyViewer(road.getEnemies());
        this.powerUpsViewer = new PowerUpsViewer(road.getPowerUps());
        this.shotsViewer = new ShotsViewer(road.getShots());

    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        playerViewer.draw(gui);
        enemyViewer.draw(gui);
        powerUpsViewer.draw(gui);
        shotsViewer.draw(gui);
    }
}

