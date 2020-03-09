package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.BaseActor;
import com.mygdx.game.TexturedActor;

public class ProtectTheKingdom extends Game {
	public Stage mainStage;
	//public Ennemy fast_walker = new Ennemy(80,10,80,25, mainStage);
	//public Ennemy boss = new Ennemy(500,3,500,100);
	//public Tower basic_gun = new Tower(15, 10, 1000, 100, );
	//public Tower fast_gun = new Tower(12, 5, 300, 250);
	public SpriteBatch batch;
	//Texture img;
	private MenuScreen menuScreen;
	private PlayScreen playScreen;
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;

	public final static int MENU = 0;
	public final static int PLAY = 1;

	
	@Override
	public void create () {
		mainStage = new Stage();
		batch = new SpriteBatch();
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
		//img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void changeScreen(int screen){
		switch (screen){
			case MENU:
				if (menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case PLAY:
				if(playScreen == null) playScreen = new PlayScreen(this);
				this.setScreen(playScreen);
				break;
		}
	}
}