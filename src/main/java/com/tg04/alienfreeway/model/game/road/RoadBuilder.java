package com.tg04.alienfreeway.model.game.road;

import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.model.game.elements.Wall;
import com.tg04.alienfreeway.model.game.elements.enemies.ArmoredEnemy;
import com.tg04.alienfreeway.model.game.elements.enemies.HealingEnemy;
import com.tg04.alienfreeway.model.game.elements.enemies.StealthEnemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoadBuilder {
    private final int width = 80;
    private final int height = 24;
    private final Random random = new Random();

    public Road createRoad() {
        System.out.println("Starting road creation...");
        Road road = new Road(width, height);

        road.setPlayer(createPlayer());
        System.out.println("Player created at: " + road.getPlayer().getPosition());

        road.setEnemies(createEnemies());
        System.out.println("Enemies created: " + road.getEnemies().size());

        road.setWalls(createWalls());
        System.out.println("Walls created: " + road.getWalls().size());

        road.setPowerUps(new ArrayList<>());
        System.out.println("Power-ups initialized as empty.");

        road.setShots(new ArrayList<>());
        System.out.println("Shots list initialized");

        System.out.println("Road creation complete.");
        return road;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            walls.add(new Wall(x, 0));
            walls.add(new Wall(x, height - 1));
        }
        return walls;
    }

    private Player createPlayer() {
        return new Player(0, height / 2);
    }

    private List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        int spawnX = width - 1;

        for (int i = 0; i < 3; i++) {
            int spawnY = getRandomYPosition();
            Enemy enemy;

            if (i == 0) enemy = new ArmoredEnemy(spawnX, spawnY);
            else if (i == 1) enemy = new HealingEnemy(spawnX, spawnY);
            else enemy = new StealthEnemy(spawnX, spawnY);

            enemies.add(enemy);
        }
        return enemies;
    }

    private int getRandomYPosition() {
        return random.nextInt(height - 2) + 1;
    }
}
