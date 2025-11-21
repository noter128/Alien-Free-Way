package com.tg04.alienfreeway.controller.menu;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.menu.MidGameMenu;
import com.tg04.alienfreeway.sound.SoundPlayer;
import com.tg04.alienfreeway.states.GameState;
import com.tg04.alienfreeway.states.ShopMenuState;
import com.tg04.alienfreeway.viewer.menu.MenuViewer;
import com.tg04.alienfreeway.viewer.menu.MidGameMenuViewer;

import java.io.IOException;

public class MidGameMenuController extends MenuController {
    private final MenuViewer menuViewer;
    private final Player player;

    public MidGameMenuController(MidGameMenu model, Player player) {
        this(model, player, new MidGameMenuViewer(model));
    }

    public MidGameMenuController(MidGameMenu model, Player player, MenuViewer menuViewer) {
        super(model);
        this.menuViewer = menuViewer;
        this.player = player;
    }

    public void executeSelectedOption(Game game) {
        MidGameMenu menu = (MidGameMenu) getModel();
        if (menu.isSelectedSpecific("resume")) {
            if (game.getPreviousState() != null) {
                game.setState(game.getPreviousState(), false);
            } else {
                System.out.println("No previous state set!");
            }
        } else if (menu.isSelectedSpecific("restart")) {
            game.setState(new GameState());
        } else if (menu.isSelectedSpecific("shop")) {
            game.setState(new ShopMenuState(player), false);
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
            case PAUSE:
                SoundPlayer.playSound("sounds/menu_pause.wav");
                game.setState(game.getPreviousState(), false);
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
