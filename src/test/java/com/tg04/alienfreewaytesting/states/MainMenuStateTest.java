package com.tg04.alienfreewaytesting.states;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.states.MainMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MainMenuStateTest {
    private Game game;
    private GUI gui;
    private MainMenuState mainMenuState;

    @BeforeEach
    public void setUp() {
        game = mock(Game.class);
        gui = mock(GUI.class);

        mainMenuState = new MainMenuState();
    }

    @Test
    public void testStepHandlesUpAction() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);

        mainMenuState.step(game, gui, 1000);

        verify(gui).getNextAction();
        verify(gui).drawTitleWithHighlight(any(), eq("[ ALIEN FREE WAY ]"), any(), any());
        verify(gui, atLeastOnce()).drawText(any(), any(), any());
        verify(gui).refresh();
        verifyNoMoreInteractions(gui);
    }
}
