package com.tg04.alienfreeway.states;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.menu.MainMenuController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.menu.MainMenu;


import java.io.IOException;

public class MainMenuState implements State {
    private final MainMenuController mainMenuController;

    public MainMenuState() {
        MainMenu mainMenu = new MainMenu();
        this.mainMenuController = new MainMenuController(mainMenu);
    }

    @Override
    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        mainMenuController.step(game, action, time);
        mainMenuController.render(gui);
    }


}