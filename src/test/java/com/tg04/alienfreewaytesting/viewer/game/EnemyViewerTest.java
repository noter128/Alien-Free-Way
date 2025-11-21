package com.tg04.alienfreewaytesting.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.viewer.game.EnemyViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class EnemyViewerTest {
    private GUI gui;
    private Enemy enemy;
    private EnemyViewer enemyViewer;

    @BeforeEach
    public void setUp() {
        gui = mock(GUI.class);
        enemy = mock(Enemy.class);

        when(enemy.getPosition()).thenReturn(new Position(10, 5));

        enemyViewer = new EnemyViewer(List.of(enemy));
    }

    @Test
    public void testDrawElements() {
        enemyViewer.drawElements(gui);

        verify(gui).drawEnemy(new Position(10, 5), enemy);
    }
}
