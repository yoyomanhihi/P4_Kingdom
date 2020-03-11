package com.mygdx.game.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.model.utils.BaseActor;
import com.mygdx.game.model.entity.Ennemy;
import com.mygdx.game.model.entity.Laser;
import com.mygdx.game.ProtectTheKingdom;
import com.mygdx.game.model.entity.Tower;

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
    private World world;
    private Box2DDebugRenderer b2dr;
    private Tower pistol;
    private Texture Pistol;
    private float w;
    private float h;
    private Texture laser;
    private boolean gameOver;
    protected Stage uiStage;
    private int temps;
    private Laser laser1;
    //private Stage menuStage;



    public PlayScreen(ProtectTheKingdom game){

        mainStage = new Stage();
        //menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth()*2/10, Gdx.graphics.getHeight()));
        gameOver = false;
        world = new World(new Vector2(0, 0), true);
        Tank = new Texture("Tank.png");
        Pistol = new Texture("Pistol.png");
        laser = new Texture("Bullet.png");
        ennemylol = new Ennemy(200, 125, 20, Tank, 0, mainStage, world);
        ennemylol.defineEnnemy();
        pistol = new Tower(40, 500, 40, 40, 850, 240, Pistol, mainStage);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        temps = 61;


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
        b2dr = new Box2DDebugRenderer();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        uiStage = new Stage();

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
        world.step(1/60f, 6, 2);
        ennemylol.update(dt, game);
        if(ennemylol.getY() < 1){
            game.setScreen(new LoseScreen(game));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(160/255f, 160/255f, 160/255f, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth()*8/10, Gdx.graphics.getHeight());
        update(delta);
        camera.update();
        renderer.setView(camera);
        renderer.render();

        b2dr.render(world, camera.combined);

        batch.begin();
        font.setColor(Color.BLACK);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 1680, 30);
        font.getData().setScale(1.8f);
        batch.draw(ennemylol.getTexture(), ennemylol.getX(), ennemylol.getY());
        batch.draw(pistol.getTexture(), pistol.getX(), pistol.getY());
        if (temps > 60 && ennemylol.isInRange(pistol)) {
            laser1 = new Laser(0, 0, mainStage, world);
            laser1.defineLaser();
            pistol.shoot(ennemylol, batch, delta, world, game, laser1);
            temps = 0;
        }
        if (ennemylol.isInRange(pistol)) {
            laser1.update(delta, batch, ennemylol, game);
        }
        temps++;
        batch.end();

        /*
        Gdx.gl.glViewport(Gdx.graphics.getWidth()*8/10, 0, Gdx.graphics.getWidth()*2/10, Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(menuStage);
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        menuStage.addActor(table);
        Drawable pistolImage = new TextureRegionDrawable(Pistol);
        pistolImage.setMinHeight(80);
        pistolImage.setMinWidth(80);
        ImageButton newGun = new ImageButton(pistolImage);
        //newGun.addListener(); FIXME
        table.add(newGun).size(80,80);
        menuStage.act(Math.min(Gdx.graphics.getDeltaTime(),1/30f));
        menuStage.draw();
    */
    }

    public void initialize()
    {
        BaseActor.setWorldBounds(w, h);
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
        //menuStage.dispose();
        //mainStage.dispose();
        //Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {
        map.dispose();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public boolean gameOver(){
        return gameOver;
    }
}

