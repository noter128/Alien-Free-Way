package com.tg04.alienfreeway;

import com.tg04.alienfreeway.gui.LanternaGUI;
import com.tg04.alienfreeway.sound.SoundPlayer;
import com.tg04.alienfreeway.states.MainMenuState;
import com.tg04.alienfreeway.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;

import java.util.logging.Logger;

public class Game {
    private final LanternaGUI gui;
    private State state;
    private State previousState;
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());

    public Game() throws IOException, FontFormatException, URISyntaxException {
        this(new LanternaGUI(80, 24));
    }

    public Game(LanternaGUI gui) {
        this.gui = gui;
        this.state = new MainMenuState();
        System.out.println("Game initialized with MainMenuState");
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        new Game().start();
    }

    public void setState(State state) {
        setState(state, true);
    }

    public void setState(State state, boolean savePrevious) {
        if (savePrevious && this.state != null) {
            this.previousState = this.state;
        }
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public State getPreviousState() {
        return this.previousState;
    }

    public void start() throws IOException {
        int FPS = 50;
        int frameTime = 1000 / FPS;

        try {
            SoundPlayer.playBackgroundMusic("sounds/background.wav");

            while (this.state != null) {
                long startTime = System.currentTimeMillis();

                state.step(this, gui, startTime);

                long elapsedTime = System.currentTimeMillis() - startTime;
                long sleepTime = frameTime - elapsedTime;

                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.WARNING, "Thread was interrupted", e);
            Thread.currentThread().interrupt();
        } finally {
            SoundPlayer.stopBackgroundMusic();
            gui.close();
        }
    }
}