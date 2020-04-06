package com.mygdx.game.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ProtectTheKingdom;
import com.mygdx.game.model.entity.FreezeTower;
import com.mygdx.game.model.entity.Player;
import com.mygdx.game.model.entity.ShopCell;
import com.mygdx.game.model.entity.Tower;
import com.mygdx.game.model.utils.BaseActor;
import com.mygdx.game.model.utils.Direction;
import com.mygdx.game.model.utils.Point;
import com.mygdx.game.model.utils.Round;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayScreen implements Screen{

    private ProtectTheKingdom game;
    private Stage mainStage;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera gameAreaCamera;
    private OrthographicCamera menuAreaCamera;
    private BitmapFont font;
    private SpriteBatch batch;
    private Texture Tank;
    private int numTilesHorizontal;
    private int numTilesVertical;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Texture Pistol1;
    private Texture Pistol2;
    private Texture Pistol3;
    private Texture Pistol4;
    private Texture Pistol5;
    private Texture Pistol6;
    private Texture Exit;
    private float GAME_WIDTH = Gdx.graphics.getWidth()*4.0f/5.0f;
    private float MENU_WIDTH = Gdx.graphics.getWidth()/5.0f;
    private float HEIGHT = Gdx.graphics.getHeight();
    private final float offsetPixel;
    private final float screenToMapOffsetWidth;
    private final float screenToMapOffsetHeight;
    private final float tileSize;
    private Texture laser;
    private boolean gameOver; // Verifie si la partie est terminee
    protected Stage uiStage;
    private int temps; // aide a faire tirer avec une certaine cadence
    private Round round; // l objet round qui permet de gerer les niveaux
    private int ennemycount; // Permet la gestion des ennemis
    private int temps1; //Permet la gestion d apparition des ennemis
    private Viewport gameAreaViewport;
    private final FitViewport menuAreaViewport;
    private Stage menuStage;
    private Texture Menu;
    private Label moneyLabel;
    private Label lifeLabel;
    private Player player;
    private Image Coin;
    private Image Heart;
    private boolean [][] mapCollision ;
    private ArrayList<Direction> directionList = new ArrayList<>();



    public PlayScreen(final ProtectTheKingdom game){

        System.out.println(Gdx.graphics.getWidth());
        mainStage = new Stage(new FitViewport(GAME_WIDTH, HEIGHT));
        menuStage = new Stage(new FitViewport(MENU_WIDTH, HEIGHT));
        map = new TmxMapLoader().load("Map 2.tmx");
        player = new Player();
        numTilesHorizontal = (int)map.getProperties().get("width");
        numTilesVertical = (int)map.getProperties().get("height");
        tileSize = (int)map.getProperties().get("tileheight");
        offsetPixel = tileSize/2.0f;
        this.screenToMapOffsetWidth = (float)  Gdx.graphics.getWidth()/(tileSize*numTilesHorizontal);
        this.screenToMapOffsetHeight = (float) Gdx.graphics.getHeight()/(tileSize*numTilesVertical);
        gameOver = false;
        mapCollision = new boolean[numTilesVertical][numTilesHorizontal];
        initMapCollision();
        world = new World(new Vector2(0, 0), true);
        Tank = new Texture("Tank.png");
        Pistol1 = new Texture("Pistol.png");
        Pistol2 = new Texture("tiny_gun_icons/md5.png");
        Pistol3 = new Texture("tiny_gun_icons/mg6000.png");
        Pistol4 = new Texture("tiny_gun_icons/pow9.png");
        Pistol5 = new Texture("tiny_gun_icons/desert_hawk.png");
        Pistol6 = new Texture("tiny_gun_icons/snp6plus.png");
        Exit = new Texture("x.png");
        Menu = new Texture("levelsel.png");
        Heart = new Image(new Texture("heart.png"));
        Coin = new Image(new Texture("coin.png"));
        laser = new Texture("Bullet.png");
        temps = 61;
        ArrayList<MapObject> directions = getRectangleList("direction");
        MapObject start = getRectangleList("start").get(0);
        MapObject end = getRectangleList("end").get(0);
        float startX = (float) start.getProperties().get("x");
        float startY = translateOffsetPointY((float) start.getProperties().get("y"));
        float endX = translateOffsetPointX((float) end.getProperties().get("x"));
        float endY = translateOffsetPointX((float) end.getProperties().get("y"));
        Rectangle endRect = new Rectangle(endX,endY, 10, 10);
        setDirectionList(directions);
        round = new Round(directionList,startX,startY,endRect);
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
        font.getData().setScale(5.0f);
        batch = new SpriteBatch();
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 32f);
        gameAreaCamera.setToOrtho(false, numTilesHorizontal, numTilesVertical);
        gameAreaCamera.update();
        b2dr = new Box2DDebugRenderer();
        uiStage = new Stage();
        temps1 = 0;

        Drawable exitImage = new TextureRegionDrawable(Exit);
        ImageButton exitButton = new ImageButton(exitImage);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        Drawable menuImage = new TextureRegionDrawable(Menu);
        ImageButton menuButton = new ImageButton(menuImage);
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen((ProtectTheKingdom) game));
            }
        });

        moneyLabel = new Label("MONEY", new Label.LabelStyle(font, Color.WHITE));
        lifeLabel = new Label("LIFE", new Label.LabelStyle(font, Color.WHITE));

        Table menu = new Table();
        menu.setFillParent(true);
        menuStage.addActor(menu);

        menu.add(menuButton).right().colspan(2);
        menu.add(exitButton).right().colspan(2);
        menu.row();
        menu.add(new ShopCell(new Tower("Gun",10, 400, 60, 50, 24, HEIGHT - Gdx.input.getY(), Pistol1, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.add(new ShopCell(new Tower("Item",0, 0, 0, 200, 0, 0, Pistol2, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.row();
        menu.add(new ShopCell(new Tower("Item",0, 0, 0, 150, 0, 0, Pistol3, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.add(new ShopCell(new Tower("Item",0, 0, 0, 75, 0, 0, Pistol4, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.row();
        menu.add(new ShopCell(new Tower("Item",0, 0, 0, 40, 0, 0, Pistol5, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.add(new ShopCell(new Tower("Item",0, 0, 0, 200, 0, 0, Pistol6, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.row();
        menu.add(Coin).expandX().right().size(60, 60).colspan(1);
        menu.add(moneyLabel).expandX().left().expandX().colspan(1);
        menu.add(Heart).expandX().right().size(60,60).colspan(1);
        menu.add(lifeLabel).expandX().left().expandX().colspan(1);


    }

    private void initMapCollision(){
        for (int i = 0 ; i<mapCollision.length;i++){
            for (int j = 0 ; j<mapCollision[i].length;j++){
                mapCollision[i][j] = false;
            }
        }
    }

    private float translateOffsetPointX(float x){
        return (x-(this.offsetPixel/2))*this.screenToMapOffsetWidth;
    }

    private float translateOffsetPointY(float y){
        return (y-this.offsetPixel)*this.screenToMapOffsetHeight;
    }

    private void setDirectionList(ArrayList<MapObject> directions){
        for (MapObject obj:directions) {
            float x = translateOffsetPointX((float) obj.getProperties().get("x"));
            float y = translateOffsetPointY((float) obj.getProperties().get("y"));
            int rotation = (int) obj.getProperties().get("rotation");
            Point point = new Point(x,y);
            directionList.add(new Direction(point,rotation));
        }

    }

    public ArrayList<MapObject> getRectangleList(String propertyName)
    {
        ArrayList<MapObject> list = new ArrayList<>();

        for ( MapLayer layer : map.getLayers() )
        {
            for ( MapObject obj : layer.getObjects() )
            {

                MapProperties props = obj.getProperties();

                if ( props.containsKey("type") && props.get("type").equals(propertyName) ) {
                    list.add(obj);
                }
            }
        }
        return list;
    }

    private void putTowerMapCol(Vector3 position){
        int col = (int) position.x;
        int row = (numTilesVertical-1) - (int) position.y;
        if (col == numTilesHorizontal) {
            col = numTilesHorizontal - 1;
        }

        /*if(y == numTilesVertical){
            y = numTilesVertical - 1;
        }*/

        System.out.println("tower in map: "+ col + " "+ row);
        System.out.println("tower in tab: "+ row + " "+ col);
        trueTowerPosition(col, row);
        /*for (int i = 0 ; i<mapCollision.length;i++){
            for (int j = 0 ; j<mapCollision[i].length;j++){
                System.out.print(i + " ; "+ j + " | ");
            }
            System.out.println();
        }*/
       /* for (int i = 0 ; i<mapCollision.length;i++){
            for (int j = 0 ; j<mapCollision[i].length;j++){
                if(mapCollision[i][j]){
                    System.out.print("T" + " | ");
                }else{
                    System.out.print("F" + " | ");
                }
            }
            System.out.println();
        }*/
        //print2D(mapCollision);
    }

    private void trueTowerPosition(int x, int y){
        mapCollision[y][x] = true;
        if(x != mapCollision[0].length-1){
            mapCollision[y][x+1] = true;
        }
        if(x != 0) {
            mapCollision[y][x-1] = true;
        }
        if(y != mapCollision.length-1){
            mapCollision[y+1][x] = true;
        }
        if(y != 0) {
            mapCollision[y-1][x] = true;
        }
       /* mapCollision[x+1][y+1] = true;
        mapCollision[x-1][y-1] = true;
        mapCollision[x+1][y-1] = true;
        mapCollision[x-1][y+1] = true;*/
    }

    private boolean checkPosTower(Vector3 position){
        int x = (int) position.x;
        int y = (numTilesVertical-1) - (int) position.y;

        if (x == numTilesHorizontal) {
            x = numTilesHorizontal - 1;
        }

        /*if(y == numTilesVertical){
            y = numTilesVertical - 1;
        }*/

        return !mapCollision[y][x] && checkPosTowerX(x, y) && checkPosTowerY(x, y);
                //&& checkPosTowerDiagonal(x, y);
    }

    private boolean checkPosTowerX(int x, int y){
        boolean ok;
        if(x == mapCollision[0].length-1){
            ok = !mapCollision[y][x-1];
        }else if (x== 0){
            ok = !mapCollision[y][x+1];
        }else{
            ok = !mapCollision[y][x+1] && !mapCollision[y][x-1];
        }
        return ok;
    }

    private boolean checkPosTowerY(int x, int y){
        boolean ok;
        if(y == mapCollision.length-1){
            ok =!mapCollision[y-1][x];
        }else if(y==0){
            ok =!mapCollision[y+1][x];
        }else{
            ok = !mapCollision[y+1][x] && !mapCollision[y-1][x];
        }
        return ok;
    }

    private boolean checkPosTowerDiagonal(int x, int y){
        return !mapCollision[x + 1][y + 1] && !mapCollision[x - 1][y - 1]
                && !mapCollision[x + 1][y - 1] && !mapCollision[x - 1][y + 1];
    }

    public void handleInput(float dt) {
        if (Gdx.input.justTouched()) {
            Vector3 pos3 = gameAreaCamera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0), 0, 0, (int) GAME_WIDTH, (int) HEIGHT);
            //System.out.println("click "+(int)pos3.x +" "+ (int)pos3.y);
            System.out.println("click "+(int)pos3.x +" "+ (29 - (int)pos3.y));
            TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) map.getLayers().get(0);
            TiledMapTileLayer.Cell cell = tiledMapTileLayer.getCell((int) pos3.x, (int) pos3.y);
            if (cell != null) {
                System.out.println("Cell id: " + cell.getTile().getId());
                System.out.println("Pas placer sur le chemin");
            } else {
                    float offset = Gdx.graphics.getWidth() / 48;
                    float x = (pos3.x * offset);
                    if (pos3.x < numTilesHorizontal && pos3.y < numTilesVertical) {
                        if(checkPosTower(pos3)) {
                            putTowerMapCol(pos3);
                            Tower tower = new FreezeTower("Gun",0, 500, 80, 75, x, HEIGHT - Gdx.input.getY(), Pistol1, mainStage, world, 2);
                            player.buyWeapons(tower);
                        }else {
                            System.out.println("trop proche !!");
                        }
                    }
            }
        }
    }

    public void update(float dt){
        if(player.getLife() <= 0 ){
            this.gameOver = true;
            game.setScreen(new LoseScreen(game, player));
        }
        else if(round.getRoundnbr() == 8){
            this.gameOver = true;
            game.setScreen(new WinScreen(game, player));
        }
        else {
            handleInput(dt);
            if (round.getRoundnbr() != 0) { // met le round a jour
                round.update(dt, game, player, mainStage);
            }
            if (ennemycount < 3 && temps1 > 125) { //demarre le premier round
                round.setRoundnbr(1);
                round.round1(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 2 && ennemycount < 8 && temps1 > 125) {
                round.round2(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 3 && ennemycount < 10 && temps1 > 125) {
                round.round3(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 4 && ennemycount < 14 && temps1 > 125) {
                round.round4(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 5 && ennemycount < 21 && temps1 > 125) {
                round.round5(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 6 && ennemycount < 22 && temps1 > 125) {
                round.round6(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 7 && ennemycount < 27 && temps1 > 125) {
                round.round7(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            temps1++;
            world.step(1 / 60f, 6, 2);
            moneyLabel.setText(String.format("%04d", player.getMoney()));
            lifeLabel.setText(String.format("%01d", player.getLife()));
        }
    }

    @Override
    public void show() {
        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(uiStage);
        im.addProcessor(mainStage);
        Gdx.input.setInputProcessor(im);
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

    }

    @Override
    public void dispose() {
        map.dispose();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public boolean gameOver(){
        return gameOver;
    }

    /*public void Wave1(float dt){
        if(temps == 59){
            Ennemy ennemy = new Ennemy(200, 125, 20, Tank, mainStage, world);
            ennemy.defineEnnemy();
        }
    }*/

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
        if(round.getRoundnbr() != 0) {
            round.draw(batch);
        }
        if(!player.isEmptyWeapons()) {
            for (Tower tower : player.getWeapons()) {
                batch.draw(tower.getSprite(), tower.getX(), tower.getY());
            }
        }
        if (round.getRoundnbr() != 0) {
            for(Tower tower : player.getWeapons()) {
                round.shoot(tower, batch, delta, world, game, uiStage);
            }
            temps = 0;
        }
        if(round.getRoundnbr() != 0) {
            for(Tower tower : player.getWeapons()) {
                round.updateLaser(delta, batch, game, uiStage, tower);
            }
        }
        temps++;
        batch.end();
    }

    private void drawMenuArea(float delta) {
        //update(delta);
        menuAreaCamera.update();

        batch.begin();
        menuStage.act(Math.min(Gdx.graphics.getDeltaTime(),1/30f));
        menuStage.draw();
        batch.end();
    }
}

