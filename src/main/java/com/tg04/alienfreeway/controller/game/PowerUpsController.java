package com.tg04.alienfreeway.controller.game;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.elements.PowerUpType;
import com.tg04.alienfreeway.model.game.elements.PowerUps;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.viewer.game.PowerUpsViewer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

public class PowerUpsController extends GameController {
    private long lastPowerUpSpawnTime = 0;
    private long lastPowerUpMovementTime = 0;
    private static final long POWERUP_SPAWN_INTERVAL = 10000;
    private static final long POWERUP_MOVE_INTERVAL = 500;

    private final Random random = new Random();
    private final PowerUpsViewer powerUpsViewer;

    public PowerUpsController(Road road) {
        super(road);
        this.powerUpsViewer = new PowerUpsViewer(road.getPowerUps());
    }

    private void updatePowerUps() {
        Iterator<PowerUps> iterator = getModel().getPowerUps().iterator();
        Player player = getModel().getPlayer();

        while (iterator.hasNext()) {
            PowerUps powerUp = iterator.next();

            Position newPosition = new Position(powerUp.getPosition().getX() - 1, powerUp.getPosition().getY());
            powerUp.setPosition(newPosition);

            if (newPosition.getX() < 0) {
                iterator.remove();
                continue;
            }

            if (player.getPosition().equals(powerUp.getPosition())) {
                System.out.println("Power-Up Collected: " + powerUp.getType());
                applyPowerUp(powerUp.getType());
                iterator.remove();
            }
        }
    }

    private void spawnPowerUps(long time) {
        if (time - lastPowerUpSpawnTime >= POWERUP_SPAWN_INTERVAL) {
            int spawnX = getModel().getWidth() - 1;
            int spawnY = random.nextInt(getModel().getHeight() - 2) + 1;
            PowerUpType type = PowerUpType.values()[random.nextInt(PowerUpType.values().length)];

            PowerUps newPowerUp = new PowerUps(new Position(spawnX, spawnY), type, 20);
            getModel().getPowerUps().add(newPowerUp);
            lastPowerUpSpawnTime = time;

            System.out.println("Spawned Power-Up: " + type + " at Y=" + spawnY);
        }
    }

    private void applyPowerUp(PowerUpType type) {
        Player player = getModel().getPlayer();

        switch (type) {
            case DOUBLE_SHOT_SPEED:
                player.activatePowerUp(PowerUpType.DOUBLE_SHOT_SPEED, 200);
                System.out.println("Power-Up: DOUBLE SHOT SPEED Activated");
                break;
            case TEMPORARY_IMMUNITY:
                player.activatePowerUp(PowerUpType.TEMPORARY_IMMUNITY, 200);
                System.out.println("Power-Up: TEMPORARY IMMUNITY Activated");
                break;
            case DOUBLE_MONEY:
                player.activatePowerUp(PowerUpType.DOUBLE_MONEY, 200);
                System.out.println("Power-Up: DOUBLE MONEY Activated");
                break;
            case SLOW_ENEMIES:
                slowDownEnemies();
                System.out.println("Power-Up: SLOW ENEMIES Activated");
                break;
            case HEAL:
                int new_health = player.getHealth() + player.getHealth()/2;
                if (new_health >= 10) {
                    player.setHealth(10);
                    break;
                }
                player.setHealth(player.getHealth() + player.getHealth()/2);
                System.out.println("Power-Up: FULL HEALTH Restored Player's Health to 10");
                break;
            default:
                System.out.println("Unknown Power-Up: " + type);
        }
    }

    private void slowDownEnemies() {
        for (Enemy enemy : getModel().getEnemies()) {
            enemy.setSpeed(Math.max(1, enemy.getSpeed() - 1));
        }
        System.out.println("Enemies slowed down!");
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        spawnPowerUps(time);

        if (time - lastPowerUpMovementTime >= POWERUP_MOVE_INTERVAL) {
            updatePowerUps();
            lastPowerUpMovementTime = time;
        }
    }

    @Override
    public void render(GUI gui) throws IOException {
        powerUpsViewer.draw(gui);
    }
}
