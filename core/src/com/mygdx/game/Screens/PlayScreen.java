package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.ProtectTheKingdom;

public class PlayScreen implements Screen {

    private ProtectTheKingdom game;
    Texture texture;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(ProtectTheKingdom game){
        this.game = game;

        texture = new Texture("badlogic.jpg");

        mapLoader = new TmxMapLoader();

        map = mapLoader.load("Map 1.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);
    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched()){

        }
    }

    public void update(float dt){
        handleInput(dt);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(texture, 0, 0);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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

    }
}

