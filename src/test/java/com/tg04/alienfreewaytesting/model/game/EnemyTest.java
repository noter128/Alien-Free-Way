package com.tg04.alienfreewaytesting.model.game;

import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.elements.enemies.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnemyTest {

    private Enemy basicEnemy;
    private Enemy armoredEnemy;
    private Enemy fastEnemy;
    private Enemy healingEnemy;
    private StealthEnemy stealthEnemy;
    private ZigZagEnemy zigZagEnemy;

    @BeforeEach
    public void setUp() {
        basicEnemy = new BasicEnemy(10, 5);
        armoredEnemy = new ArmoredEnemy(15, 10);
        fastEnemy = new FastEnemy(20, 8);
        healingEnemy = new HealingEnemy(25, 12);
        stealthEnemy = new StealthEnemy(30, 15);
        zigZagEnemy = new ZigZagEnemy(35, 18);
    }

    @Test
    public void testBasicEnemyAttributes() {
        assertEquals(4, basicEnemy.getEnemyHealth());
        assertEquals(1, basicEnemy.getSpeed());
        assertEquals(1, basicEnemy.getValue());
        assertEquals(2, basicEnemy.getDamage());
    }

    @Test
    public void testArmoredEnemyAttributes() {
        assertEquals(5, armoredEnemy.getEnemyHealth());
        assertEquals(1, armoredEnemy.getSpeed());
        assertEquals(20, armoredEnemy.getValue());
        assertEquals(3, armoredEnemy.getDamage());

        armoredEnemy.decreaseEnemyHealth();
        assertEquals(5, armoredEnemy.getEnemyHealth());
        for (int i  =0; i < 9; i++) {
            armoredEnemy.decreaseEnemyHealth();
        }
        armoredEnemy.decreaseEnemyHealth();
        assertEquals(4, armoredEnemy.getEnemyHealth());
    }

    @Test
    public void testFastEnemyAttributes() {
        assertEquals(2, fastEnemy.getEnemyHealth());
        assertEquals(5, fastEnemy.getSpeed());
        assertEquals(50, fastEnemy.getValue());
        assertEquals(0, fastEnemy.getDamage());
    }

    @Test
    public void testHealingEnemyAttributes() {
        assertEquals(5, healingEnemy.getEnemyHealth());
        assertEquals(1, healingEnemy.getSpeed());
        assertEquals(25, healingEnemy.getValue());
        assertEquals(1, healingEnemy.getDamage());
    }

    @Test
    public void testStealthEnemyVisibilityWithMockPlayer() {
        Player mockPlayer = mock(Player.class);

        when(mockPlayer.getPosition()).thenReturn(new Position(30, 15));

        assertFalse(stealthEnemy.isVisible());
        stealthEnemy.updateVisibility(mockPlayer);
        assertTrue(stealthEnemy.isVisible());

        when(mockPlayer.getPosition()).thenReturn(new Position(50, 50));
        stealthEnemy.updateVisibility(mockPlayer);
        assertFalse(stealthEnemy.isVisible());
    }

    @Test
    public void testZigZagEnemyMovement() {
        Position initialPosition = zigZagEnemy.getPosition();

        zigZagEnemy.moveZigZag();
        Position newPosition = zigZagEnemy.getPosition();

        assertNotEquals(initialPosition, newPosition);
        assertTrue(newPosition.getX() < initialPosition.getX());
        assertTrue(newPosition.getY() >= 1 && newPosition.getY() <= 22);
    }

    @Test
    public void testEnemyHealthDecrease() {
        basicEnemy.decreaseEnemyHealth();
        assertEquals(3, basicEnemy.getEnemyHealth());

        basicEnemy.decreaseEnemyHealth(2);
        assertEquals(1, basicEnemy.getEnemyHealth());
    }
    @Test
    public void testIncreaseEnemyHealth() {
        basicEnemy.increaseEnemyHealth(2);
        assertEquals(6, basicEnemy.getEnemyHealth());

        basicEnemy.increaseEnemyHealth(1);
        assertEquals(7, basicEnemy.getEnemyHealth());
    }

    @Test
    public void testSetAndGetSpeed() {
        basicEnemy.setSpeed(3);
        assertEquals(3, basicEnemy.getSpeed());

        basicEnemy.setSpeed(5);
        assertEquals(5, basicEnemy.getSpeed());
    }

    @Test
    public void testHealingEnemyHealAfterTwoShots() {
        healingEnemy.decreaseEnemyHealth();
        assertEquals(4, healingEnemy.getEnemyHealth());

        healingEnemy.decreaseEnemyHealth();
        assertEquals(4, healingEnemy.getEnemyHealth());
    }

    @Test
    public void testEnemyDamageValidation() {
        assertTrue(basicEnemy.getDamage() > 0);
        assertTrue(fastEnemy.getDamage() >= 0);
    }

    @Test
    public void testEnemyHealthEdgeCases() {
        basicEnemy.decreaseEnemyHealth(10);
        assertEquals(-6, basicEnemy.getEnemyHealth());

        basicEnemy.increaseEnemyHealth(1000);
        assertEquals(994, basicEnemy.getEnemyHealth());
    }

    @Test
    public void testEnemyCombinedAttributes() {
        basicEnemy.decreaseEnemyHealth(2);
        basicEnemy.setSpeed(2);
        assertEquals(2, basicEnemy.getEnemyHealth());
        assertEquals(2, basicEnemy.getSpeed());
    }
}
