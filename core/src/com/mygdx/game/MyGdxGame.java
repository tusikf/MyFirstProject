package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.units.Crossbowman;
import com.mygdx.game.units.Mag;
import com.mygdx.game.units.Monk;
import com.mygdx.game.units.Names;
import com.mygdx.game.units.Outlaw;
import com.mygdx.game.units.Peasant;
import com.mygdx.game.units.Sniper;
import com.mygdx.game.units.Spearman;
import com.mygdx.game.units.Unit;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon, crossBowMan, mage, monk, peasant, rouge, sniper, spearMan;

	public ArrayList<Unit> holyTeam;
	public ArrayList<Unit> darkTeam;
	public ArrayList<Unit> allTeam;
	
	@Override
	public void create () {
		holyTeam = new ArrayList<>();
		darkTeam = new ArrayList<>();
		allTeam = new ArrayList<>();

		init();

		allTeam.addAll(holyTeam);
		allTeam.addAll(darkTeam);

		allTeam.sort((o1, o2) -> o2.position.getX() - o1.position.getX());


		batch = new SpriteBatch();
		int rnd = MathUtils.random(0,4);
		fon = new Texture("fon/CmBk" + MathUtils.random(0,4) +".png");

		Music music = Gdx.audio.newMusic(Gdx.files.internal("music/paul-romero-rob-king-combat-theme-0" + MathUtils.random(1, 4) + ".mp3"));
		music.setVolume(.01f);
		music.play();

		this.crossBowMan = new Texture("units/CrossBowMan.png");
		this.mage = new Texture("units/Mage.png");
		this.monk = new Texture("units/Monk.png");
		this.peasant= new Texture("units/Peasant.png");
		this.rouge = new Texture("units/Rouge.png");
		this.sniper = new Texture("units/Sniper.png");
		this.spearMan = new Texture("units/SpearMan.png");




	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(fon, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		for (Unit unit : allTeam){
			int y = unit.position.getX() * Gdx.graphics.getWidth() / 20;
			int x = unit.position.getY() * Gdx.graphics.getHeight() / 10;
			int k = 1;
			if (darkTeam.contains(unit)) k = -1;
			if (unit.getHp() > 0){
				switch (unit.getInfo()){
					case "С":
						batch.draw(sniper, x, y, sniper.getWidth() * k, sniper.getHeight());
						break;
					case "Ф":
						batch.draw(peasant, x, y, peasant.getWidth() * k, peasant.getHeight());
						break;
					case "К":
						batch.draw(spearMan, x, y, spearMan.getWidth() * k, spearMan.getHeight());
						break;
					case "Р":
						batch.draw(rouge, x, y, rouge.getWidth() * k, rouge.getHeight());
						break;
					case "А":
						batch.draw(crossBowMan, x, y, crossBowMan.getWidth() * k, crossBowMan.getHeight());
						break;
					case "М":
						batch.draw(monk, x, y, monk.getWidth() * k, monk.getHeight());
						break;
					case "В":
						batch.draw(mage, x, y, mage.getWidth() * k, mage.getHeight());
						break;

				}
			}

		}
		batch.end();
		
		boolean flag = false;
		for (Unit unit : darkTeam) {
			if (unit.getHp() > 0) flag = false;
		}
		if (flag) {
			Gdx.graphics.setTitle("Команда тёмных победила");
			return;
		}

		flag = true;
		for (Unit unit : holyTeam) {
			if (unit.getHp() > 0) flag = false;
		}
		if (flag) {
			Gdx.graphics.setTitle("Команда светлых победила");
			return;
		}



		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.justTouched()){
			for (Unit unit : allTeam) {
				if (holyTeam.contains(unit)) unit.step(darkTeam, holyTeam);
				else unit.step(holyTeam, darkTeam);
			}
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fon.dispose();
		crossBowMan.dispose();
		mage.dispose();
		monk.dispose();
		peasant.dispose();
		rouge.dispose();
		sniper.dispose();
		spearMan.dispose();

	}

	private String getName(){
		return String.valueOf(Names.values()[new Random().nextInt(Names.values().length-1)]);
	}

	public void init(){
		Random random = new Random();
		int teamcount = 10;
		for (int i = 1; i < teamcount+1; i++) {
			int val = MathUtils.random(6);
			switch (val) {
				case 0:
					holyTeam.add(new Crossbowman(getName(), i,1));
					break;
				case 1:
					holyTeam.add(new Mag(getName(), i,1));
					break;
				case 2:
					holyTeam.add(new Monk(getName(),i,1));
					break;
				case 3:
					holyTeam.add(new Outlaw(getName(), i,1));
					break;
				case 4:
					holyTeam.add(new Peasant(getName(), i,1));
					break;
				case 5:
					holyTeam.add(new Sniper(getName(), i,1));
					break;
				case 6:
					holyTeam.add(new Spearman(getName(), i,1));
					break;
			}
		}
		for (int i = 1; i < teamcount+1; i++) {
			int val = (int) MathUtils.random(6);
			switch (val) {
				case 0:
					darkTeam.add(new Crossbowman(getName(), i, 10));
					break;
				case 1:
					darkTeam.add(new Mag(getName(), i,10));
					break;
				case 2:
					darkTeam.add(new Monk(getName(), i,10));
					break;
				case 3:
					darkTeam.add(new Outlaw(getName(), i,10));
					break;
				case 4:
					darkTeam.add(new Peasant(getName(), i,10));
					break;
				case 5:
					darkTeam.add(new Sniper(getName(), i,10));
					break;
				case 6:
					darkTeam.add(new Spearman(getName(), i,10));
					break;
			}
		}
	}
}
