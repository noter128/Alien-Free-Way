package com.tg04.alienfreewaytesting.controller.menu;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.menu.ShopMenuController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.elements.ShotType;
import com.tg04.alienfreeway.model.menu.ShopMenu;
import com.tg04.alienfreeway.states.GameState;
import com.tg04.alienfreeway.states.MidGameMenuState;
import com.tg04.alienfreeway.viewer.menu.ShopMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ShopMenuControllerTest {

    private ShopMenuController controller;
    private ShopMenu menu;
    private Player player;
    private Game game;
    private GUI gui;

    @BeforeEach
    void setUp() {
        menu = Mockito.mock(ShopMenu.class);
        player = Mockito.mock(Player.class);
        game = Mockito.mock(Game.class);
        gui = Mockito.mock(GUI.class);

        controller = new ShopMenuController(menu, player);
    }

    @Test
    void testExecuteBuyLaser() {
        when(menu.getSelectedIndex()).thenReturn(0);
        when(player.getWallet()).thenReturn(700);
        GameState previousState = mock(GameState.class);
        when(game.getPreviousState()).thenReturn(previousState);

        controller.executeSelectedOption(game);

        verify(player, times(1)).deductWallet(700);
        verify(player, times(1)).setTemporaryWeapon(ShotType.LASER);
        verify(game, times(1)).setState(previousState);
    }

    @Test
    void testExecuteBuyMissile() {
        when(menu.getSelectedIndex()).thenReturn(1);
        when(player.getWallet()).thenReturn(1000);

        GameState previousState = mock(GameState.class);
        when(game.getPreviousState()).thenReturn(previousState);

        controller.executeSelectedOption(game);

        verify(player, times(1)).deductWallet(1000);
        verify(player, times(1)).setTemporaryWeapon(ShotType.MISSILE);
        verify(game, times(1)).setState(previousState);
    }

    @Test
    void testExecuteBuyPowerShot() {
        when(menu.getSelectedIndex()).thenReturn(2);
        when(player.getWallet()).thenReturn(1500);

        GameState previousState = mock(GameState.class);
        when(game.getPreviousState()).thenReturn(previousState);

        controller.executeSelectedOption(game);

        verify(player, times(1)).deductWallet(1500);
        verify(player, times(1)).setTemporaryWeapon(ShotType.POWER_SHOT);
        verify(game, times(1)).setState(previousState);
    }

    @Test
    void testExecuteBuyExtraLife() {
        when(menu.getSelectedIndex()).thenReturn(3);
        when(player.getWallet()).thenReturn(10000);

        GameState previousState = mock(GameState.class);
        when(game.getPreviousState()).thenReturn(previousState);

        controller.executeSelectedOption(game);

        verify(player, times(1)).deductWallet(10000);
        verify(player, times(1)).buyExtraLife();
        verify(game, times(1)).setState(previousState);
    }

    @Test
    void testExecuteInsufficientFunds() {
        when(menu.getSelectedIndex()).thenReturn(0);
        when(player.getWallet()).thenReturn(600);

        GameState previousState = mock(GameState.class);
        when(game.getPreviousState()).thenReturn(previousState);

        controller.executeSelectedOption(game);

        verify(player, never()).deductWallet(anyInt());
        verify(game, never()).setState(previousState);
    }

    @Test
    void testStepUp() {
        controller.step(game, GUI.ACTION.UP, 0);

        verify(menu, times(1)).previousEntry();
    }

    @Test
    void testStepDown()  {
        controller.step(game, GUI.ACTION.DOWN, 0);

        verify(menu, times(1)).nextEntry();
    }

    @Test
    void testStepSelect() {
        when(menu.getSelectedIndex()).thenReturn(0);
        when(player.getWallet()).thenReturn(700);

        GameState previousState = mock(GameState.class);
        when(game.getPreviousState()).thenReturn(previousState);

        controller.step(game, GUI.ACTION.SELECT, 0);

        verify(player, times(1)).deductWallet(700);
        verify(game, times(1)).setState(previousState);
    }

    @Test
    void testStepPause() {
        controller.step(game, GUI.ACTION.PAUSE, 0);

        verify(game, times(1)).setState(any(MidGameMenuState.class), eq(false));
    }

    @Test
    void testRender() throws IOException {
        ShopMenuViewer menuViewer = mock(ShopMenuViewer.class);

        ShopMenuController controller = new ShopMenuController(menu, player, menuViewer);

        controller.render(gui);

        verify(menuViewer, times(1)).draw(gui);
    }

}


