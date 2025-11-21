package com.tg04.alienfreewaytesting;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.LanternaGUI;
import com.tg04.alienfreeway.states.MainMenuState;
import com.tg04.alienfreeway.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest {
    private Game game;
    private LanternaGUI mockGUI;

    @BeforeEach
    void setUp() {
        mockGUI = mock(LanternaGUI.class);
        game = new Game(mockGUI);
    }

    @Test
    void testSetStateUpdatesCorrectly() {
        State mockState = mock(State.class);
        game.setState(mockState);

        assertEquals(mockState, game.getState(), "State should update correctly.");
        assertTrue(game.getPreviousState() instanceof MainMenuState, "Previous state should initially be MainMenuState.");
    }

    @Test
    void testGeneralStateTransitions() {
        State firstState = mock(State.class);
        State secondState = mock(State.class);

        game.setState(firstState);
        assertEquals(firstState, game.getState(), "State should be set to the first state.");
        assertTrue(game.getPreviousState() instanceof MainMenuState, "Previous state should initially be MainMenuState.");

        game.setState(secondState);
        assertEquals(secondState, game.getState(), "State should transition to the second state.");
        assertEquals(firstState, game.getPreviousState(), "Previous state should now be the first state.");
    }

    @Test
    void testTransitionToNullState() {
        State mockState = mock(State.class);

        game.setState(mockState);
        assertEquals(mockState, game.getState(), "Initial state should be set.");

        game.setState(null);
        assertNull(game.getState(), "State should be null after setting a null state.");
        assertEquals(mockState, game.getPreviousState(), "Previous state should remain the last valid state.");
    }

    @Test
    void testMultipleTransitions() {
        State state1 = mock(State.class);
        State state2 = mock(State.class);
        State state3 = mock(State.class);

        game.setState(state1);
        assertEquals(state1, game.getState(), "State should be set to state1.");
        assertTrue(game.getPreviousState() instanceof MainMenuState, "Previous state should initially be MainMenuState.");

        game.setState(state2);
        assertEquals(state2, game.getState(), "State should transition to state2.");
        assertEquals(state1, game.getPreviousState(), "Previous state should now be state1.");

        game.setState(state3);
        assertEquals(state3, game.getState(), "State should transition to state3.");
        assertEquals(state2, game.getPreviousState(), "Previous state should now be state2.");
    }

    @Test
    void testGUIResourceManagement() throws Exception {
        State mockState = mock(State.class);
        game.setState(mockState);

        Thread gameThread = new Thread(() -> {
            try {
                game.start();
            } catch (IOException ignored) {
            }
        });

        gameThread.start();

        Thread.sleep(100);

        game.setState(null);
        gameThread.join();

        verify(mockGUI, times(1)).close();

        assertNull(game.getState(), "Game state should be null after termination.");
    }


    @Test
    void testStateStepCalledDuringGameLoop() throws Exception {
        State mockState = mock(State.class);
        game.setState(mockState);

        Thread gameThread = new Thread(() -> {
            try {
                game.start();
            } catch (IOException ignored) {
            }
        });

        gameThread.start();

        Thread.sleep(200);

        verify(mockState, atLeastOnce()).step(eq(game), eq(mockGUI), anyLong());

        game.setState(null);
        gameThread.join();
    }

    @Test
    void testStartEndsWhenStateIsNull() throws Exception {
        State mockState = mock(State.class);
        game.setState(mockState);

        Thread gameThread = new Thread(() -> {
            try {
                game.start();
            } catch (IOException ignored) {
            }
        });

        gameThread.start();

        Thread.sleep(100);
        game.setState(null);

        gameThread.join();
        assertNull(game.getState(), "Game state should be null after termination.");
    }
}