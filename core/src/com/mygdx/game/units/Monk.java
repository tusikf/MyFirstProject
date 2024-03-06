package com.mygdx.game.units;

import java.util.ArrayList;
import java.util.Iterator;

// Монах. Своё свойство - лечение
public class Monk extends Unit {
    int healing;
    int mana;
    boolean flag;
    public Monk(String name, int x, int y) {
        super(name, 150, "null", 0, 4, 10, 50, 40, new Position(x,y));
        healing = 0;
        mana = 10;
        flag = false;
    }

    @Override
    public void step(ArrayList<Unit> enemy, ArrayList<Unit> friend) {
        if (getHp() <= 0) return;
        ArrayList<Unit> sortlist = new ArrayList<>(friend);
        ArrayList<Unit> deadlist = new ArrayList<>();
        sortlist.sort((o1, o2) -> o1.getHp() - o2.getHp());

        Iterator<Unit> iterator = sortlist.iterator();
        while (iterator.hasNext()){
            Unit unit = iterator.next();
            if (unit.getHp() == 0) {
                deadlist.add(unit);
                iterator.remove();
            }
        }
        if (deadlist.size() > 3 ) {
            flag = true;
            System.out.println("Флаг установлен");
        }

        if (flag && mana == 10) {
                deadlist.sort((o1, o2) -> o2.speed - o1.speed);
                deadlist.get(0).health = maxHealth;
                mana = 0;
                System.out.println("Воскресил: " + deadlist.get(0).name);
                flag = false;
                return;
        }
        if (flag) {
            mana++;
            return;
        }
        if (mana < 2) {
                mana++;
                return;
            }
            sortlist.get(0).health += 10;
            mana -= 2;
        }
    public String getInfo(){
        return "М";
    }
    @Override
    public String toString() {
        return super.toString() + ", \u0271 : " + mana;
    }
    }




