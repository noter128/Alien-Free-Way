package com.tg04.alienfreewaytesting.controller.menu;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.menu.MainMenuController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.menu.MainMenu;
import com.tg04.alienfreeway.states.GameState;
import com.tg04.alienfreeway.viewer.menu.MainMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MainMenuControllerTest {

    private MainMenuController controller;
    private MainMenu menu;
    private Game game;
    private GUI gui;
    private MainMenuViewer menuViewer;

    @BeforeEach
    void setUp() {
        menu = Mockito.mock(MainMenu.class);
        game = Mockito.mock(Game.class);
        gui = Mockito.mock(GUI.class);
        menuViewer = Mockito.mock(MainMenuViewer.class);

        controller = new MainMenuController(menu, menuViewer);
    }

    @Test
    void testExecutePlayOption() {
        when(menu.isSelectedSpecific("play")).thenReturn(true);

        controller.executeSelectedOption(game);

        verify(game, times(1)).setState(any(GameState.class));
    }

    @Test
    void testExecuteQuitOption() {
        when(menu.isSelectedSpecific("quit")).thenReturn(true);

        controller.executeSelectedOption(game);

        verify(game, times(1)).setState(null);
    }

    @Test
    void testStepUp(){
        controller.step(game, GUI.ACTION.UP, 0);
        verify(menu, times(1)).previousEntry();
    }

    @Test
    void testStepDown() {
        controller.step(game, GUI.ACTION.DOWN, 0);
        verify(menu, times(1)).nextEntry();
    }

    @Test
    void testRender() throws IOException {
        controller.render(gui);

        verify(menuViewer, times(1)).draw(gui);
    }
}


