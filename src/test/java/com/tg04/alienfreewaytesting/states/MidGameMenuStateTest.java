package com.tg04.alienfreewaytesting.states;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.menu.MidGameMenuController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.states.MidGameMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MidGameMenuStateTest {
    private Game game;
    private GUI gui;
    private MidGameMenuController midGameMenuController;
    private MidGameMenuState midGameMenuState;

    @BeforeEach
    public void setUp() {
        game = mock(Game.class);
        gui = mock(GUI.class);
        midGameMenuController = mock(MidGameMenuController.class);
        midGameMenuState = new MidGameMenuState(midGameMenuController);
    }

    @Test
    public void testStepCallsControllerMethods() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.SELECT);

        midGameMenuState.step(game, gui, 1000);

        verify(gui).getNextAction();
        verify(gui).clear();
        verify(midGameMenuController).step(game, GUI.ACTION.SELECT, 1000);
        verify(midGameMenuController).render(gui);
    }

    @Test
    public void testActionHandledCorrectly() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);

        midGameMenuState.step(game, gui, 1000);

        verify(gui).getNextAction();
        verify(midGameMenuController).step(game, GUI.ACTION.UP, 1000);
        verify(midGameMenuController).render(gui);
    }

    @Test
    public void testNoExtraInteractions() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);

        midGameMenuState.step(game, gui, 1000);

        verify(gui).getNextAction();
        verify(gui).clear();
        verify(midGameMenuController).step(game, GUI.ACTION.NONE, 1000);
        verify(midGameMenuController).render(gui);
        verifyNoMoreInteractions(gui, midGameMenuController);
    }
}
