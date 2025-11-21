package com.tg04.alienfreewaytesting.viewer.menu;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.menu.Menu;
import com.tg04.alienfreeway.viewer.menu.MainMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MainMenuViewerTest {

    private GUI gui;
    private MainMenuViewer mainMenuViewer;

    @BeforeEach
    public void setUp() {
        gui = mock(GUI.class);

        Menu menu = mock(Menu.class);
        when(menu.getNumberEntries()).thenReturn(2);
        when(menu.getEntry(0)).thenReturn("Play");
        when(menu.getEntry(1)).thenReturn("Quit");
        when(menu.isSelected(0)).thenReturn(true);
        when(menu.isSelected(1)).thenReturn(false);

        mainMenuViewer = new MainMenuViewer(menu);
    }

    @Test
    public void testDrawTitle() {
        mainMenuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(new Position(30, 7), "[ ALIEN FREE WAY ]", "#FFFFFF", "#FF0000");
    }

    @Test
    public void testDrawOptions() {
        mainMenuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(eq(new Position(30, 7)), eq("[ ALIEN FREE WAY ]"), eq("#FFFFFF"), eq("#FF0000"));
        verify(gui).drawText(eq(new Position(34, 10)), eq("-> Play"), eq("#FFDD00"));
        verify(gui).drawText(eq(new Position(34, 12)), eq("   Quit"), eq("#AAAAAA"));
        verifyNoMoreInteractions(gui);
    }
}
