package com.tg04.alienfreeway.states;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;

import java.io.IOException;

public interface State {
    void step(Game game, GUI gui, long time) throws IOException;
}