package com.tg04.alienfreeway.model.game.elements;

import com.tg04.alienfreeway.model.Position;

public class PowerUps extends Element {
    private final PowerUpType type;
    private final int duration;

    public PowerUps(Position position, PowerUpType type, int duration) {
        super(position.getX(), position.getY());
        this.type = type;
        this.duration = Math.max(0, duration);
    }

    public PowerUpType getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }
}