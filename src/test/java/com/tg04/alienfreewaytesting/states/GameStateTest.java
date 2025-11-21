package com.tg04.alienfreewaytesting.states;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.states.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameStateTest {
    private Game game;
    private GUI gui;
    private GameState gameState;

    @BeforeEach
    public void setUp() {
        game = mock(Game.class);
        gui = mock(GUI.class);
        gameState = new GameState();
    }

    @Test
    public void testGameStateStep() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);

        gameState.step(game, gui, 1000);

        verify(gui).getNextAction();
        verify(gui, atLeastOnce()).clear();
        verify(gui, atLeastOnce()).refresh();
    }
}
