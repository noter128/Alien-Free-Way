package com.tg04.alienfreeway.controller;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void render(GUI gui) throws IOException;


    public abstract void step(Game game, GUI.ACTION action, long time);
}
