package com.tg04.alienfreewaytesting.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.viewer.game.BackgroundViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BackgroundViewerTest {
    private GUI gui;
    private BackgroundViewer backgroundViewer;

    @BeforeEach
    public void setUp() {
        gui = mock(GUI.class);
        Road road = mock(Road.class);
        Player player = mock(Player.class);

        when(road.getPlayer()).thenReturn(player);
        when(player.getHealth()).thenReturn(10);
        when(player.getWallet()).thenReturn(100);
        when(player.getCurrentShotType()).thenReturn("BULLET");
        when(player.getScore()).thenReturn(500);
        when(player.hasExtraLife()).thenReturn(true);

        backgroundViewer = new BackgroundViewer(road);
    }

    @Test
    public void testDrawElements() {
        backgroundViewer.drawElements(gui);

        verify(gui).drawBackground(80, 24, 10, 100, "BULLET", 500, true);
    }
}
