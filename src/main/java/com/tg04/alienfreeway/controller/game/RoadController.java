package com.tg04.alienfreeway.controller.game;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.road.Road;
import com.tg04.alienfreeway.states.GameOverState;
import com.tg04.alienfreeway.states.MidGameMenuState;
import com.tg04.alienfreeway.viewer.game.RoadElementsViewer;
import com.tg04.alienfreeway.viewer.game.RoadViewer;

import java.io.IOException;

public class RoadController extends GameController {
     private final PlayerController playerController;
     private final EnemyController enemyController;
     private final PowerUpsController powerUpsController;
     private final ShotsController shotsController;

    private final RoadViewer roadViewer;
    private final RoadElementsViewer roadElementsViewer;

    public RoadController(Road road) {
        super(road);
        this.playerController = new PlayerController(road);
        this.enemyController = new EnemyController(road);
        this.powerUpsController = new PowerUpsController(road);
        this.shotsController = new ShotsController(road);

        this.roadViewer = new RoadViewer(road);
        this.roadElementsViewer = new RoadElementsViewer(road);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time){
        Player player = getModel().getPlayer();
        if (action == GUI.ACTION.QUIT || player.getHealth() <= 0) {
            game.setState(new GameOverState(player));
            return;
        } else if (action == GUI.ACTION.PAUSE) {
            game.setState(new MidGameMenuState(player));
            return;
        }

        playerController.step(game, action, time);
        enemyController.step(game, action, time);
        powerUpsController.step(game, action, time);
        shotsController.step(game, action, time);
    }

    @Override
    public void render(GUI gui) throws IOException {
        gui.clear();
        roadViewer.draw(gui);
        roadElementsViewer.draw(gui);
        gui.refresh();
    }
}