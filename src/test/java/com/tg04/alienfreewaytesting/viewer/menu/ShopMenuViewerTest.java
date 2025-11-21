package com.tg04.alienfreewaytesting.viewer.menu;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.menu.Menu;
import com.tg04.alienfreeway.viewer.menu.ShopMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ShopMenuViewerTest {

    private GUI gui;
    private ShopMenuViewer shopMenuViewer;

    @BeforeEach
    public void setUp() {
        gui = mock(GUI.class);

        Menu menu = mock(Menu.class);
        when(menu.getNumberEntries()).thenReturn(4);
        when(menu.getEntry(0)).thenReturn("Laser - 700 coins");
        when(menu.getEntry(1)).thenReturn("Missile - 1000 coins");
        when(menu.getEntry(2)).thenReturn("Power Shot - 1500 coins");
        when(menu.getEntry(3)).thenReturn("Extra Life - 10 000 coins");
        when(menu.isSelected(0)).thenReturn(true);
        when(menu.isSelected(1)).thenReturn(false);
        when(menu.isSelected(2)).thenReturn(false);
        when(menu.isSelected(3)).thenReturn(false);

        Player player = mock(Player.class);
        when(player.getWallet()).thenReturn(1500);

        shopMenuViewer = new ShopMenuViewer(menu, player);
    }

    @Test
    public void testDrawBox() {
        shopMenuViewer.drawElements(gui);

        verify(gui).drawBox(new Position(19, 7), 40, 11, "#FFFFFF");
    }

    @Test
    public void testDrawTitle() {
        shopMenuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(new Position(34, 7), "SHOP MENU", "#00FF00", "#FF0000");
    }

    @Test
    public void testDrawOptions() {
        shopMenuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(new Position(34, 7), "SHOP MENU", "#00FF00", "#FF0000");
        verify(gui).drawText(new Position(25, 9), "-> Laser - 700 coins", "#FFDD00");
        verify(gui).drawText(new Position(25, 11), "   Missile - 1000 coins", "#AAAAAA");
        verify(gui).drawText(new Position(25, 13), "   Power Shot - 1500 coins", "#AAAAAA");
        verify(gui).drawText(new Position(25, 15), "   Extra Life - 10 000 coins", "#AAAAAA");
    }

    @Test
    public void testDrawWallet() {
        shopMenuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(new Position(34, 7), "SHOP MENU", "#00FF00", "#FF0000");
        verify(gui).drawText(new Position(34, 18), "Wallet: 1500", "#00FF00");
    }

    @Test
    public void testNoExtraDrawCalls() {
        shopMenuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(
                eq(new Position(34, 7)),
                eq("SHOP MENU"),
                eq("#00FF00"),
                eq("#FF0000")
        );

        verify(gui).drawBox(
                eq(new Position(19, 7)),
                eq(40),
                eq(11),
                eq("#FFFFFF")
        );

        verify(gui).drawText(eq(new Position(25, 9)), eq("-> Laser - 700 coins"), eq("#FFDD00"));
        verify(gui).drawText(eq(new Position(25, 11)), eq("   Missile - 1000 coins"), eq("#AAAAAA"));
        verify(gui).drawText(eq(new Position(25, 13)), eq("   Power Shot - 1500 coins"), eq("#AAAAAA"));
        verify(gui).drawText(eq(new Position(25, 15)), eq("   Extra Life - 10 000 coins"), eq("#AAAAAA"));
        verify(gui).drawText(eq(new Position(34, 18)), eq("Wallet: 1500"), eq("#00FF00"));
        verifyNoMoreInteractions(gui);
    }
}
