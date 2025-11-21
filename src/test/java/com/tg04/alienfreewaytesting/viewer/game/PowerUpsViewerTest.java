package com.tg04.alienfreewaytesting.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.PowerUpType;
import com.tg04.alienfreeway.model.game.elements.PowerUps;
import com.tg04.alienfreeway.viewer.game.PowerUpsViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class PowerUpsViewerTest {
    private GUI gui;
    private PowerUpsViewer powerUpsViewer;

    @BeforeEach
    public void setUp() {
        gui = mock(GUI.class);
        PowerUps powerUp = mock(PowerUps.class);

        when(powerUp.getPosition()).thenReturn(new Position(10, 15));
        when(powerUp.getType()).thenReturn(PowerUpType.TEMPORARY_IMMUNITY);

        powerUpsViewer = new PowerUpsViewer(List.of(powerUp));
    }

    @Test
    public void testDrawElements() {
        powerUpsViewer.drawElements(gui);

        verify(gui).drawPowerUp(new Position(10, 15), PowerUpType.TEMPORARY_IMMUNITY);
    }
}
