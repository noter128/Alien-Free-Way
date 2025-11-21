package com.tg04.alienfreewaytesting.states;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.menu.GameOverMenuController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.states.GameOverState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameOverStateTest {
    private Game game;
    private GUI gui;
    private GameOverMenuController gameOverMenuController;
    private GameOverState gameOverState;

    @BeforeEach
    public void setUp() {
        game = mock(Game.class);
        gui = mock(GUI.class);
        gameOverMenuController = mock(GameOverMenuController.class);

        gameOverState = new GameOverState(gameOverMenuController);
    }

    @Test
    public void testStepCallsControllerMethods() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.SELECT);

        gameOverState.step(game, gui, 1000);

        verify(gui).getNextAction();
        verify(gui).clear();

        verify(gameOverMenuController).step(game, GUI.ACTION.SELECT, 1000);
        verify(gameOverMenuController).render(gui);
    }

    @Test
    public void testActionHandledCorrectly() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);

        gameOverState.step(game, gui, 1000);

        verify(gui).getNextAction();
        verify(gameOverMenuController).step(game, GUI.ACTION.UP, 1000);
        verify(gameOverMenuController).render(gui);
    }

    @Test
    public void testNoExtraInteractions() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);

        gameOverState.step(game, gui, 1000);

        verify(gui).getNextAction();
        verify(gui).clear();
        verify(gameOverMenuController).step(game, GUI.ACTION.NONE, 1000);
        verify(gameOverMenuController).render(gui);

        verifyNoMoreInteractions(gui, gameOverMenuController);
    }
}
