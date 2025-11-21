package com.tg04.alienfreeway.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.viewer.Viewer;

import java.util.List;

public class EnemyViewer extends Viewer<List<Enemy>> {
    public EnemyViewer(List<Enemy> enemies) {
        super(enemies);
    }

    private static final int HUD_HEIGHT = 1;
    private static final int LAST_LINE = 22;

    @Override
    public void drawElements(GUI gui) {
        for (Enemy enemy : getModel()) {
            int y = enemy.getPosition().getY();
            if (y > HUD_HEIGHT && y < LAST_LINE) {
                gui.drawEnemy(enemy.getPosition(), enemy);
            }
        }
    }
}
