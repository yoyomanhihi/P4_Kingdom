package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private OrthographicCamera camera;
    private BitmapFont font;
    private SpriteBatch batch;

    public PlayScreen(ProtectTheKingdom game){

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();

        font = new BitmapFont();
        batch = new SpriteBatch();

        map = new TmxMapLoader().load("Map 1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 32f);
        camera.setToOrtho(false, 48, 30);
        camera.update();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);
        renderer.render();

        batch.begin();
        font.setColor(Color.BLACK);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 1680, 30);
        font.getData().setScale(1.8f);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

        camera.update();
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
        map.dispose();
    }
}

