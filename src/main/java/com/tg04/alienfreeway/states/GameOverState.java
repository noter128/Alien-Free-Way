package com.tg04.alienfreeway.states;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.menu.GameOverMenuController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.menu.GameOverMenu;

import java.io.IOException;

public class GameOverState implements State {
    private final GameOverMenuController gameOverMenuController;

    public GameOverState(Player player) {
        GameOverMenu gameOverMenu = new GameOverMenu();
        this.gameOverMenuController = new GameOverMenuController(gameOverMenu, player);
    }

    public GameOverState(GameOverMenuController gameOverMenuController) {
        this.gameOverMenuController = gameOverMenuController;
    }

    @Override
    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        gameOverMenuController.step(game, action, time);
        gui.clear();
        gameOverMenuController.render(gui);
    }
}