package com.tg04.alienfreewaytesting.controller.game;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.game.RoadController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.states.GameOverState;
import com.tg04.alienfreeway.states.MidGameMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class RoadControllerTest {
    private Road mockRoad;
    private Game mockGame;
    private GUI mockGUI;
    private Player mockPlayer;
    private RoadController roadController;

    @BeforeEach
    void setUp() {
        mockRoad = mock(Road.class);
        mockGame = mock(Game.class);
        mockGUI = mock(GUI.class);
        mockPlayer = mock(Player.class);

        when(mockRoad.getPlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getHealth()).thenReturn(10);

        roadController = new RoadController(mockRoad);
    }

    @Test
    void testStepGameOverState()  {
        when(mockPlayer.getHealth()).thenReturn(0);
        roadController.step(mockGame, GUI.ACTION.NONE, 1000);
        verify(mockGame, times(1)).setState(isA(GameOverState.class));
    }

    @Test
    void testStepMidGameMenuState()  {
        roadController.step(mockGame, GUI.ACTION.PAUSE, 1000);
        verify(mockGame, times(1)).setState(isA(MidGameMenuState.class));
    }

    @Test
    void testRenderTriggersViewers() throws IOException {
        roadController.render(mockGUI);
        verify(mockGUI, times(1)).clear();
        verify(mockGUI, atLeastOnce()).refresh();
    }

    @Test
    void testStepTriggersControllers()  {
        Player playerSpy = spy(mockPlayer);
        when(mockRoad.getPlayer()).thenReturn(playerSpy);
        roadController.step(mockGame, GUI.ACTION.NONE, 1000);
        verify(playerSpy, atLeast(1)).getHealth();
    }
}
