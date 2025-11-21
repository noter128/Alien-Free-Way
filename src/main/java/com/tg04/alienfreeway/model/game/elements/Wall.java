package com.tg04.alienfreeway.model.game.elements;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Wall)) return false;
        Wall wall = (Wall) obj;
        return this.getPosition().equals(wall.getPosition());
    }

    @Override
    public int hashCode() {
        return getPosition().hashCode();
    }

}