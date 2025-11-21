package com.tg04.alienfreewaytesting.controller.menu;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.menu.MidGameMenuController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.menu.MidGameMenu;
import com.tg04.alienfreeway.states.GameState;
import com.tg04.alienfreeway.states.ShopMenuState;
import com.tg04.alienfreeway.viewer.menu.MidGameMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MidGameMenuControllerTest {

    private MidGameMenuController controller;
    private MidGameMenu menu;
    private Player player;
    private Game game;
    private GUI gui;

    @BeforeEach
    void setUp() {
        menu = Mockito.mock(MidGameMenu.class);
        player = Mockito.mock(Player.class);
        game = Mockito.mock(Game.class);
        gui = Mockito.mock(GUI.class);

        controller = new MidGameMenuController(menu, player);
    }

    @Test
    void testExecuteResumeOption() {
        when(menu.isSelectedSpecific("resume")).thenReturn(true);
        when(game.getPreviousState()).thenReturn(Mockito.mock(GameState.class));

        controller.executeSelectedOption(game);

        verify(game, times(1)).setState(any(GameState.class), eq(false));
    }

    @Test
    void testExecuteRestartOption() {
        when(menu.isSelectedSpecific("restart")).thenReturn(true);

        controller.executeSelectedOption(game);

        verify(game, times(1)).setState(any(GameState.class));
    }

    @Test
    void testExecuteShopOption() {
        when(menu.isSelectedSpecific("shop")).thenReturn(true);

        controller.executeSelectedOption(game);

        verify(game, times(1)).setState(any(ShopMenuState.class), eq(false));
    }

    @Test
    void testExecuteQuitOption() {
        when(menu.isSelectedSpecific("quit")).thenReturn(true);

        controller.executeSelectedOption(game);

        verify(game, times(1)).setState(null);
    }

    @Test
    void testStepUp()  {
        controller.step(game, GUI.ACTION.UP, 0);
        verify(menu, times(1)).previousEntry();
    }

    @Test
    void testStepDown() {
        controller.step(game, GUI.ACTION.DOWN, 0);
        verify(menu, times(1)).nextEntry();
    }

    @Test
    void testStepSelect() {
        when(menu.isSelectedSpecific("resume")).thenReturn(true);
        when(game.getPreviousState()).thenReturn(Mockito.mock(GameState.class));

        controller.step(game, GUI.ACTION.SELECT, 0);

        verify(menu, times(1)).isSelectedSpecific("resume");
        verify(game, times(1)).setState(any(GameState.class), eq(false));
    }

    @Test
    void testStepPause()  {
        when(game.getPreviousState()).thenReturn(Mockito.mock(GameState.class));

        controller.step(game, GUI.ACTION.PAUSE, 0);

        verify(game, times(1)).setState(any(GameState.class), eq(false));
    }

    @Test
    void testRender() throws IOException {
        MidGameMenuViewer menuViewerMock = mock(MidGameMenuViewer.class);

        MidGameMenuController controller = new MidGameMenuController(menu, player, menuViewerMock);

        controller.render(gui);

        verify(menuViewerMock, times(1)).draw(gui);
    }

}


