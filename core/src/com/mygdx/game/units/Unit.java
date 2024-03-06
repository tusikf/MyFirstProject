package com.mygdx.game.units;
import java.util.ArrayList;
import java.util.List;

public abstract class Unit implements MyInterface{
    String name;
    int health;
    String weapon;
    int powerHit;
    int speed;
    int bronya;
    int atackRange;
    int hidding;
    int maxHealth;
    int x;
    int y;

    String className;
    public Position position;
    ArrayList deadList;

    public Unit(String name, int health, String weapon, int powerHit, int speed, int bronya, int atackRange, int hidding, Position position){
        this.className = this.getClass().getSimpleName();
        this.name = name;
        this.maxHealth = this.health = health;
        this.weapon = weapon;
        this.powerHit = powerHit;
        this.speed = speed;
        this.bronya = bronya;
        this.atackRange = atackRange;
        this.hidding = hidding;
        this.position = position;
    }

    // Метод вывода информации о персонаже
    @Override
    public String toString() {
        return  name + ", \u2665: " + health + ",  ⚔ : " + powerHit + ", \uD83D\uDEE1\uFE0F :" + bronya;
    }

    // Метод нанесения урона
    public void getHit(float damage) {
        health -= damage;
        if (health < 0) health = 0;
        if (health > maxHealth) health = maxHealth;
    }

    // Метод нахождения ближайшего врага
    public Unit nearestEnemy(List<Unit> targets) {
        if (targets.isEmpty()) {
            return null; // Handle empty list case
        }
        Unit nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Unit hero : targets) {
            double distance = position.distance(hero.position);
            if (distance < minDistance && hero.health > 0) {
                minDistance = distance;
                nearest = hero;
            }
        }

        return nearest;
    }


    public int getSpeed() {
        return speed;
    }

    public int getHp(){
        return health;
    };

    public String getInfo(){
        return " ";
    };
}


