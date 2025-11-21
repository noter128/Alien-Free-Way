package com.tg04.alienfreeway.controller.game;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.model.game.elements.Shots;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.viewer.game.ShotsViewer;
import com.tg04.alienfreeway.model.game.elements.Player;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ShotsController extends GameController {
    private final List<Shots> activeShots;
    private final ShotsViewer shotsViewer;

    public ShotsController(Road road) {
        super(road);
        this.activeShots = road.getShots();
        this.shotsViewer = new ShotsViewer(activeShots);
    }

    private void moveShots() {
        Iterator<Shots> iterator = activeShots.iterator();
        while (iterator.hasNext()) {
            Shots shot = iterator.next();
            Position newPosition = new Position(
                    shot.getPosition().getX() + shot.getSpeed(),
                    shot.getPosition().getY()
            );

            if (newPosition.getX() >= getModel().getWidth()) {
                iterator.remove();
            } else {
                shot.setPosition(newPosition);
            }
        }
    }

    private void checkCollisions() {
        Iterator<Shots> shotIterator = activeShots.iterator();
        List<Enemy> enemies = getModel().getEnemies();
        Player player = getModel().getPlayer();

        while (shotIterator.hasNext()) {
            Shots shot = shotIterator.next();
            boolean hit = false;

            for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
                Enemy enemy = enemyIterator.next();
                if (shot.getPosition().equals(enemy.getPosition())) {
                    enemy.decreaseEnemyHealth(shot.getDamage());
                    hit = true;

                    if (enemy.getEnemyHealth() <= 0) {
                        player.addWallet(enemy.getValue());
                        enemyIterator.remove();
                    }
                    break;
                }
            }
            if (hit) {
                shotIterator.remove();
            }
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        moveShots();
        checkCollisions();
    }

    @Override
    public void render(GUI gui) throws IOException {
        shotsViewer.draw(gui);
    }
}
