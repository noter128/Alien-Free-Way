package com.tg04.alienfreeway.model.game.elements;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Player extends Element {
    private int health;
    private int wallet;

    public boolean extra_life = false;

    private boolean doubleShotSpeed = false;
    private boolean temporaryImmunity = false;
    private boolean doubleMoney = false;
    private boolean slowEnemies = false;

    private int doubleShotTimer = 0;
    private int immunityTimer = 0;
    private int doubleMoneyTimer = 0;
    private int slowEnemiesTimer = 0;

    private int score;

    private ShotType currentShotType = ShotType.BULLET;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Player(int x, int y) {
        super(x, y);
        this.health = 10;
        this.wallet = 0;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int highScore) {
        this.score = highScore;
    }

    public void decreaseHealth(int damage) {
        System.out.println("Temporary Immunity: " + temporaryImmunity);
        if (!temporaryImmunity) {
            this.health -= damage;
            if (this.health <= 0) {
                this.health = 0;
            }
            System.out.println("Player health decreased by " + damage + ". Current health: " + this.health);
        } else {
            System.out.println("Player is IMMUNE! No damage taken.");
        }
    }

    public String getCurrentShotType() {
        if (currentShotType == ShotType.BULLET) {
            return "BULLET";
        } else if (currentShotType == ShotType.MISSILE) {
            return "MISSILE";
        } else if (currentShotType == ShotType.LASER) {
            return "LASER";
        } else {
            return "POWER_SHOT";
        }
    }

    public Shots shoot() {
        int shotSpeed = doubleShotSpeed ? 2 : 1;
        return new Shots(getPosition().getX() + shotSpeed, getPosition().getY(), currentShotType);
    }

    public int getHealth() {
        return this.health;
    }

    public int getWallet() {
        return wallet;
    }

    public void addWallet(int amount) {
        if (doubleMoney) {
            this.wallet += amount * 2;
            System.out.println("DOUBLE MONEY: Wallet increased by " + (amount * 2) + ". Current wallet: " + this.wallet);
        } else {
            this.wallet += amount;
            System.out.println("Wallet increased by " + amount + ". Current wallet: " + this.wallet);
        }
    }

    public void deductWallet(int x) {
        this.wallet -= x;
    }

    public void buyExtraLife() {
        this.extra_life = true;
    }

    public void useExtraLife() {
        this.extra_life = false;
    }

    public boolean hasExtraLife() {
        return this.extra_life;
    }

    public void activatePowerUp(PowerUpType type, int duration) {
        switch (type) {
            case DOUBLE_SHOT_SPEED:
                doubleShotSpeed = true;
                doubleShotTimer = duration;
                System.out.println("Power-Up: DOUBLE SHOT SPEED Activated!");
                break;
            case TEMPORARY_IMMUNITY:
                temporaryImmunity = true;
                immunityTimer = duration;
                System.out.println("Power-Up: TEMPORARY IMMUNITY Activated!");
                break;
            case DOUBLE_MONEY:
                doubleMoney = true;
                doubleMoneyTimer = duration;
                System.out.println("Power-Up: DOUBLE MONEY Activated!");
                break;
            case SLOW_ENEMIES:
                slowEnemies = true;
                slowEnemiesTimer = duration;
                System.out.println("Power-Up: SLOW ENEMIES Activated!");
                break;
            case HEAL:
                int new_health = health + health/2;
                if(new_health >= 10) {
                    this.health = 10;
                }
                this.health += getHealth()/2;
                System.out.println("Power-Up: FULL HEALTH Restored to Full Health!");
                break;
        }
    }

    public void updatePowerUpTimers() {
        if (doubleShotSpeed && --doubleShotTimer <= 0) {
            doubleShotSpeed = false;
            System.out.println("Power-Up: DOUBLE SHOT SPEED Expired.");
        }
        if (temporaryImmunity && --immunityTimer <= 0) {
            temporaryImmunity = false;
            System.out.println("Power-Up: TEMPORARY IMMUNITY Expired.");
        }
        if (doubleMoney && --doubleMoneyTimer <= 0) {
            doubleMoney = false;
            System.out.println("Power-Up: DOUBLE MONEY Expired.");
        }
        if (slowEnemies && --slowEnemiesTimer <= 0) {
            slowEnemies = false;
            System.out.println("Power-Up: SLOW ENEMIES Expired.");
        }
    }

    public boolean hasDoubleShotSpeed() {
        return doubleShotSpeed;
    }

    public boolean hasTemporaryImmunity() {
        return temporaryImmunity;
    }

    public boolean hasDoubleMoney() {
        return doubleMoney;
    }

    public boolean hasSlowEnemies() {
        return slowEnemies;
    }

    public void setHealth(int health) {
        this.health = Math.min(10, health);
        System.out.println("Player Health: " + this.health);
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    public void setTemporaryWeapon(ShotType newWeapon) {
        this.currentShotType = newWeapon;
        System.out.println("Weapon changed to: " + newWeapon);

        scheduler.schedule(() -> {
            this.currentShotType = ShotType.BULLET;
            System.out.println("Weapon reverted to default: BULLET");
        }, 10, TimeUnit.SECONDS);
    }
}