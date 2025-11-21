package com.tg04.alienfreeway.states;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.menu.ShopMenuController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.menu.ShopMenu;

import java.io.IOException;

public class ShopMenuState implements State {
    private final ShopMenuController shopMenuController;

    public ShopMenuState(Player player) {
        ShopMenu shopMenu = new ShopMenu();

        this.shopMenuController = new ShopMenuController(shopMenu, player);
    }

    public ShopMenuState(ShopMenuController shopMenuController) {
        this.shopMenuController = shopMenuController;
    }

    @Override
    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        shopMenuController.step(game, action, time);
        gui.clear();
        shopMenuController.render(gui);
    }
}
