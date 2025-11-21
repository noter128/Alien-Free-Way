package com.tg04.alienfreewaytesting.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Shots;
import com.tg04.alienfreeway.model.game.elements.ShotType;
import com.tg04.alienfreeway.viewer.game.ShotsViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ShotsViewerTest {
    private GUI gui;
    private ShotsViewer shotsViewer;

    @BeforeEach
    public void setUp() {
        gui = mock(GUI.class);

        Shots shot1 = mock(Shots.class);
        when(shot1.getPosition()).thenReturn(new Position(10, 5));
        when(shot1.getType()).thenReturn(ShotType.BULLET);

        Shots shot2 = mock(Shots.class);
        when(shot2.getPosition()).thenReturn(new Position(15, 8));
        when(shot2.getType()).thenReturn(ShotType.LASER);

        List<Shots> shots = Arrays.asList(shot1, shot2);
        shotsViewer = new ShotsViewer(shots);
    }

    @Test
    public void testDrawElements() {
        shotsViewer.drawElements(gui);

        verify(gui).drawShot(new Position(10, 5), ShotType.BULLET);
        verify(gui).drawShot(new Position(15, 8), ShotType.LASER);
        verifyNoMoreInteractions(gui);
    }
}