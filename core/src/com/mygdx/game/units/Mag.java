package com.mygdx.game.units;

import java.util.ArrayList;

// Колдун - волшебник. Своё свойство - мана и магия. Мана при ходе уменьшается.
public class Mag extends Unit {
    int mana;
    String magic;
    public Mag(String name, int x, int y) {
        super(name, 150, "magicStick", 20, 2, 10, 50, 40, new Position(x,y));
        mana = 200;
        magic = "wizardDust";
    }

    @Override
    public void step(ArrayList<Unit> enemy, ArrayList<Unit> friend) {
        if (health<=0) return;

        Unit target = super.nearestEnemy(enemy);
        if (target == null || mana < 0) return;
        if (position.distance(target.position) < 2){
            target.getHit(this.powerHit);
            mana-=10;
            return;
        }
        Position diff = position.getDifference(target.position);

        Position newposition = new Position(position.x, position.y);

        if (Math.abs(diff.x) > Math.abs(diff.y))

            newposition.x += diff.x < 0 ? 1 : -1;
        else
            newposition.y += diff.y < 0 ? 1 : -1;

        for (Unit unit : friend) {
            if (unit.position.equals(newposition) && unit.health > 0) return;

        }
        this.position = newposition;
    }
    @Override
    public String toString() {
        return super.toString() + ", \u058D : " + mana;
    }
    public String getInfo(){
        return "В";
    };
}
