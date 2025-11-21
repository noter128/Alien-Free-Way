package com.tg04.alienfreeway.controller.game;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.elements.enemies.*;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.viewer.game.EnemyViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class EnemyController extends GameController {
    private long lastUpdateTime = 0;
    private long lastWaveTime = 0;
    private long lastSpecialEnemySpawnTime = 0;

    private static final int WAVE_INTERVAL = 12000;
    private static final int SPECIAL_ENEMY_SPAWN_INTERVAL = 120;

    public final EnemyViewer enemyViewer;
    private final Random random = new Random();

    public EnemyController(Road road) {
        super(road);
        this.enemyViewer = new EnemyViewer(road.getEnemies());
    }

    private void moveEnemies() {
        Iterator<Enemy> iterator = getModel().getEnemies().iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            Position currentPosition = enemy.getPosition();
            Position newPosition;

            if (enemy instanceof ZigZagEnemy) {
                ((ZigZagEnemy) enemy).moveZigZag();
                newPosition = enemy.getPosition();
            } else {
                newPosition = new Position(currentPosition.getX() - enemy.getSpeed(), currentPosition.getY());
            }

            if (newPosition.getX() < 0) {
                iterator.remove();
            } else {
                enemy.setPosition(newPosition);
            }

            if (enemy instanceof StealthEnemy) {
                ((StealthEnemy) enemy).updateVisibility(getModel().getPlayer());
            }

        }
    }

    public void checkCollisions() {
        Player player = getModel().getPlayer();
        List<Enemy> enemiesToRemove = new ArrayList<>();

        for (Enemy enemy : getModel().getEnemies()) {
            if (player.getPosition().equals(enemy.getPosition())) {
                System.out.println("Collision detected with: " + enemy.getClass().getSimpleName());

                if (!player.hasTemporaryImmunity()) {
                    System.out.println("Dealing " + enemy.getDamage() + " damage to player.");
                    player.decreaseHealth(enemy.getDamage());
                }
                enemiesToRemove.add(enemy);
            }
        }
        getModel().getEnemies().removeAll(enemiesToRemove);
    }

    private void spawnEnemyWave(long time) {
        if (time - lastWaveTime > WAVE_INTERVAL) {
            List<Enemy> enemies = getModel().getEnemies();
            int spawnX = getModel().getWidth() - 1;

            for (int y = 1; y < getModel().getHeight() - 1; y++) {
                BasicEnemy enemy = new BasicEnemy(spawnX, y);
                enemies.add(enemy);
            }
            lastWaveTime = time;
        }
    }

    private void spawnSpecialEnemy(long time) {
        if (time - lastSpecialEnemySpawnTime > SPECIAL_ENEMY_SPAWN_INTERVAL) {
            List<Enemy> enemies = getModel().getEnemies();

            int spawnX = getModel().getWidth() - 1;
            int roadHeight = getModel().getHeight();

            if (roadHeight > 2) {
                int spawnY = random.nextInt(roadHeight - 2) + 1;

                Enemy enemy = generateRandomEnemy(spawnX, spawnY);
                enemies.add(enemy);

                lastSpecialEnemySpawnTime = time;
            }
        }
    }

    private Enemy generateRandomEnemy(int x, int y) {
        int type = random.nextInt(6);
        return switch (type) {
            case 1 -> new ArmoredEnemy(x, y);
            case 2 -> new FastEnemy(x, y);
            case 3 -> new HealingEnemy(x, y);
            case 4 -> new StealthEnemy(x, y);
            case 5 -> new ZigZagEnemy(x, y);
            default -> new BasicEnemy(x, y);
        };
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (time - lastUpdateTime > 500) {
            getModel().getPlayer().updatePowerUpTimers();
            moveEnemies();
            checkCollisions();
            spawnEnemyWave(time);
            spawnSpecialEnemy(time);
            lastUpdateTime = time;
        }
    }

    @Override
    public void render(GUI gui) throws IOException {
        enemyViewer.draw(gui);
    }
}
