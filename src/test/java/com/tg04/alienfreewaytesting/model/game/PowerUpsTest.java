package com.tg04.alienfreewaytesting.model.game;

import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.PowerUps;
import com.tg04.alienfreeway.model.game.elements.PowerUpType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PowerUpsTest {

    @Test
    public void testPowerUpsInitialization() {
        Position position = new Position(5, 10);
        PowerUps powerUp = new PowerUps(position, PowerUpType.TEMPORARY_IMMUNITY, 10);

        assertEquals(5, powerUp.getPosition().getX());
        assertEquals(10, powerUp.getPosition().getY());
        assertEquals(PowerUpType.TEMPORARY_IMMUNITY, powerUp.getType());
        assertEquals(10, powerUp.getDuration());
    }

    @Test
    public void testDifferentPowerUpTypes() {
        Position position = new Position(0, 0);

        PowerUps doubleShotSpeed = new PowerUps(position, PowerUpType.DOUBLE_SHOT_SPEED, 15);
        assertEquals(PowerUpType.DOUBLE_SHOT_SPEED, doubleShotSpeed.getType());
        assertEquals(15, doubleShotSpeed.getDuration());

        PowerUps temporaryImmunity = new PowerUps(position, PowerUpType.TEMPORARY_IMMUNITY, 20);
        assertEquals(PowerUpType.TEMPORARY_IMMUNITY, temporaryImmunity.getType());
        assertEquals(20, temporaryImmunity.getDuration());

        PowerUps doubleMoney = new PowerUps(position, PowerUpType.DOUBLE_MONEY, 30);
        assertEquals(PowerUpType.DOUBLE_MONEY, doubleMoney.getType());
        assertEquals(30, doubleMoney.getDuration());

        PowerUps slowEnemies = new PowerUps(position, PowerUpType.SLOW_ENEMIES, 25);
        assertEquals(PowerUpType.SLOW_ENEMIES, slowEnemies.getType());
        assertEquals(25, slowEnemies.getDuration());

        PowerUps heal = new PowerUps(position, PowerUpType.HEAL, 5);
        assertEquals(PowerUpType.HEAL, heal.getType());
        assertEquals(5, heal.getDuration());
    }

    @Test
    public void testPositionConsistency() {
        Position position = new Position(3, 6);
        PowerUps powerUp = new PowerUps(position, PowerUpType.SLOW_ENEMIES, 10);

        assertEquals(3, powerUp.getPosition().getX());
        assertEquals(6, powerUp.getPosition().getY());

        position.setX(8);
        position.setY(12);

        assertEquals(3, powerUp.getPosition().getX());
        assertEquals(6, powerUp.getPosition().getY());
    }

    @Test
    public void testInvalidDuration() {
        Position position = new Position(2, 4);

        PowerUps powerUp = new PowerUps(position, PowerUpType.DOUBLE_SHOT_SPEED, -1);
        assertEquals(0, powerUp.getDuration());
    }

    @Test
    public void testNegativeDurationClampedToZero() {
        Position position = new Position(2, 4);

        PowerUps powerUp = new PowerUps(position, PowerUpType.DOUBLE_SHOT_SPEED, -5);
        assertEquals(0, powerUp.getDuration());
    }
}
