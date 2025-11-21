package com.tg04.alienfreeway.model.game.elements;

public enum ShotType {
    BULLET(1, 2),
    MISSILE(5, 1),
    LASER(2, 2),
    POWER_SHOT(3, 2);

    private final int damage;
    private final int speed;

    ShotType(int damage, int speed) {
        this.damage = damage;
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }
}