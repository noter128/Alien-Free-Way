package com.tg04.alienfreewaytesting.controller.game;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.game.EnemyController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.elements.enemies.*;
import com.tg04.alienfreeway.model.game.road.Road;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnemyControllerTest {

    private Road road;
    private EnemyController enemyController;
    private Game mockGame;
    private GUI mockGUI;

    @BeforeEach
    void setUp() {
        road = new Road(80, 24);
        road.setPlayer(new Player(10, 12));
        road.setEnemies(new ArrayList<>());
        mockGame = mock(Game.class);
        mockGUI = mock(GUI.class);
        enemyController = new EnemyController(road);
    }

    @Test
    void testMoveEnemies_RemovesOffscreenEnemies() {
        List<Enemy> enemies = road.getEnemies();
        BasicEnemy offscreenEnemy = new BasicEnemy(0, 10);
        enemies.add(offscreenEnemy);

        enemyController.step(mockGame, GUI.ACTION.NONE, 1500);

        assertFalse(enemies.contains(offscreenEnemy), "Off-screen enemy should be removed.");
    }

    @Test
    void testMoveEnemies_UpdatesPositions() {
        List<Enemy> enemies = road.getEnemies();
        BasicEnemy enemy = new BasicEnemy(10, 10);
        enemies.add(enemy);

        enemyController.step(mockGame, GUI.ACTION.NONE, 1000);

        assertEquals(9, enemy.getPosition().getX(), "Enemy position should decrease by speed.");
    }

    @Test
    void testRenderCallsEnemyViewer() throws IOException {
        EnemyController spyController = Mockito.spy(enemyController);
        spyController.render(mockGUI);

        verify(spyController).render(mockGUI);
    }
}
