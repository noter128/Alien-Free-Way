package com.tg04.alienfreewaytesting.model.game;

import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.elements.PowerUpType;
import com.tg04.alienfreeway.model.game.elements.ShotType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    @BeforeEach
    public void setUp() {
        player = new Player(0, 12);
    }

    @Test
    public void testHealth() {
        assertEquals(10, player.getHealth());
        player.setHealth(5);
        assertEquals(5, player.getHealth());

        player.decreaseHealth(3);
        assertEquals(2, player.getHealth());
    }

    @Test
    void testSetHealthClampedToMaxValue() {
        player.setHealth(15);
        assertEquals(10, player.getHealth());
    }
    @Test
    void testHealthCannotGoBelowZero() {
        player.setHealth(5);
        player.decreaseHealth(10);
        assertEquals(0, player.getHealth());
    }

    @Test
    public void testWallet() {
        assertEquals(0, player.getWallet());

        player.addWallet(5);
        assertEquals(5, player.getWallet());

        player.deductWallet(3);
        assertEquals(2, player.getWallet());
    }

    @Test
    public void testScore() {
        assertEquals(0, player.getScore());

        player.setScore(5);
        assertEquals(5, player.getScore());
    }

    @Test
    public void testExtraLife() {
        player.buyExtraLife();
        assertTrue(player.hasExtraLife());

        player.useExtraLife();
        assertFalse(player.hasExtraLife());
    }

    @Test
    public void testShotType() {

        assertEquals("BULLET", player.getCurrentShotType());

        player.setTemporaryWeapon(ShotType.MISSILE);
        assertEquals("MISSILE", player.getCurrentShotType());

        player.setTemporaryWeapon(ShotType.LASER);
        assertEquals("LASER", player.getCurrentShotType());

        player.setTemporaryWeapon(ShotType.POWER_SHOT);
        assertEquals("POWER_SHOT", player.getCurrentShotType());
    }

    @Test
    public void testDoubleShotSpeed() {
        assertFalse(player.hasDoubleShotSpeed());

        player.activatePowerUp(PowerUpType.DOUBLE_SHOT_SPEED, 200);
        assertTrue(player.hasDoubleShotSpeed());
    }

    @Test
    public void testTemporaryImmunity() {
        assertFalse(player.hasTemporaryImmunity());

        player.activatePowerUp(PowerUpType.TEMPORARY_IMMUNITY, 200);
        assertTrue(player.hasTemporaryImmunity());
    }

    @Test
    public void testDoubleMoney() {
        assertFalse(player.hasDoubleMoney());

        player.activatePowerUp(PowerUpType.DOUBLE_MONEY, 200);
        assertTrue(player.hasDoubleMoney());
    }

    @Test
    public void testSlowEnemies() {
        assertFalse(player.hasSlowEnemies());

        player.activatePowerUp(PowerUpType.SLOW_ENEMIES, 200);
        assertTrue(player.hasSlowEnemies());
    }

    @Test
    public void testPowerUpTimers() {
        player.activatePowerUp(PowerUpType.DOUBLE_SHOT_SPEED, 0);
        player.activatePowerUp(PowerUpType.TEMPORARY_IMMUNITY, 8);
        player.activatePowerUp(PowerUpType.DOUBLE_MONEY, 0);
        player.activatePowerUp(PowerUpType.SLOW_ENEMIES, 3);

        player.updatePowerUpTimers();

        assertFalse(player.hasDoubleShotSpeed());
        assertTrue(player.hasTemporaryImmunity());
        assertFalse(player.hasDoubleMoney());
        assertTrue(player.hasSlowEnemies());
    }

    @Test
    void testPowerUpTimersExpireCorrectly() {
        player.activatePowerUp(PowerUpType.DOUBLE_SHOT_SPEED, 2);
        player.activatePowerUp(PowerUpType.TEMPORARY_IMMUNITY, 3);

        player.updatePowerUpTimers();
        assertTrue(player.hasDoubleShotSpeed());
        assertTrue(player.hasTemporaryImmunity());

        player.updatePowerUpTimers();
        assertFalse(player.hasDoubleShotSpeed());
        assertTrue(player.hasTemporaryImmunity());

        player.updatePowerUpTimers();
        assertFalse(player.hasTemporaryImmunity());
    }
}
