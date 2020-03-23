package com.mygdx.game.view.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ProtectTheKingdom;

public class WinScreen implements Screen{

    private Viewport viewport;
    private Stage stage;
    private Game game;

        public WinScreen(Game game){
            this.game = game;
            stage = new Stage();
            //Gdx.input.setInputProcessor(stage);

            Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

            Table table = new Table();
            table.center();
            table.setFillParent(true);

            Label gameWinLabel = new Label("YOU WIN", font);
            Label playAgainLabel = new Label("Click to Play Again", font);
            gameWinLabel.setFontScale(5);
            playAgainLabel.setFontScale(5);

            table.add(gameWinLabel).fillX().uniformX();
            table.row().pad(10, 0, 10, 0);
            table.add(playAgainLabel).fillX().uniformX();

            stage.addActor(table);
        }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta) {
            if(Gdx.input.justTouched()) {
                game.setScreen(new PlayScreen((ProtectTheKingdom) game));
                dispose();
            }
            Gdx.gl.glClearColor(0, 0, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            stage.draw();
        }

        @Override
        public void resize(int width, int height) {
            stage.getViewport().update(width, height, true);
        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void dispose() {
            stage.dispose();
        }
    }
