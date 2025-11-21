package com.tg04.alienfreewaytesting.controller.game;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.*;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.controller.game.PlayerController;
import com.tg04.alienfreeway.viewer.game.PlayerViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerControllerTest {

    @Mock
    private Road mockRoad;

    @Mock
    private Game mockGame;

    @Mock
    private GUI mockGUI;

    private PlayerController playerController;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        mockRoad = mock(Road.class);
        mockPlayer = mock(Player.class);
        when(mockRoad.getPlayer()).thenReturn(mockPlayer);
        playerController = new PlayerController(mockRoad);
    }

    @Test
    void testMovePlayerUp_ValidMove() {
        Position currentPosition = new Position(5, 5);
        Position upPosition = new Position(5, 4);

        when(mockPlayer.getPosition()).thenReturn(currentPosition);
        when(mockRoad.isEmpty(upPosition)).thenReturn(true);

        playerController.movePlayerUp();

        verify(mockPlayer, times(1)).setPosition(upPosition);
    }

    @Test
    void testMovePlayerUp_InvalidMove() {
        Position currentPosition = new Position(5, 5);
        Position upPosition = new Position(5, 4);

        when(mockPlayer.getPosition()).thenReturn(currentPosition);
        when(mockRoad.isEmpty(upPosition)).thenReturn(false);

        playerController.movePlayerUp();

        verify(mockPlayer, never()).setPosition(upPosition);
    }

    @Test
    void testFireShots_SingleShot() {
        Shots mockShot = mock(Shots.class);
        List<Shots> mockShotsList = mock(List.class);

        when(mockShot.getType()).thenReturn(ShotType.BULLET);
        when(mockPlayer.shoot()).thenReturn(mockShot);
        when(mockPlayer.hasDoubleShotSpeed()).thenReturn(false);
        when(mockRoad.getShots()).thenReturn(mockShotsList);

        playerController.step(mockGame, GUI.ACTION.NONE, 300);

        verify(mockRoad, times(1)).getShots();
        verify(mockShotsList, times(1)).add(mockShot);
    }

    @Test
    void testFireShots_PowerShot() {
        Shots mockShot = mock(Shots.class);
        when(mockShot.getType()).thenReturn(ShotType.POWER_SHOT);
        when(mockPlayer.shoot()).thenReturn(mockShot);
        when(mockPlayer.hasDoubleShotSpeed()).thenReturn(false);

        List<Shots> shotsList = new ArrayList<>();
        when(mockRoad.getShots()).thenReturn(shotsList);
        when(mockPlayer.getPosition()).thenReturn(new Position(5, 5));

        playerController.step(mockGame, GUI.ACTION.NONE, 300);

        assertEquals(3, shotsList.size());
    }

    @Test
    void testRender() throws IOException {
        PlayerViewer mockPlayerViewer = mock(PlayerViewer.class);

        PlayerController playerController = new PlayerController(mockRoad, mockPlayerViewer);

        playerController.render(mockGUI);

        verify(mockPlayerViewer, times(1)).draw(mockGUI);
    }


    @Test
    void testUpdateScore() {
        Shots mockShot = mock(Shots.class);

        when(mockShot.getType()).thenReturn(ShotType.BULLET);
        when(mockPlayer.shoot()).thenReturn(mockShot);
        when(mockPlayer.getScore()).thenReturn(100);

        playerController.step(mockGame, GUI.ACTION.NONE, 300);

        verify(mockPlayer, times(1)).setScore(101);
    }
}
