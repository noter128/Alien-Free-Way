package com.tg04.alienfreewaytesting.controller;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.Controller;
import com.tg04.alienfreeway.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ControllerTest {

    private Game mockGame;
    private GUI mockGUI;
    private Controller<String> controller;

    private static class TestController extends Controller<String> {
        public TestController(String model) {
            super(model);
        }

        @Override
        public void render(GUI gui) {
            gui.drawText(null, "Rendering: " + getModel(), "#FFFFFF");
        }

        @Override
        public void step(Game game, GUI.ACTION action, long time) {
            if (action == GUI.ACTION.UP) {
                game.setState(null);
            }
        }
    }

    @BeforeEach
    void setUp() {
        mockGame = mock(Game.class);
        mockGUI = mock(GUI.class);
        controller = new TestController("TestModel");
    }

    @Test
    void testGetModel() {
        assertEquals("TestModel", controller.getModel());
    }

    @Test
    void testRenderCallsGui() throws IOException {
        controller.render(mockGUI);
        verify(mockGUI, times(1)).drawText(null, "Rendering: TestModel", "#FFFFFF");
    }

    @Test
    void testStepChangesGameState() {
        controller.step(mockGame, GUI.ACTION.UP, 100);
        verify(mockGame, times(1)).setState(null);
    }

    @Test
    void testStepNoAction() {
        controller.step(mockGame, GUI.ACTION.NONE, 100);
        verify(mockGame, never()).setState(any());
    }
}
