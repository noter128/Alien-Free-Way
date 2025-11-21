package com.tg04.alienfreeway.model.game.elements.enemies;

import com.tg04.alienfreeway.model.game.elements.Enemy;

public class BasicEnemy extends Enemy {
    public BasicEnemy(int x, int y) {
        super(x, y, 4, 1, 1, 2);
    }

    @Override
    public String toString() {
        return "BasicEnemy at " + getPosition() + " [Speed=" + getSpeed() + ", Health=" + getEnemyHealth() + "]";
    }
}
