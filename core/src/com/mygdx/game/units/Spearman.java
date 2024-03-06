package com.mygdx.game.units;

import java.util.ArrayList;

// Копейщик. Своё свойство - количество копей
public class Spearman extends Unit {
    int countSpear;
    public Spearman(String name, int x, int y) {
        super(name, 150, "spear", 30, 6, 15, 50, 40, new Position(x,y));
        countSpear = 10;
    }

    @Override
    public void step(ArrayList<Unit> enemy, ArrayList<Unit> friend) {
        if (health<=0) return;
        Unit target = super.nearestEnemy(enemy);
        if (target == null) return;
        if (position.distance(target.position) < 2){
            target.getHit(this.powerHit);
            return;
        }
        Position diff = position.getDifference(target.position);
        if (Math.abs(diff.x) > Math.abs(diff.y)) {
            this.position.x += diff.x < 0 ? 1 : -1;
        } else {
            this.position.y += diff.y < 0 ? 1 : -1;
        }
        countSpear--;

    }
    @Override
    public String toString() {
        return super.toString() + ", \u2197 : " + countSpear;
    }
    public String getInfo(){
        return "К";
    };
}
