package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Screens.PlayScreen;

public class ProtectTheKingdom extends Game {
	public SpriteBatch batch;
	//Texture img;
	private MenuScreen menuScreen;
	private PlayScreen playScreen;

	public final static int MENU = 0;
	public final static int PLAY = 1;
	
	@Override
	public void create () {
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
