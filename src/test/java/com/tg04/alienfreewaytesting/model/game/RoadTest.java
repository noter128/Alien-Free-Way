package com.tg04.alienfreewaytesting.model.game;

import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.*;
import com.tg04.alienfreeway.model.game.elements.enemies.*;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.model.game.road.RoadBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoadTest {

    private Road road;

    @BeforeEach
    public void setUp() {
        RoadBuilder roadBuilder = new RoadBuilder();
        road = roadBuilder.createRoad();
    }

    @Test
    public void testRoadDimensions() {
        assertEquals(80, road.getWidth());
        assertEquals(24, road.getHeight());
    }

    @Test
    public void testPlayerInitialization() {
        assertNotNull(road.getPlayer());
        assertEquals(0, road.getPlayer().getPosition().getX());
        assertEquals(12, road.getPlayer().getPosition().getY());
    }

    @Test
    public void testWallsInitialization() {
        List<Wall> walls = road.getWalls();
        assertNotNull(walls);
        assertEquals(80 * 2, walls.size());

        for (int x = 0; x < 80; x++) {
            assertTrue(walls.contains(new Wall(x, 0)));
            assertTrue(walls.contains(new Wall(x, 23)));
        }
    }

    @Test
    public void testEnemiesInitialization() {
        List<Enemy> enemies = road.getEnemies();
        assertNotNull(enemies);
        assertEquals(3, enemies.size());

        assertTrue(enemies.get(0) instanceof ArmoredEnemy);
        assertTrue(enemies.get(1) instanceof HealingEnemy);
        assertTrue(enemies.get(2) instanceof StealthEnemy);
    }

    @Test
    public void testPowerUpsInitialization() {
        List<PowerUps> powerUps = road.getPowerUps();
        assertNotNull(powerUps);
        assertTrue(powerUps.isEmpty());
    }

    @Test
    public void testShotsInitialization() {
        List<Shots> shots = road.getShots();
        assertNotNull(shots);
        assertTrue(shots.isEmpty());
    }

    @Test
    public void testIsEmptyPositionWithWalls() {
        Position wallPosition = new Position(0, 0);
        assertFalse(road.isEmpty(wallPosition));

        Position emptyPosition = new Position(40, 12);
        assertTrue(road.isEmpty(emptyPosition));
    }

    @Test
    public void testSetAndGetEnemies() {
        List<Enemy> mockEnemies = mock(List.class);
        road.setEnemies(mockEnemies);
        assertSame(mockEnemies, road.getEnemies());
    }

    @Test
    public void testSetAndGetWalls() {
        List<Wall> mockWalls = mock(List.class);
        road.setWalls(mockWalls);
        assertSame(mockWalls, road.getWalls());
    }

    @Test
    public void testSetAndGetPowerUps() {
        List<PowerUps> mockPowerUps = mock(List.class);
        road.setPowerUps(mockPowerUps);
        assertSame(mockPowerUps, road.getPowerUps());
    }

    @Test
    public void testSetAndGetShots() {
        List<Shots> mockShots = mock(List.class);
        road.setShots(mockShots);
        assertSame(mockShots, road.getShots());
    }
}
