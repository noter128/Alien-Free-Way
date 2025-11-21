package com.tg04.alienfreewaytesting.controller.game;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.game.ShotsController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.elements.Shots;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.viewer.game.ShotsViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ShotsControllerTest {
    private Game mockGame;
    private GUI mockGUI;
    private ShotsViewer mockShotsViewer;
    private ShotsController shotsController;
    private List<Shots> mockShots;
    private List<Enemy> mockEnemies;

    @BeforeEach
    void setUp() {
        Road mockRoad = mock(Road.class);
        mockGame = mock(Game.class);
        mockGUI = mock(GUI.class);
        Player mockPlayer = mock(Player.class);
        mockShotsViewer = mock(ShotsViewer.class);
        mockShots = new ArrayList<>();
        mockEnemies = new ArrayList<>();

        when(mockRoad.getShots()).thenReturn(mockShots);
        when(mockRoad.getEnemies()).thenReturn(mockEnemies);
        when(mockRoad.getPlayer()).thenReturn(mockPlayer);
        when(mockRoad.getWidth()).thenReturn(10);

        shotsController = new ShotsController(mockRoad);
    }

    @Test
    void testMoveShots_RemovesShotsOutOfBounds() {
        Shots inBoundsShot = mock(Shots.class);
        when(inBoundsShot.getPosition()).thenReturn(new Position(5, 5));
        when(inBoundsShot.getSpeed()).thenReturn(2);

        Shots outOfBoundsShot = mock(Shots.class);
        when(outOfBoundsShot.getPosition()).thenReturn(new Position(9, 5));
        when(outOfBoundsShot.getSpeed()).thenReturn(2);

        mockShots.add(inBoundsShot);
        mockShots.add(outOfBoundsShot);

        shotsController.step(mockGame, GUI.ACTION.NONE, 1000);

        assertEquals(1, mockShots.size());
        assertTrue(mockShots.contains(inBoundsShot));
        assertFalse(mockShots.contains(outOfBoundsShot));
    }

    @Test
    void testMoveShots_UpdatesShotPositions() {
        Shots shot = mock(Shots.class);
        Position initialPosition = new Position(5, 5);
        Position expectedPosition = new Position(7, 5);

        when(shot.getPosition()).thenReturn(initialPosition).thenReturn(expectedPosition);
        when(shot.getSpeed()).thenReturn(2);
        mockShots.add(shot);

        shotsController.step(mockGame, GUI.ACTION.NONE, 1000);

        verify(shot, times(1)).setPosition(expectedPosition);
    }

    @Test
    void testCheckCollisions_RemovesShotAndDamagesEnemy() {
        Shots shot = mock(Shots.class);
        Position collisionPosition = new Position(5, 5);

        when(shot.getPosition()).thenReturn(collisionPosition);
        when(shot.getDamage()).thenReturn(3);

        Enemy enemy = mock(Enemy.class);
        when(enemy.getPosition()).thenReturn(collisionPosition);
        when(enemy.getEnemyHealth()).thenReturn(5).thenReturn(2);

        mockShots.add(shot);
        mockEnemies.add(enemy);

        shotsController.step(mockGame, GUI.ACTION.NONE, 1000);

        verify(enemy, times(1)).decreaseEnemyHealth(3);

        assertFalse(mockShots.contains(shot));
    }

    @Test
    void testRenderCallsShotsViewer() throws IOException, NoSuchFieldException, IllegalAccessException {
        java.lang.reflect.Field shotsViewerField = ShotsController.class.getDeclaredField("shotsViewer");
        shotsViewerField.setAccessible(true);
        shotsViewerField.set(shotsController, mockShotsViewer);

        shotsController.render(mockGUI);

        verify(mockShotsViewer, times(1)).draw(mockGUI);
    }
}
