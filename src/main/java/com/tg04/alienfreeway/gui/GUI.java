package com.tg04.alienfreeway.gui;

import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.model.game.elements.PowerUpType;
import com.tg04.alienfreeway.model.game.elements.ShotType;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawPlayer(Position position);

    void drawEnemy(Position position, Enemy enemy);

    void drawText(Position position, String text, String color);

    void drawShot(Position position, ShotType shotType);

    void drawPowerUp(Position position, PowerUpType type);

    void drawTitleWithHighlight(Position position, String title, String color1, String color2);

    void clear();

    void refresh() throws IOException;

    void drawBox(Position topLeft, int width, int height, String color);

    void drawBackground(int width, int height, int health, int wallet, String ShotType, int highScore, boolean b);

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, PAUSE, QUIT, SELECT}
}