package com.tg04.alienfreeway.model.game.elements.enemies;

import com.tg04.alienfreeway.model.game.elements.Element;
import com.tg04.alienfreeway.model.game.elements.Enemy;

public class StealthEnemy extends Enemy {
    private boolean visible;

    public StealthEnemy(int x, int y) {
        super(x, y, 2, 1, 30, 3);
        this.visible = false;
    }

    public void updateVisibility(Element player) {
        int dx = Math.abs(getPosition().getX() - player.getPosition().getX());
        this.visible = dx <= 5;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public void decreaseEnemyHealth() {
        if (visible) super.decreaseEnemyHealth();
    }

    @Override
    public String toString() {
        return "StealthEnemy at " + getPosition() +
                " [Speed=" + getSpeed() + ", Health=" + getEnemyHealth() +
                ", Visible=" + visible + "]";
    }
}
