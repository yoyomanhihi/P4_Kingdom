	package com.mygdx.game;

	import com.badlogic.gdx.Game;
	import com.badlogic.gdx.Gdx;
	import com.badlogic.gdx.audio.Music;
	import com.badlogic.gdx.graphics.g2d.SpriteBatch;
	import com.badlogic.gdx.scenes.scene2d.Stage;
	import com.mygdx.game.model.entity.Player;
	import com.mygdx.game.view.screen.MenuScreen;
	import com.mygdx.game.view.screen.PlayScreen;
	import com.mygdx.game.view.screen.WinScreen;

	public class ProtectTheKingdom extends Game {
		public Stage mainStage;
		public SpriteBatch batch;
		public Player player;
		private MenuScreen menuScreen;
		private PlayScreen playScreen;
		public static final int V_WIDTH = 400;
		public static final int V_HEIGHT = 208;
		private Music music;

		public final static int MENU = 0;
		public final static int PLAY = 1;


		@Override
		public void create () {
			mainStage = new Stage();
			batch = new SpriteBatch();
			player = new Player();
			music = Gdx.audio.newMusic(Gdx.files.internal("Actofwar.mp3"));
			music.setLooping(true);
			music.setVolume(0.5f);
			music.play();
			menuScreen = new MenuScreen(this);
			setScreen(menuScreen);
		}

		@Override
		public void render () {
			super.render();
		}

		@Override
		public void dispose () {
			batch.dispose();
			music.dispose();
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