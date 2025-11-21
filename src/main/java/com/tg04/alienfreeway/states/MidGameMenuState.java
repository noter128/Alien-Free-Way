package com.tg04.alienfreeway.states;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.menu.MidGameMenuController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.menu.MidGameMenu;

import java.io.IOException;


public class MidGameMenuState implements State {
    private final MidGameMenuController midGameMenuController;

    public MidGameMenuState(Player player) {
        MidGameMenu midGameMenu = new MidGameMenu();
        this.midGameMenuController = new MidGameMenuController(midGameMenu, player);
    }

    public MidGameMenuState(MidGameMenuController midGameMenuController) {
        this.midGameMenuController = midGameMenuController;
    }

    @Override
    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        midGameMenuController.step(game, action, time);
        gui.clear();
        midGameMenuController.render(gui);
    }
}