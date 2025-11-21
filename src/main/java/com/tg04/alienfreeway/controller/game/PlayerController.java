package com.tg04.alienfreeway.controller.game;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.elements.PowerUps;
import com.tg04.alienfreeway.model.game.elements.Shots;
import com.tg04.alienfreeway.model.game.elements.ShotType;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.sound.SoundPlayer;
import com.tg04.alienfreeway.viewer.game.PlayerViewer;

import java.io.IOException;
import java.util.List;

public class PlayerController extends GameController {
    private long lastShotTime;
    private final List<PowerUps> powerUpsList;
    private static final long DEFAULT_SHOT_COOLDOWN = 200;
    private final PlayerViewer playerViewer;

    public PlayerController(Road road) {
        this(road, new PlayerViewer(road.getPlayer()));
    }

    public PlayerController(Road road, PlayerViewer playerViewer) {
        super(road);
        this.lastShotTime = 0;
        this.powerUpsList = road.getPowerUps();
        this.playerViewer = playerViewer;
    }

    public void movePlayerUp() {
        Player player = getModel().getPlayer();
        movePlayer(player.getPosition().getUp());
    }

    public void movePlayerDown() {
        Player player = getModel().getPlayer();
        movePlayer(player.getPosition().getDown());
    }

    private void movePlayer(Position position) {
        if (getModel().isEmpty(position)) {
            getModel().getPlayer().setPosition(position);
        }
    }

    protected void fireShots(long time) {
        Player player = getModel().getPlayer();
        long cooldown = player.hasDoubleShotSpeed() ? DEFAULT_SHOT_COOLDOWN / 2 : DEFAULT_SHOT_COOLDOWN;

        if (time - lastShotTime >= cooldown) {
            Shots newShot = player.shoot();
            getModel().getShots().add(newShot);

            if(newShot.getType() == ShotType.POWER_SHOT) {
                Position playerPos = player.getPosition();
                int shotSpeed = newShot.getSpeed();

                Shots upperShot = new Shots(playerPos.getX() + shotSpeed, playerPos.getY() - 1, ShotType.POWER_SHOT);
                Shots lowerShot = new Shots(playerPos.getX() + shotSpeed, playerPos.getY() + 1, ShotType.POWER_SHOT);

                getModel().getShots().add(upperShot);
                getModel().getShots().add(lowerShot);
            }

            switch (newShot.getType()) {
                case BULLET:
                    SoundPlayer.playSound("sounds/bullet.wav");
                    break;
                case LASER:
                    SoundPlayer.playSound("sounds/laser.wav");
                    break;
                case MISSILE:
                    SoundPlayer.playSound("sounds/missile.wav");
                    break;
                case POWER_SHOT:
                    SoundPlayer.playSound("sounds/power_shot.wav");
                    break;
            }
            lastShotTime = time;
        }
    }

    private void checkPowerUpCollision() {
        Player player = getModel().getPlayer();
        for (int i = 0; i < powerUpsList.size(); i++) {
            PowerUps powerUp = powerUpsList.get(i);
            if (player.getPosition().equals(powerUp.getPosition())) {
                player.activatePowerUp(powerUp.getType(), powerUp.getDuration());
                powerUpsList.remove(i);
                i--;
            }
        }
    }

    protected void updateScore() {
        Player player = getModel().getPlayer();
        player.setScore(player.getScore() + 1);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        switch (action) {
            case UP:
                movePlayerUp();
                break;
            case DOWN:
                movePlayerDown();
                break;
            default:
                break;
        }
        updateScore();
        fireShots(time);
        checkPowerUpCollision();
        getModel().getPlayer().updatePowerUpTimers();
    }

    @Override
    public void render(GUI gui) throws IOException {
        playerViewer.draw(gui);
    }
}
