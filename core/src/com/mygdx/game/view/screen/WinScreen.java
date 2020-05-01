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
import com.mygdx.game.model.entity.Player;

public class WinScreen implements Screen{

    private Viewport viewport;
    private Stage stage;
    private Game game;

        public WinScreen(Game game, Player player){
            this.game = game;
            stage = new Stage();
            //Gdx.input.setInputProcessor(stage);

            Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

            Table table = new Table();
            table.center();
            table.setFillParent(true);

            Label gameWinLabel = new Label("YOU WIN", font);
            Label playAgainLabel = new Label("Click to Play Again", font);
            Label ennemieskilled = new Label("Game score : ", font);
            String gamescore = (String) Integer.toString(player.getScore());
            Label gamescoreLabel = new Label(gamescore, font);
            Label lifebonus = new Label("Bonus life left : ", font);
            String lifescore = (String) Integer.toString(100*player.getEndlife());
            Label lifescoreLabel = new Label(lifescore, font);
            Label roundbonus = new Label("Bonus round :", font);
            String bonusscore = (String) Integer.toString(100*player.getBonusennemies());
            Label bonusscoreLabel = new Label(bonusscore, font);
            Label score_Label = new Label("Total score : ", font);
            player.setScore(player.getScore() + 100*player.getEndlife() + 100*player.getBonusennemies());
            String score = (String) Integer.toString(player.getScore());
            Label scoreLabel = new Label(score, font);

            gameWinLabel.setFontScale(5);
            playAgainLabel.setFontScale(5);
            score_Label.setFontScale(5);
            scoreLabel.setFontScale(5);
            ennemieskilled.setFontScale(5);
            lifebonus.setFontScale(5);
            roundbonus.setFontScale(5);
            gamescoreLabel.setFontScale(5);
            lifescoreLabel.setFontScale(5);
            bonusscoreLabel.setFontScale(5);

            table.add(gameWinLabel).fillX().uniformX();
            table.row().pad(10, 0, 10, 0);
            table.add(ennemieskilled).fillX().uniformX();
            table.add(gamescoreLabel).fillX().uniformX();
            table.row().pad(10, 0, 10, 0);
            table.add(lifebonus).fillX().uniformX();
            table.add(lifescoreLabel).fillX().uniformX();
            table.row().pad(10, 0, 10, 0);
            table.add(roundbonus).fillX().uniformX();
            table.add(bonusscoreLabel).fillX().uniformX();
            table.row().pad(10, 0, 10, 0);
            table.add(score_Label).fillX().uniformX();
            table.add(scoreLabel).fillX().uniformX();
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
                game.setScreen(new MenuScreen((ProtectTheKingdom) game));
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
