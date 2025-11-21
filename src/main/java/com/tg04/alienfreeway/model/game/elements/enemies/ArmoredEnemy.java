package com.tg04.alienfreeway.model.game.elements.enemies;

import com.tg04.alienfreeway.model.game.elements.Enemy;

public class ArmoredEnemy extends Enemy {
    private int armor;

    public ArmoredEnemy(int x, int y) {
        super(x, y, 5, 1, 20, 3);
        this.armor = 10;
    }

    @Override
    public void decreaseEnemyHealth() {
        if (armor > 0) {
            armor--;
        } else {
            super.decreaseEnemyHealth();
        }
    }
}
