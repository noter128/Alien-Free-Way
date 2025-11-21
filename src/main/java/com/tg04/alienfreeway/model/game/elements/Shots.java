package com.tg04.alienfreeway.model.game.elements;

public class Shots extends Element {
    private final int damage;
    private final int speed;
    private final ShotType type;

    public Shots(int x, int y, ShotType type) {
        super(x, y);
        this.type = type;
        this.damage = type.getDamage();
        this.speed = type.getSpeed();
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public ShotType getType() {
        return type;
    }
}