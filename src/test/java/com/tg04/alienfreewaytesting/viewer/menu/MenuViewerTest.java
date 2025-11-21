package com.tg04.alienfreewaytesting.viewer.menu;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.menu.Menu;
import com.tg04.alienfreeway.viewer.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MenuViewerTest {

    private GUI gui;
    private Menu menu;
    private MenuViewer menuViewer;

    @BeforeEach
    public void setUp() {
        gui = mock(GUI.class);

        menu = mock(Menu.class);
        when(menu.getNumberEntries()).thenReturn(3);
        when(menu.getEntry(0)).thenReturn("Option 1");
        when(menu.getEntry(1)).thenReturn("Option 2");
        when(menu.getEntry(2)).thenReturn("Option 3");
        when(menu.isSelected(0)).thenReturn(true);
        when(menu.isSelected(1)).thenReturn(false);
        when(menu.isSelected(2)).thenReturn(false);

        menuViewer = new MenuViewer(menu, "Test Menu", new Position(10, 5), "#FFFFFF", "#FF0000") {
            @Override
            protected int getInitialY() {
                return 7;
            }

            @Override
            protected int getOptionsX() {
                return 15;
            }
        };
    }

    @Test
    public void testDrawTitle() {
        menuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(new Position(10, 5), "Test Menu", "#FFFFFF", "#FF0000");
    }

    @Test
    public void testDrawOptions() {
        menuViewer.drawElements(gui);

        verify(gui).drawTitleWithHighlight(new Position(10, 5), "Test Menu", "#FFFFFF", "#FF0000");
        verify(gui).drawText(new Position(15, 7), "-> Option 1", "#FFDD00");
        verify(gui).drawText(new Position(15, 9), "   Option 2", "#AAAAAA");
        verify(gui).drawText(new Position(15, 11), "   Option 3", "#AAAAAA");
        verifyNoMoreInteractions(gui);
    }
}