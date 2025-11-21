package com.tg04.alienfreewaytesting.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.viewer.game.PlayerViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PlayerViewerTest {
    private GUI gui;
    private PlayerViewer playerViewer;

    @BeforeEach
    public void setUp() {
        gui = mock(GUI.class);
        Player player = mock(Player.class);

        when(player.getPosition()).thenReturn(new Position(5, 10));

        playerViewer = new PlayerViewer(player);
    }

    @Test
    public void testDrawElements() {
        playerViewer.drawElements(gui);

        verify(gui).drawPlayer(new Position(5, 10));
    }
}
