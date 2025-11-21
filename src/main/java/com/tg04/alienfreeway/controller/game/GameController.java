package com.tg04.alienfreeway.controller.game;

import com.tg04.alienfreeway.controller.Controller;
import com.tg04.alienfreeway.model.game.road.Road;

public abstract class GameController extends Controller<Road> {
    public GameController(Road road){
        super(road);
    }
}
