package com.tg04.alienfreewaytesting.viewer.menu;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.menu.Menu;
import com.tg04.alienfreeway.viewer.menu.MidGameMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MidGameMenuViewerTest {

    private GUI gui;
    private MidGameMenuViewer midGameMenuViewer;

    @BeforeEach
    public void setUp() {
        gui = mock(GUI.class);

        Menu menu = mock(Menu.class);
        when(menu.getNumberEntries()).thenReturn(3);
        when(menu.getEntry(0)).thenReturn("Resume");
        when(menu.getEntry(1)).thenReturn("Restart");
        when(menu.getEntry(2)).thenReturn("Quit");
        when(menu.isSelected(0)).thenReturn(true);
        when(menu.isSelected(1)).thenReturn(false);
        when(menu.isSelected(2)).thenReturn(false);

        midGameMenuViewer = new MidGameMenuViewer(menu);
    }

    @Test
    public void testDrawTitle() {
        midGameMenuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(new Position(30, 7), "[ ALIEN FREE WAY ]", "#FFFFFF", "#FF0000");
    }

    @Test
    public void testDrawOptions() {
        midGameMenuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(new Position(30, 7), "[ ALIEN FREE WAY ]", "#FFFFFF", "#FF0000");
        verify(gui).drawText(new Position(33, 9), "-> Resume", "#FFDD00");
        verify(gui).drawText(new Position(33, 11), "   Restart", "#AAAAAA");
        verify(gui).drawText(new Position(33, 13), "   Quit", "#AAAAAA");
        verifyNoMoreInteractions(gui);
    }
}
