package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.BaseActor;
import com.mygdx.game.Ennemy;
import com.mygdx.game.ProtectTheKingdom;

public class PlayScreen implements Screen{

    private ProtectTheKingdom game;
    Texture texture;
    private TmxMapLoader mapLoader;
    private Stage mainStage;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private BitmapFont font;
    private SpriteBatch batch;
    private Texture Tank;
    private Ennemy ennemylol;


    public PlayScreen(ProtectTheKingdom game){

        mainStage = new Stage();
        ennemylol = new Ennemy(20, 20, 20, 20, mainStage);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        Tank = new Texture("Tank.png");


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

        for (MapObject mapObject : map.getLayers().get(1).getObjects() )
        {
            final RectangleMapObject collisionObject = (RectangleMapObject) mapObject;
            final Rectangle rectangle = collisionObject.getRectangle();
            System.out.println(rectangle.x + " "+ rectangle.y);
            MapProperties props = mapObject.getProperties();
            System.out.println(props.get("type", String.class) + " x: "
                    + props.get("x",Integer.class)+" y: "+ props.get("y",Integer.class));
        }

    }


    public void handleInput(float dt){
        if(Gdx.input.justTouched()){
            Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
            System.out.println(pos.x+ "  "+pos.y);
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

        update(delta);
        camera.update();
        renderer.setView(camera);
        renderer.render();

        batch.begin();
        font.setColor(Color.BLACK);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 1680, 30);
        font.getData().setScale(1.8f);
        batch.draw(Tank, ennemylol.getX(), ennemylol.getY());
        batch.end();
    }

    public void initialize()
    {
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

