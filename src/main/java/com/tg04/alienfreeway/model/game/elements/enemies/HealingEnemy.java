package com.tg04.alienfreeway.model.game.elements.enemies;

import com.tg04.alienfreeway.model.game.elements.Enemy;

public class HealingEnemy extends Enemy {
    private int shotsTaken;

    public HealingEnemy(int x, int y) {
        super(x, y, 5, 1, 25, 1);
        this.shotsTaken = 0;
    }

    @Override
    public void decreaseEnemyHealth() {
        shotsTaken++;
        if (shotsTaken == 2) {
            super.decreaseEnemyHealth();
            super.increaseEnemyHealth(1);
            shotsTaken = 0;
        } else {
            super.decreaseEnemyHealth();
        }
    }
}
