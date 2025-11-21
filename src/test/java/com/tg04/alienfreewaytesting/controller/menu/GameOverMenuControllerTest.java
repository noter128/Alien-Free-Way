package com.tg04.alienfreewaytesting.controller.menu;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.menu.GameOverMenuController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.menu.GameOverMenu;
import com.tg04.alienfreeway.states.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GameOverMenuControllerTest {

    private GameOverMenuController controller;
    private GameOverMenu menu;
    private Player player;
    private Game game;

    @BeforeEach
    void setUp() {
        menu = Mockito.mock(GameOverMenu.class);
        player = Mockito.mock(Player.class);
        game = Mockito.mock(Game.class);
        controller = new GameOverMenuController(menu, player);
    }

    @Test
    void testExecuteUseExtraLife() {
        when(menu.isSelectedSpecific("use extra life")).thenReturn(true);
        when(player.hasExtraLife()).thenReturn(true);

        GameState previousState = mock(GameState.class);
        when(game.getPreviousState()).thenReturn(previousState);

        controller.executeSelectedOption(game);

        verify(player, times(1)).useExtraLife();
        verify(player, times(1)).setHealth(10);
        verify(game, times(1)).setState(previousState);
    }

    @Test
    void testExecuteUseExtraLife_NoLives() {
        when(menu.isSelectedSpecific("use extra life")).thenReturn(true);
        when(player.hasExtraLife()).thenReturn(false);

        controller.executeSelectedOption(game);

        verify(player, never()).useExtraLife();
        verify(player, never()).setHealth(anyInt());
        verify(game, never()).setState(any());
    }

    @Test
    void testExecuteRestart() {
        when(menu.isSelectedSpecific("restart")).thenReturn(true);

        controller.executeSelectedOption(game);

        verify(game, times(1)).setState(any(GameState.class));
    }

    @Test
    void testExecuteQuit() {
        when(menu.isSelectedSpecific("quit")).thenReturn(true);

        controller.executeSelectedOption(game);

        verify(game, times(1)).setState(null);
    }

    @Test
    void testStepUp() {
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
        controller.step(game, GUI.ACTION.SELECT, 0);
        verify(menu, never()).nextEntry();
        verify(menu, never()).previousEntry();
    }
}

