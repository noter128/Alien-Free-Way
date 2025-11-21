package com.tg04.alienfreeway.states;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.controller.game.RoadController;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.model.game.road.RoadBuilder;

import java.io.IOException;

public class GameState implements State {
    private final RoadController roadController;

    public GameState() {
        RoadBuilder roadBuilder = new RoadBuilder();
        Road road = roadBuilder.createRoad();
        this.roadController = new RoadController(road);
    }

    @Override
    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        roadController.step(game, action, time);
        roadController.render(gui);
    }
}
