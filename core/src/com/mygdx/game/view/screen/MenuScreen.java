package com.mygdx.game.view.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ProtectTheKingdom;

public class MenuScreen implements Screen {

    private final Stage stage;
    private ProtectTheKingdom game;

    public void initialize(){

    }

    public void update(float dt){

    }

    public MenuScreen(ProtectTheKingdom game){
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin2/glassy-ui.json"));

        final TextButton newGame = new TextButton("New Game", skin);
        TextButton resume = new TextButton("Resume", skin);
        TextButton exit = new TextButton("Exit", skin);

        if (game.playScreen != null){
            table.add(resume).fillX().uniformX();
            table.row().pad(10, 0, 10, 0);
        }
        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(exit).fillX().uniformX();

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (game.playScreen != null) {
                    game.playScreen.dispose();
                }
                PlayScreen playScreen = new PlayScreen(game);
                game.playScreen = playScreen;
                game.setScreen(playScreen);
            }
        });

        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.playScreen);
            }
        });

        //Test dialog box dans le menu screen
/*
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
        Label label = new Label("Sell or not?",font);
        Dialog dialogSell = new Dialog("Sell",new Skin(Gdx.files.internal("skin2/default/skin/uiskin.json")));
        dialogSell.setMovable(true);
        dialogSell.padTop(50).padBottom(50);
        //dialogSell.getContentTable().add(label).row();
        dialogSell.text("Sell or not?");
        dialogSell.text("????");
        dialogSell.button("Yes", true).button("No", false);
        //stage.addActor(dialogSell);
        dialogSell.show(stage);

 */
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(),1/30f));
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
