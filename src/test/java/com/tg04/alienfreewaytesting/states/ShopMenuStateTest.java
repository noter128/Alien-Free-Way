package com.tg04.alienfreewaytesting.states;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.menu.ShopMenuController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.states.ShopMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ShopMenuStateTest {
    private Game game;
    private GUI gui;
    private ShopMenuController shopMenuController;
    private ShopMenuState shopMenuState;

    @BeforeEach
    public void setUp() {
        game = mock(Game.class);
        gui = mock(GUI.class);
        shopMenuController = mock(ShopMenuController.class);

        shopMenuState = new ShopMenuState(shopMenuController);
    }

    @Test
    public void testStepCallsControllerMethods() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.SELECT);

        shopMenuState.step(game, gui, 1000);

        verify(gui).getNextAction();
        verify(gui).clear();
        verify(shopMenuController).step(game, GUI.ACTION.SELECT, 1000);
        verify(shopMenuController).render(gui);
    }

    @Test
    public void testActionHandledCorrectly() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);

        shopMenuState.step(game, gui, 1000);

        verify(gui).getNextAction();
        verify(shopMenuController).step(game, GUI.ACTION.UP, 1000);
        verify(shopMenuController).render(gui);
    }

    @Test
    public void testNoExtraInteractions() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);

        shopMenuState.step(game, gui, 1000);

        verify(gui).getNextAction();
        verify(gui).clear();
        verify(shopMenuController).step(game, GUI.ACTION.NONE, 1000);
        verify(shopMenuController).render(gui);
        verifyNoMoreInteractions(gui, shopMenuController);
    }
}
