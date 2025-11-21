package com.tg04.alienfreeway.controller.menu;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.menu.MainMenu;
import com.tg04.alienfreeway.sound.SoundPlayer;
import com.tg04.alienfreeway.states.GameState;
import com.tg04.alienfreeway.viewer.menu.MainMenuViewer;
import com.tg04.alienfreeway.viewer.menu.MenuViewer;

import java.io.IOException;

public class MainMenuController extends MenuController {
    private final MenuViewer menuViewer;

    public MainMenuController(MainMenu model) {
        this(model, new MainMenuViewer(model));
    }

    public MainMenuController(MainMenu model, MenuViewer menuViewer) {
        super(model);
        this.menuViewer = menuViewer;
    }

    public void executeSelectedOption(Game game) {
        MainMenu menu = (MainMenu) getModel();
        if (menu.isSelectedSpecific("play")) {
            game.setState(new GameState());
        } else if (menu.isSelectedSpecific("quit")) {
            game.setState(null);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        switch (action) {
            case UP:
                SoundPlayer.playSound("sounds/menu_navigate.wav");
                getModel().previousEntry();
                break;
            case DOWN:
                SoundPlayer.playSound("sounds/menu_navigate.wav");
                getModel().nextEntry();
                break;
            case SELECT:
                SoundPlayer.playSound("sounds/menu_select.wav");
                executeSelectedOption(game);
                break;
            default:
                break;
        }
    }

    @Override
    public void render(GUI gui) throws IOException {
        if (menuViewer != null) {
            menuViewer.draw(gui);
        }
    }
}

