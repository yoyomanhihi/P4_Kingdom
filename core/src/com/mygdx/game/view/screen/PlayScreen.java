package com.mygdx.game.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.utils.BaseActor;
import com.mygdx.game.model.entity.Ennemy;
import com.mygdx.game.model.entity.Laser;
import com.mygdx.game.ProtectTheKingdom;
import com.mygdx.game.model.entity.Tower;
import com.mygdx.game.model.utils.Round;

public class PlayScreen implements Screen{

    private ProtectTheKingdom game;
    Texture texture;
    private TmxMapLoader mapLoader;
    private Stage mainStage;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera gameAreaCamera;
    private OrthographicCamera menuAreaCamera;
    private BitmapFont font;
    private SpriteBatch batch;
    private Texture Tank;
    //private Ennemy ennemylol;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Tower pistol; //weapon test
    private Texture Pistol;
    private float GAME_WIDTH = Gdx.graphics.getWidth()*4.0f/5.0f;
    private float MENU_WIDTH = Gdx.graphics.getWidth()/5.0f;
    private float HEIGHT = Gdx.graphics.getHeight();
    private Texture laser;
    private boolean gameOver; // Verifie si la partie est terminee
    protected Stage uiStage;
    private int temps; // aide a faire tirer avec une certaine cadence
    private Round round; // l objet round qui permet de gerer les niveaux
    private boolean round1; //verifie si c est le premier round
    private int ennemycount; // Permet la gestion des ennemis
    private int temps1; //Permet la gestion d apparition des ennemis
    private Viewport gameAreaViewport;
    private final FitViewport menuAreaViewport;
    private Stage menuStage;


    public PlayScreen(ProtectTheKingdom game){

        mainStage = new Stage(new FitViewport(GAME_WIDTH, HEIGHT));
        menuStage = new Stage(new FitViewport(MENU_WIDTH, HEIGHT));
        gameOver = false;
        world = new World(new Vector2(0, 0), true);
        Tank = new Texture("Tank.png");
        Pistol = new Texture("Pistol.png");
        laser = new Texture("Bullet.png");
        //ennemylol = new Ennemy(200, 125, 20, Tank, 0, mainStage, world);
        //ennemylol.defineEnnemy();
        pistol = new Tower(40, 500, 40, 40, 850, 240, Pistol, mainStage, world);
        temps = 61;
        round = new Round();
        ennemycount = 0;


        this.game = game;

        gameAreaCamera = new OrthographicCamera();
        gameAreaCamera.setToOrtho(false, GAME_WIDTH, HEIGHT);
        gameAreaCamera.update();
        gameAreaViewport = new FitViewport(GAME_WIDTH, HEIGHT, gameAreaCamera);

        menuAreaCamera = new OrthographicCamera();
        menuAreaCamera.setToOrtho(false, MENU_WIDTH, HEIGHT);
        menuAreaCamera.update();
        menuAreaViewport = new FitViewport(MENU_WIDTH, HEIGHT, menuAreaCamera);


        font = new BitmapFont();
        batch = new SpriteBatch();
        map = new TmxMapLoader().load("Map 1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 32f);
        gameAreaCamera.setToOrtho(false, 48, 30);
        gameAreaCamera.update();
        b2dr = new Box2DDebugRenderer();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        uiStage = new Stage();
        round1 = false;
        temps1 = 0;

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
            Vector3 pos = gameAreaCamera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
            System.out.println(pos.x+ "  "+pos.y);
        }
    }

    public void update(float dt){
        handleInput(dt);
        if(ennemycount < 1 && temps1 > 100) { //demarre le premier round
            round.round1(temps, uiStage, world, ennemycount);
            round1 = true;
            ennemycount++;
            temps1 = 0;
        }
        if(round1){ // met le round a jour
            round.update(dt, game);
        }
        temps1++;
        world.step(1/60f, 6, 2);
        //if(ennemylol.getY() < 1){
        //    game.setScreen(new LoseScreen(game));
        //}
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glViewport(0, 0, (int) GAME_WIDTH, (int) HEIGHT);
        drawGameArea(delta);
        Gdx.gl.glViewport((int) GAME_WIDTH, 0, (int) MENU_WIDTH, (int) HEIGHT);
        drawMenuArea(delta);

    }

    public void initialize()
    {
        BaseActor.setWorldBounds(GAME_WIDTH, HEIGHT);
    }


    @Override
    public void resize(int width, int height) {
        gameAreaCamera.update();
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

    public void Wave1(float dt){
        if(temps == 59){
            Ennemy ennemy = new Ennemy(200, 125, 20, Tank, mainStage, world);
            ennemy.defineEnnemy();
        }
    }

    private void drawGameArea(float delta) {
        update(delta);
        gameAreaCamera.update();
        renderer.setView(gameAreaCamera);
        renderer.render();

        b2dr.render(world, gameAreaCamera.combined);

        batch.begin();
        font.setColor(Color.BLACK);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 1680, 30);
        font.getData().setScale(1.8f);
        if(round1) {
            round.draw(batch);
        }
        batch.draw(pistol.getTexture(), pistol.getX(), pistol.getY());
        if (temps > 60 && round1) {
            round.shoot(pistol, batch, delta, world, game, uiStage);
            temps = 0;
        }
        if(round1) {
            round.updateLaser(delta, batch, game, uiStage, pistol);
        }
        temps++;
        batch.end();
    }

    private void drawMenuArea(float delta) {
        update(delta);
        menuAreaCamera.update();

        batch.begin();
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
        batch.end();
    }
}

