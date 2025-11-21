package com.tg04.alienfreeway.model.game.elements;

public class Enemy extends Element {
    private int health;
    private int speed;
    private final int value;
    private final int damage;
    public Enemy(int x, int y, int health, int speed, int value, int damage) {
        super(x, y);
        this.health = health;
        this.speed = speed;
        this.value = value;
        this.damage = damage;
    }

    public void decreaseEnemyHealth() {
        this.health--;
    }

    public void decreaseEnemyHealth(int x){
        this.health -= x;
    }

    public void increaseEnemyHealth(int x){
        this.health += x;
    }

    public int getEnemyHealth() {
        return this.health;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getValue() {
        return this.value;
    }

    public int getDamage() {
        return this.damage;
    }
}
