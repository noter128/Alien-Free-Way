package com.tg04.alienfreeway.controller.menu;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.menu.GameOverMenu;
import com.tg04.alienfreeway.states.GameState;
import com.tg04.alienfreeway.viewer.menu.GameOverMenuViewer;
import com.tg04.alienfreeway.viewer.menu.MenuViewer;

import java.io.IOException;


public class GameOverMenuController extends MenuController {
    private final MenuViewer menuViewer;
    private final  Player player;
    public GameOverMenuController(GameOverMenu model, Player player) {
        super(model);
        this.menuViewer = new GameOverMenuViewer(model, player);
        this.player = player;
    }

    public void executeSelectedOption(Game game) {
        GameOverMenu menu = (GameOverMenu) getModel();
        if (menu.isSelectedSpecific("use extra life")) {
            if (player.hasExtraLife()) {
                System.out.println("Using Extra Life...");
                player.useExtraLife();
                player.setHealth(10);

                game.setState(game.getPreviousState());
            } else {
                System.out.println("No extra lives available!");
            }
        } else if (menu.isSelectedSpecific("restart")) {
            game.setState(new GameState());
        } else if (menu.isSelectedSpecific("quit")) {
            game.setState(null);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
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
