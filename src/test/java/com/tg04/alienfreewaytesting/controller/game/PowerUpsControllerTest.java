package com.tg04.alienfreewaytesting.controller.game;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.elements.PowerUpType;
import com.tg04.alienfreeway.model.game.elements.PowerUps;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.controller.game.PowerUpsController;
import com.tg04.alienfreeway.viewer.game.PowerUpsViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PowerUpsControllerTest {
    private Road mockRoad;
    private Game mockGame;
    private Player mockPlayer;
    private GUI mockGUI;
    private PowerUpsController powerUpsController;

    @BeforeEach
    void setUp() {
        mockRoad = mock(Road.class);
        mockGame = mock(Game.class);
        mockPlayer = mock(Player.class);
        mockGUI = mock(GUI.class);

        when(mockRoad.getPlayer()).thenReturn(mockPlayer);
        when(mockRoad.getPowerUps()).thenReturn(new ArrayList<>());
        when(mockRoad.getEnemies()).thenReturn(new ArrayList<>());
        when(mockRoad.getWidth()).thenReturn(10);
        when(mockRoad.getHeight()).thenReturn(5);

        powerUpsController = new PowerUpsController(mockRoad);
    }


    @Test
    void testPowerUpCollision() {
        List<PowerUps> powerUps = new ArrayList<>();
        PowerUps mockPowerUp = mock(PowerUps.class);
        when(mockPowerUp.getPosition()).thenReturn(new Position(2, 2));
        when(mockPowerUp.getType()).thenReturn(PowerUpType.DOUBLE_MONEY);
        powerUps.add(mockPowerUp);

        when(mockPlayer.getPosition()).thenReturn(new Position(2, 2));
        when(mockRoad.getPowerUps()).thenReturn(powerUps);

        powerUpsController.step(mockGame, GUI.ACTION.NONE, 500);

        assertTrue(powerUps.isEmpty(), "Power-up should be removed after collision.");
        verify(mockPlayer, times(1)).activatePowerUp(PowerUpType.DOUBLE_MONEY, 200);
    }

    @Test
    void testSlowDownEnemiesPowerUp() {
        when(mockPlayer.getPosition()).thenReturn(new Position(0, 0));

        List<Enemy> enemies = new ArrayList<>();
        Enemy mockEnemy1 = mock(Enemy.class);
        Enemy mockEnemy2 = mock(Enemy.class);
        when(mockEnemy1.getSpeed()).thenReturn(3);
        when(mockEnemy2.getSpeed()).thenReturn(2);

        enemies.add(mockEnemy1);
        enemies.add(mockEnemy2);
        when(mockRoad.getEnemies()).thenReturn(enemies);

        List<PowerUps> powerUps = new ArrayList<>();
        PowerUps mockPowerUp = mock(PowerUps.class);
        when(mockPowerUp.getPosition()).thenReturn(new Position(2, 2));
        when(mockPowerUp.getType()).thenReturn(PowerUpType.SLOW_ENEMIES);
        powerUps.add(mockPowerUp);
        when(mockRoad.getPowerUps()).thenReturn(powerUps);

        when(mockPlayer.getPosition()).thenReturn(new Position(2, 2));

        powerUpsController.step(mockGame, GUI.ACTION.NONE, 10000);
        powerUpsController.step(mockGame, GUI.ACTION.NONE, 20000);

        verify(mockEnemy1, times(1)).setSpeed(2);
        verify(mockEnemy2, times(1)).setSpeed(1);
    }

    @Test
    void testPowerUpMovement() {
        when(mockPlayer.getPosition()).thenReturn(new Position(0, 0));

        List<PowerUps> powerUps = new ArrayList<>();
        PowerUps mockPowerUp = mock(PowerUps.class);
        when(mockPowerUp.getPosition()).thenReturn(new Position(5, 2));
        powerUps.add(mockPowerUp);

        when(mockRoad.getPowerUps()).thenReturn(powerUps);

        powerUpsController.step(mockGame, GUI.ACTION.NONE, 500);

        verify(mockPowerUp, times(1)).setPosition(new Position(4, 2));
    }

    @Test
    void testRenderPowerUps() throws IOException {
        List<PowerUps> powerUps = new ArrayList<>();
        PowerUps powerUp1 = new PowerUps(new Position(5, 5), PowerUpType.DOUBLE_SHOT_SPEED, 200);
        PowerUps powerUp2 = new PowerUps(new Position(3, 3), PowerUpType.HEAL, 200);
        powerUps.add(powerUp1);
        powerUps.add(powerUp2);

        when(mockRoad.getPowerUps()).thenReturn(powerUps);

        PowerUpsViewer spyViewer = spy(new PowerUpsViewer(powerUps));
        PowerUpsController controllerWithSpy = new PowerUpsController(mockRoad) {
            @Override
            public void render(GUI gui) throws IOException {
                spyViewer.draw(gui);
            }
        };

        controllerWithSpy.render(mockGUI);

        verify(mockGUI, times(1)).drawPowerUp(new Position(5, 5), PowerUpType.DOUBLE_SHOT_SPEED);
        verify(mockGUI, times(1)).drawPowerUp(new Position(3, 3), PowerUpType.HEAL);
    }
}
