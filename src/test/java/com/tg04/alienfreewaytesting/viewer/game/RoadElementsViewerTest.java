package com.tg04.alienfreewaytesting.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.*;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.viewer.game.RoadElementsViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class RoadElementsViewerTest {
    private GUI gui;
    private Enemy enemy;
    private PowerUps powerUp;
    private Shots shot;

    private RoadElementsViewer roadElementsViewer;

    @BeforeEach
    public void setUp() {
        gui = mock(GUI.class);
        Road road = mock(Road.class);

        Player player = mock(Player.class);
        when(player.getPosition()).thenReturn(new Position(5, 10));

        enemy = mock(Enemy.class);
        when(enemy.getPosition()).thenReturn(new Position(15, 10));

        powerUp = mock(PowerUps.class);
        when(powerUp.getPosition()).thenReturn(new Position(20, 15));
        when(powerUp.getType()).thenReturn(PowerUpType.HEAL);

        shot = mock(Shots.class);
        when(shot.getPosition()).thenReturn(new Position(25, 20));
        when(shot.getType()).thenReturn(ShotType.LASER);

        when(road.getPlayer()).thenReturn(player);
        when(road.getEnemies()).thenReturn(Collections.singletonList(enemy));
        when(road.getPowerUps()).thenReturn(Collections.singletonList(powerUp));
        when(road.getShots()).thenReturn(Collections.singletonList(shot));

        roadElementsViewer = new RoadElementsViewer(road);
    }

    @Test
    public void testDrawElements() throws IOException {
        PowerUpType expectedPowerUpType = PowerUpType.HEAL;
        ShotType expectedShotType = ShotType.LASER;

        when(powerUp.getType()).thenReturn(expectedPowerUpType);
        when(shot.getType()).thenReturn(expectedShotType);

        roadElementsViewer.drawElements(gui);

        verify(gui).drawPlayer(new Position(5, 10));
        verify(gui).drawEnemy(new Position(15, 10), enemy);
        verify(gui).drawPowerUp(new Position(20, 15), expectedPowerUpType);
        verify(gui).drawShot(new Position(25, 20), expectedShotType);
    }


}