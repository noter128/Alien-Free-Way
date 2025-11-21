package com.tg04.alienfreewaytesting.viewer.game;

import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.viewer.game.RoadViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class RoadViewerTest {
    private GUI gui;
    private RoadViewer roadViewer;

    @BeforeEach
    public void setUp() throws IOException {
        gui = mock(GUI.class);
        Road road = mock(Road.class);

        roadViewer = spy(new RoadViewer(road));
        doNothing().when(roadViewer).drawElements(gui);
    }

    @Test
    public void testDrawElements() throws IOException {
        roadViewer.draw(gui);

        verify(roadViewer).drawElements(gui);
    }
}
