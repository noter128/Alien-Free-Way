package com.tg04.alienfreeway.model.game.elements.enemies;

import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Enemy;

import java.util.Random;

public class ZigZagEnemy extends Enemy {
    private boolean movingDown;

    public ZigZagEnemy(int x, int y) {
        super(x, y, 3, 1, 20, 2);
        Random random = new Random();
        this.movingDown = random.nextBoolean();
    }

    @Override
    public void setPosition(Position newPosition) {
        int newY = newPosition.getY();
        if (movingDown) {
            newY++;
            if (newY >= 22) movingDown = false;
        } else {
            newY--;
            if (newY <= 1) movingDown = true;
        }
        super.setPosition(new Position(newPosition.getX(), newY));
    }

    public void moveZigZag() {
        int newY = getPosition().getY();
        if (movingDown) {
            newY++;
            if (newY >= 22) movingDown = false;
        } else {
            newY--;
            if (newY <= 1) movingDown = true;
        }
        setPosition(new Position(getPosition().getX() - getSpeed(), newY));
    }

    @Override
    public String toString() {
        return "ZigZagEnemy at " + getPosition() + " [Speed=" + getSpeed() + ", Health=" + getEnemyHealth() + "]";
    }
}
