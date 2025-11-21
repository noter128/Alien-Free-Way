package com.tg04.alienfreewaytesting.viewer.menu;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.menu.Menu;
import com.tg04.alienfreeway.viewer.menu.GameOverMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GameOverMenuViewerTest {

    private GUI gui;
    private Menu menu;
    private GameOverMenuViewer gameOverMenuViewer;

    @BeforeEach
    public void setUp() {
        gui = mock(GUI.class);

        menu = mock(Menu.class);
        when(menu.getNumberEntries()).thenReturn(3);
        when(menu.getEntry(0)).thenReturn("Use Extra Life");
        when(menu.getEntry(1)).thenReturn("Restart");
        when(menu.getEntry(2)).thenReturn("Quit");
        when(menu.isSelected(0)).thenReturn(true);
        when(menu.isSelected(1)).thenReturn(false);
        when(menu.isSelected(2)).thenReturn(false);

        Player player = mock(Player.class);
        when(player.getScore()).thenReturn(1500);
        gameOverMenuViewer = new GameOverMenuViewer(menu, player);
    }

    @Test
    public void testDrawTitleOnly() {
        gameOverMenuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(new Position(30, 7), "!!! GAME OVER !!!", "#FF0000", "#FFFFFF");
    }


    @Test
    public void testDrawMenuOptions() {
        gameOverMenuViewer.drawElements(gui);

        verify(gui).drawText(new Position(30, 10), "-> Use Extra Life", "#FFDD00");
        verify(gui).drawText(new Position(30, 12), "   Restart", "#AAAAAA");
        verify(gui).drawText(new Position(30, 14), "   Quit", "#AAAAAA");
    }



    @Test
    public void testDrawHighScoreOnly() {
        gameOverMenuViewer.drawElements(gui);

        verify(gui).drawText(new Position(30, 17), "High Score: 1500", "#00FF00");
    }

    @Test
    public void testDrawOptionsWithEmptyMenu() {
        when(menu.getNumberEntries()).thenReturn(0);

        gameOverMenuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(
                eq(new Position(30, 7)),
                eq("!!! GAME OVER !!!"),
                eq("#FF0000"),
                eq("#FFFFFF")
        );

        verify(gui).drawText(
                eq(new Position(30, 17)),
                eq("High Score: 1500"),
                eq("#00FF00")
        );

        verifyNoMoreInteractions(gui);
    }
}