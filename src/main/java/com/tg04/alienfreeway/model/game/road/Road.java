package com.tg04.alienfreeway.model.game.road;

import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.*;

import java.util.List;

public class Road {
    private final int width;
    private final int height;

    private Player player;

    private List<Enemy> enemies;
    private List<Wall> walls;
    private List<PowerUps> powerUps;
    private List<Shots> shots;

    public Road(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> monsters) {
        this.enemies = monsters;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setPowerUps(List<PowerUps> powerUps) {
        this.powerUps = powerUps;
    }

    public List<PowerUps> getPowerUps() {return powerUps;}

    public List<Shots> getShots() {
        return shots;
    }

    public void setShots(List<Shots> shots) {this.shots = shots;}

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }
}