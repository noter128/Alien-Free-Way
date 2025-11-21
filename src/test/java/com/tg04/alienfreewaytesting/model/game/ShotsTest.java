package com.tg04.alienfreewaytesting.model.game;

import com.tg04.alienfreeway.model.game.elements.ShotType;
import com.tg04.alienfreeway.model.game.elements.Shots;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShotsTest {

    @Test
    public void testShotsInitialization() {
        Shots bulletShot = new Shots(10, 15, ShotType.BULLET);
        assertEquals(10, bulletShot.getPosition().getX());
        assertEquals(15, bulletShot.getPosition().getY());
        assertEquals(ShotType.BULLET, bulletShot.getType());
        assertEquals(1, bulletShot.getDamage());
        assertEquals(2, bulletShot.getSpeed());
    }

    @Test
    public void testDifferentShotTypes() {
        Shots missileShot = new Shots(20, 25, ShotType.MISSILE);
        assertEquals(ShotType.MISSILE, missileShot.getType());
        assertEquals(5, missileShot.getDamage());
        assertEquals(1, missileShot.getSpeed());

        Shots laserShot = new Shots(30, 35, ShotType.LASER);
        assertEquals(ShotType.LASER, laserShot.getType());
        assertEquals(2, laserShot.getDamage());
        assertEquals(2, laserShot.getSpeed());

        Shots powerShot = new Shots(40, 45, ShotType.POWER_SHOT);
        assertEquals(ShotType.POWER_SHOT, powerShot.getType());
        assertEquals(3, powerShot.getDamage());
        assertEquals(2, powerShot.getSpeed());
    }

    @Test
    public void testShotTypeAttributes() {
        assertEquals(1, ShotType.BULLET.getDamage());
        assertEquals(2, ShotType.BULLET.getSpeed());

        assertEquals(5, ShotType.MISSILE.getDamage());
        assertEquals(1, ShotType.MISSILE.getSpeed());

        assertEquals(2, ShotType.LASER.getDamage());
        assertEquals(2, ShotType.LASER.getSpeed());

        assertEquals(3, ShotType.POWER_SHOT.getDamage());
        assertEquals(2, ShotType.POWER_SHOT.getSpeed());
    }

    @Test
    public void testShotPosition() {
        Shots laserShot = new Shots(12, 18, ShotType.LASER);

        assertEquals(12, laserShot.getPosition().getX());
        assertEquals(18, laserShot.getPosition().getY());
    }

    @Test
    public void testSpeedAndDamageConsistency() {
        Shots powerShot = new Shots(15, 20, ShotType.POWER_SHOT);

        assertEquals(ShotType.POWER_SHOT.getDamage(), powerShot.getDamage());
        assertEquals(ShotType.POWER_SHOT.getSpeed(), powerShot.getSpeed());
    }
}
