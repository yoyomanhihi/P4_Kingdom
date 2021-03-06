package com.mygdx.game.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ProtectTheKingdom;
import com.mygdx.game.model.entity.Ennemy;
import com.mygdx.game.model.entity.FreezeTower;
import com.mygdx.game.model.entity.MoneyTower;
import com.mygdx.game.model.entity.Nuke;
import com.mygdx.game.model.entity.Player;
import com.mygdx.game.model.entity.ShopCell;
import com.mygdx.game.model.entity.Tower;
import com.mygdx.game.model.utils.BaseActor;
import com.mygdx.game.model.utils.Direction;
import com.mygdx.game.model.utils.Point;
import com.mygdx.game.model.utils.Round;
import com.mygdx.game.view.stage.MenuStage;

import java.awt.Button;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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
    private Texture snowlaser;
    private Texture Exit;
    private Texture Weapon1;
    private Texture Weapon2;
    private Texture Weapon3;
    private Texture Weapon4;
    private Texture Base1;
    private Texture Base2;
    private Texture Base3;
    private Texture Base4;
    private Texture Gun1;
    private Texture Gun2;
    private Texture Gun3;
    private Texture Gun4;
    private Texture MoneyCoin;
    private Texture FreezerBase;
    private Texture FreezerGun;
    private Texture FreezerWeapon;
    private Texture blank;
    private Sprite circle;
    public static final float GAME_WIDTH = Gdx.graphics.getWidth()*4.0f/5.0f;
    public static final float MENU_WIDTH = Gdx.graphics.getWidth()/5.0f;
    public static final float HEIGHT = Gdx.graphics.getHeight();
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
    private MenuStage menuStage;
    private Texture Menu;
    private Label moneyLabel;
    private Label lifeLabel;
    private Player player;
    private Image Coin;
    private Image Heart;
    private boolean [][] mapCollision ;
    private Tower [][] towersMap;
    private ArrayList<Direction> directionList = new ArrayList<>();
    private ArrayList<Point> towerPositionList = new ArrayList<>();
    private int IDTower;
    private Dialog dialogSell;
    private boolean dialogSellOrNot = false;
    private Tower tower1;
    private Tower tower2;
    private Tower tower3;
    private Tower tower4;
    private FreezeTower tower5;
    private MoneyTower tower6;
    private Tower selectedTower = null;
    private Tower towerToSell;
    private TextButton sellButton;
    private TextButton cancelButton;
    private TextButton skipButton;
    private TextButton upgradeButton1;
    private TextButton upgradeButton2;
    private TextButton upgradeButton3;
    private TextButton upgradeButton4;
    private TextButton upgradeButton5;
    TextButton test;
    private Vector3 posTowerSell;
    private long lastTouchTime;
    private int lastTouchCell;
    private boolean endcapture;
    private boolean islevelmax;
    private Sprite moab;
    private float transitionmoab;



    public PlayScreen(final ProtectTheKingdom game){

        System.out.println(Gdx.graphics.getWidth());
        mainStage = new Stage(new FitViewport(GAME_WIDTH, HEIGHT));
        Gdx.input.setInputProcessor(mainStage);

        map = new TmxMapLoader().load("Map 2.tmx");
        numTilesHorizontal = (int)map.getProperties().get("width");
        numTilesVertical = (int)map.getProperties().get("height");
        tileSize = (int)map.getProperties().get("tileheight");
        offsetPixel = tileSize/2.0f;
        this.screenToMapOffsetWidth = (float)  Gdx.graphics.getWidth()/(tileSize*numTilesHorizontal);
        this.screenToMapOffsetHeight = (float) Gdx.graphics.getHeight()/(tileSize*numTilesVertical);
        gameOver = false;
        mapCollision = new boolean[numTilesVertical][numTilesHorizontal];
        towersMap = new Tower[numTilesVertical][numTilesHorizontal];
        initMapCollision();
        initMapTower();
        islevelmax = false;
        world = new World(new Vector2(0, 0), true);
        Tank = new Texture("ennemies_sprite/red1.png");
        Pistol1 = new Texture("Pistol.png");
        moab = new Sprite(new Texture("blanc.jpg"));
        transitionmoab = 0;

        Weapon1 = new Texture("weapon_sprites/Weapon1.png");
        Weapon2 = new Texture("weapon_sprites/Weapon2.png");
        Weapon3 = new Texture("weapon_sprites/Weapon3.png");
        Weapon4 = new Texture("weapon_sprites/Weapon4.png");
        Base1 = new Texture("weapon_sprites/Base1.png");
        Base2 = new Texture("weapon_sprites/Base2.png");
        Base3 = new Texture("weapon_sprites/Base3.png");
        Base4 = new Texture("weapon_sprites/Base4.png");
        Gun1 = new Texture("weapon_sprites/Gun1.png");
        Gun2 = new Texture("weapon_sprites/Gun2.png");
        Gun3 = new Texture("weapon_sprites/Gun3.png");
        Gun4 = new Texture("weapon_sprites/Gun4.png");
        FreezerBase = new Texture("weapon_sprites/FreezerBase.png");
        FreezerWeapon = new Texture("weapon_sprites/FreezerWeapon.png");
        FreezerGun = new Texture("weapon_sprites/FreezerGun.png");

        circle = new Sprite(new Texture("circle.png"));

        MoneyCoin = new Texture("Gold.png");
        blank = new Texture("Healthbar.png");
        snowlaser = new Texture("snowflake.png");
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
        Skin skin = new Skin(Gdx.files.internal("skin2/skin/star-soldier-ui.json"));
        sellButton = new TextButton("Sell\nTower", skin);
        cancelButton = new TextButton("No", skin);
        skipButton = new TextButton("Next\nRound", skin);
        upgradeButton1 = new TextButton("Upgrade\nTower: 40", skin);
        upgradeButton2 = new TextButton("Upgrade\nTower: 120", skin);
        upgradeButton3 = new TextButton("Upgrade\nTower: 300", skin);
        upgradeButton4 = new TextButton("Upgrade\nTower: 640", skin);
        upgradeButton5 = new TextButton("Upgrade\nTower: 200", skin);

        int row_height = (int) (HEIGHT/3)/3;
        //propertiesButton(sellButton,-10,180,2.3f);
        propertiesButton(sellButton,-10,row_height*2-30,2.1f);
        //propertiesButton(upgradeButton1, -35, 80, 2.0f);
        propertiesButton(upgradeButton1, -35, row_height*1-25, 2.0f);
        propertiesButton(upgradeButton2, -35, row_height*1-25, 2.0f);
        propertiesButton(upgradeButton3, -35, row_height*1-25, 2.0f);
        propertiesButton(upgradeButton4, -35, row_height*1-25, 2.0f);
        propertiesButton(upgradeButton5, -35, row_height*1-25, 2.0f);
        propertiesButton(cancelButton,25,row_height*0-25, 2.8f);
        //propertiesButton(cancelButton,25,-25, 2.8f);
        //propertiesButton(skipButton, -40, 70, 2.5f);
        propertiesButton(skipButton, -40, row_height*1-25, 2.5f);

        this.game = game;
        this.player = this.game.player;
        endcapture = false;

        gameAreaCamera = new OrthographicCamera();
        gameAreaCamera.setToOrtho(false, GAME_WIDTH, HEIGHT);
        gameAreaCamera.update();
        gameAreaViewport = new FitViewport(GAME_WIDTH, HEIGHT, gameAreaCamera);

        menuAreaCamera = new OrthographicCamera();
        menuAreaCamera.setToOrtho(false, MENU_WIDTH, HEIGHT);
        menuAreaCamera.update();
        menuAreaViewport = new FitViewport(MENU_WIDTH, HEIGHT, menuAreaCamera);

        tower1 = new Tower(1,"Classic",6, 350, 60, 50, 0, 0, Weapon1, Base1, Gun1, laser, .5f, mainStage, world);
        tower2 = new Tower(2,"Cadence",8, 275, 20, 150, 0, 0, Weapon2, Base2, Gun2, laser, .4f, mainStage, world);
        tower3 = new Tower(3,"Portée",100, 800, 400, 375, 0, 0, Weapon3, Base3, Gun3, laser, .8f, mainStage, world);
        tower4 = new Tower(4,"Dégats",120, 400, 100, 800, 0, 0, Weapon4, Base4, Gun4, laser, .6f, mainStage, world);
        tower5 = new FreezeTower(5, "Freeze",0, 250, 120, 250, 0, 0, FreezerBase, FreezerGun, snowlaser, .3f, mainStage, world, 2);
        tower6 = new MoneyTower(6,"Money",0, 0, Integer.MAX_VALUE, 250, 0, 0, MoneyCoin, blank, laser, 2, mainStage, world, 1.15f, player);
        player.setMoneyboost(0);

        menuStage = new MenuStage(menuAreaViewport, game, tower1, tower2, tower3, tower4, tower5, tower6);

        font = new BitmapFont();
        font.getData().setScale(5.0f);
        batch = new SpriteBatch();
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 32f);
        gameAreaCamera.setToOrtho(false, numTilesHorizontal, numTilesVertical);
        gameAreaCamera.update();
        b2dr = new Box2DDebugRenderer();
        uiStage = new Stage();
        temps1 = 0;
/*
        Drawable exitImage = new TextureRegionDrawable(Exit);
        ImageButton exitButton = new ImageButton(exitImage);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("dddddd");
                Gdx.app.exit();
                return true;
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
        menu.add(new ShopCell(new Tower(0,"Gun",10, 400, 60,
                50, 24, HEIGHT - Gdx.input.getY(), Pistol1, snowlaser, laser,1, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.add(new ShopCell(new Tower(0,"Item",0, 0, 0, 200, 0, 0, Pistol2, snowlaser, laser, 1, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.row();
        menu.add(new ShopCell(new Tower(0,"Item",0, 0, 0, 150, 0, 0, Pistol3, snowlaser, laser, 1, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.add(new ShopCell(new Tower(0,"Item",0, 0, 0, 75, 0, 0, Pistol4, snowlaser, laser, 1, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.row();
        menu.add(new ShopCell(new Tower(0,"Item",0, 0, 0, 40, 0, 0, Pistol5, snowlaser, laser, 1, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.add(new ShopCell(new Tower(0,"Item",0, 0, 0, 200, 0, 0, Pistol6, snowlaser, laser, 1, mainStage, world), font)).expandX().expandY().colspan(2);
        menu.row();
        menu.add(Coin).expandX().right().size(60, 60).colspan(1);
        menu.add(moneyLabel).expandX().left().expandX().colspan(1);
        menu.add(Heart).expandX().right().size(60,60).colspan(1);
        menu.add(lifeLabel).expandX().left().expandX().colspan(1);*/
    }

    private void initMapCollision(){
        for (int i = 0 ; i<mapCollision.length;i++){
            for (int j = 0 ; j<mapCollision[i].length;j++){
                mapCollision[i][j] = false;
            }
        }
    }

    private void initMapTower(){
        for (int i = 0 ; i<towersMap.length;i++){
            for (int j = 0 ; j<towersMap[i].length;j++){
                towersMap[i][j] = null;
            }
        }
    }

    private void printMapTower(){
        System.out.println("Map");
        for (int i = 0 ; i<towersMap.length;i++){
            for (int j = 0 ; j<towersMap[i].length;j++){
                System.out.println(towersMap[i][j]);
            }
            System.out.println();
        }
        System.out.println("end");
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

        System.out.println("tower in map: "+ col + " "+ row);
        System.out.println("tower in tab: "+ row + " "+ col);
        trueTowerPosition(col, row);
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

    private void deleteTowerMapCol(Vector3 position){
        int col = (int) position.x;
        int row = (numTilesVertical-1) - (int) position.y;
        if (col == numTilesHorizontal) {
            col = numTilesHorizontal - 1;
        }

        System.out.println("tower in map: "+ col + " "+ row);
        System.out.println("tower in tab: "+ row + " "+ col);
        falseTowerPosition(col, row);
    }

    private void falseTowerPosition(int x, int y){
        mapCollision[y][x] = false;
        if(x != mapCollision[0].length-1){
            mapCollision[y][x+1] = false;
        }
        if(x != 0) {
            mapCollision[y][x-1] = false;
        }
        if(y != mapCollision.length-1){
            mapCollision[y+1][x] = false;
        }
        if(y != 0) {
            mapCollision[y-1][x] = false;
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
        nuke_detection(player.getNuke(), round.getEnnemies());
        if (Gdx.input.justTouched()) {
            Vector3 pos3 = gameAreaCamera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0), 0, 0, (int) GAME_WIDTH, (int) HEIGHT);
            System.out.println("x: "+pos3.x +" y:"+ pos3.y);
            System.out.println("click "+(int)pos3.x +" "+ (29 - (int)pos3.y));
            TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) map.getLayers().get(0);
            TiledMapTileLayer.Cell cell = tiledMapTileLayer.getCell((int) pos3.x, (int) pos3.y);
            if (pos3.x >56 && pos3.x <60 && pos3.y>26 && pos3.y<30) {
                game.setScreen(new MenuScreen(game));
            }
            else if (pos3.x >49.5 && pos3.x <53 && pos3.y>20.5 && pos3.y<25.5) {
                if (doubleTap(1)) {
                    menuStage.setInfo(menuStage.getItem1(), true);
                }
                else {
                    lastTouchCell = 1;
                    lastTouchTime = System.currentTimeMillis();
                }
                handleSelected(1);
            }
            else if (pos3.x >49.5 && pos3.x <53 && pos3.y>11.5 && pos3.y<16.5) {
                if (doubleTap(3)) {
                    menuStage.setInfo(menuStage.getItem3(), true);
                }
                else {
                    lastTouchCell = 3;
                    lastTouchTime = System.currentTimeMillis();
                }
                handleSelected(3);
            }
            else if (pos3.x >49.5 && pos3.x <53 && pos3.y>3 && pos3.y<8) {
                if (doubleTap(5)) {
                    menuStage.setInfo(menuStage.getItem5(), true);
                }
                else {
                    lastTouchCell = 5;
                    lastTouchTime = System.currentTimeMillis();
                }
                handleSelected(5);
            }
            else if (pos3.x >55.5 && pos3.x <59 && pos3.y>20.5 && pos3.y<25.5) {
                if (doubleTap(2)) {
                    menuStage.setInfo(menuStage.getItem2(), true);
                }
                else {
                    lastTouchCell = 2;
                    lastTouchTime = System.currentTimeMillis();
                }
                handleSelected(2);
            }
            else if (pos3.x >55.5 && pos3.x <59 && pos3.y>11.5 && pos3.y<16.5) {
                if (doubleTap(4)) {
                    menuStage.setInfo(menuStage.getItem4(), true);
                }
                else {
                    lastTouchCell = 4;
                    lastTouchTime = System.currentTimeMillis();
                }
                handleSelected(4);
            }
            else if (pos3.x >55.5 && pos3.x <59 && pos3.y>3 && pos3.y<8) {
                if (doubleTap(6)) {
                    menuStage.setInfo(menuStage.getItem6(), true);
                }
                else {
                    lastTouchCell = 6;
                    lastTouchTime = System.currentTimeMillis();
                }
                handleSelected(6);
            }
            else if (cell != null) {
                if(dialogSellOrNot){
                    if(pos3.x>1.1 && pos3.x<6.7 && pos3.y>6.3 && pos3.y<8.5){
                        System.out.println("SELL");
                        System.out.println("sell: x : "+ posTowerSell.x + " y: "+ posTowerSell.y);
                        player.sellWeapon(towerToSell);
                        deleteTowerMapCol(posTowerSell);
                        towersMap[(numTilesVertical-1) - (int)posTowerSell.y][(int)posTowerSell.x] = null;
                        removeEmptyTowerMap((int)posTowerSell.x,(numTilesVertical-1) - (int)posTowerSell.y);
                        dialogSellOrNot = false;
                        islevelmax = false;
                        towerToSell = null;
                        posTowerSell = null;
                    }else if(pos3.x>0.63 && pos3.x<7.2 && pos3.y>3.5 && pos3.y<5.4){
                        System.out.println("Upgrade");
                        player.upgradeWeapon(towerToSell);
                        dialogSellOrNot = false;
                        islevelmax = false;
                        towerToSell = null;
                        posTowerSell = null;
                    }else if(pos3.x>2.2 && pos3.x<5.1 && pos3.y>1 && pos3.y<2.5){
                        System.out.println("CANCEL");
                        dialogSellOrNot = false;
                        islevelmax = false;
                        towerToSell = null;
                        posTowerSell = null;
                    }
                }
                else if(round.getTemps() > 50 && round.getRoundnbr() < 40 && !dialogSellOrNot){
                    if(pos3.x>0.86 && pos3.x<6.8 && pos3.y>3.5 && pos3.y<5.9){
                        System.out.println("SKIP");
                        round.setTemps(1800);
                    }
                }
            } else {
                    float offset = Gdx.graphics.getWidth() / 48;
                    float x = (pos3.x * offset);
                    if (pos3.x < numTilesHorizontal && pos3.y < numTilesVertical) {
                        if(towersMap[(numTilesVertical-1) - (int)pos3.y][(int)pos3.x] != null){
                            System.out.println("vendre");
                            System.out.println(towersMap[(numTilesVertical-1) - (int)pos3.y][(int)pos3.x].getName());
                            dialogSellOrNot = true;
                            if(towersMap[(numTilesVertical-1) - (int)pos3.y][(int)pos3.x].getUpgraded() == 3){
                                islevelmax = true;
                            }
                            if(towersMap[(numTilesVertical-1) - (int)pos3.y][(int)pos3.x].isOrigin()){
                                System.out.println("Origin");
                                towerToSell = towersMap[(numTilesVertical-1) - (int)pos3.y][(int)pos3.x];
                                posTowerSell = pos3;
                                System.out.println("x: " + posTowerSell.x + " y: " + posTowerSell.y);
                            }else{
                                System.out.println("Not Origin");
                                int xOrigin = towersMap[(numTilesVertical-1) - (int)pos3.y][(int)pos3.x].getxOrigin();
                                int yOrigin = towersMap[(numTilesVertical-1) - (int)pos3.y][(int)pos3.x].getyOrigin();
                                towerToSell = towersMap[yOrigin][xOrigin];
                                posTowerSell = towersMap[(numTilesVertical-1) - (int)pos3.y][(int)pos3.x].getPosOrigin();
                                System.out.println("xO: " + posTowerSell.x + " yO: " + posTowerSell.y);
                            }
                        }else if(selectedTower != null && player.canBuyNot(selectedTower) && checkPosTower(pos3)) {
                            putTowerMapCol(pos3);
                            if(selectedTower.getID() < 5) {
                                Tower tower = new Tower(selectedTower.getID(), selectedTower.getName(), selectedTower.getDamage(), selectedTower.getRange(), selectedTower.getFireRate(), selectedTower.getPrice(), x, HEIGHT - Gdx.input.getY(), selectedTower.getGlobalTexture(), selectedTower.getBase_texture(), selectedTower.getWeapon_texture(), selectedTower.getLaserTexture(), selectedTower.getLasersize(), mainStage, world);
                                player.buyWeapons(tower);
                                towersMap[(numTilesVertical-1) - (int)pos3.y][(int)pos3.x] = tower;
                                putEmptyTowerMap(pos3);
                            }
                            else if(selectedTower.getID() == 5) {
                                FreezeTower tower = new FreezeTower(selectedTower.getID(), selectedTower.getName(), selectedTower.getDamage(), selectedTower.getRange(), selectedTower.getFireRate(), selectedTower.getPrice(), x, HEIGHT - Gdx.input.getY(), selectedTower.getBase_texture(), selectedTower.getWeapon_texture(), selectedTower.getLaserTexture(), selectedTower.getLasersize(), mainStage, world, 2);
                                player.buyWeapons(tower);
                                towersMap[(numTilesVertical-1) - (int)pos3.y][(int)pos3.x] = tower;
                                putEmptyTowerMap(pos3);
                            }
                            else{
                                MoneyTower tower = new MoneyTower(selectedTower.getID(), selectedTower.getName(), selectedTower.getDamage(), selectedTower.getRange(), selectedTower.getFireRate(), selectedTower.getPrice(), x, HEIGHT - Gdx.input.getY(), selectedTower.getBase_texture(), selectedTower.getWeapon_texture(), selectedTower.getLaserTexture(), selectedTower.getLasersize(), mainStage, world, 1.2f, player);
                                player.buyWeapons(tower);
                                towersMap[(numTilesVertical-1) - (int)pos3.y][(int)pos3.x] = tower;
                                putEmptyTowerMap(pos3);
                            }
                            menuStage.deselect();
                            selectedTower = null;
                        }else {
                            System.out.println("trop proche !!");
                        }
                    }
            }
        }
    }

    private boolean doubleTap(int i) {
        return (lastTouchCell==i && System.currentTimeMillis()-lastTouchTime < 400);
    }

    private void putEmptyTowerMap(Vector3 pos){
        Tower emptyTower = new Tower((int)pos.x, (numTilesVertical-1) - (int)pos.y, pos);
        if((numTilesVertical-1) - (int)pos.y != towersMap.length - 1 ){
            towersMap[((numTilesVertical-1) - (int)pos.y)+1][(int)pos.x] = emptyTower;
        }
        if((numTilesVertical-1) - (int)pos.y !=0 ){
            towersMap[((numTilesVertical-1) - (int)pos.y)-1][(int)pos.x] = emptyTower;
        }
        if((int) pos.x != towersMap[0].length - 1){
            towersMap[((numTilesVertical-1) - (int)pos.y)][(int)pos.x + 1] = emptyTower;
        }
        if((int)pos.x != 0){
            towersMap[((numTilesVertical-1) - (int)pos.y)][(int)pos.x - 1] = emptyTower;
        }

    }

    private void removeEmptyTowerMap(int x, int y){
        if(y != towersMap.length - 1 ){
            towersMap[y+1][x] = null;
        }
        if(y != 0 ){
            towersMap[y-1][x] = null;
        }
        if(x != towersMap[0].length - 1){
            towersMap[y][x+1] = null;
        }
        if(x != 0 ){
            towersMap[y][x-1] = null;
        }
    }

    public void update(float dt){
        if(player.getNuke().getCooldown() > 0){
            player.getNuke().setCooldown(player.getNuke().getCooldown()-1);
        }
        if(player.getLife() <= 0 && round.getRoundnbr() < 40){
            this.gameOver = true;
            game.setScreen(new LoseScreen(game, player));
        }
        else if(round.getRoundnbr() == 40 && player.getLife()<=0){
            player.setScore(player.getScore());
            this.gameOver = true;
            game.setScreen(new WinScreen(game, player));
        }
        else {
            handleInput(dt);
            round.update(dt, game, player, mainStage);
            if (round.getRoundnbr() == 0 && ennemycount < 1) { //demarre le premier round
                round.round0(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 1 && ennemycount < 3 && temps1 > 125) { //demarre le premier round
                round.round1(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 2 && ennemycount < 6 && temps1 > 125) {
                round.round2(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 3 && ennemycount < 7 && temps1 > 125) {
                round.round3(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 4 && ennemycount < 12 && temps1 > 100) {
                round.round4(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 5 && ennemycount < 16 && temps1 > 125) {
                round.round5(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 6 && ennemycount < 18 && temps1 > 150) {
                round.round6(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 7 && ennemycount < 26 && temps1 > 125) {
                round.round7(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 8 && ennemycount < 36 && temps1 > 60) {
                round.round8(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 9 && ennemycount < 37 && temps1 > 125) {
                round.round9(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 10 && ennemycount < 41 && temps1 > 125) {
                round.round10(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 11 && ennemycount < 42 && temps1 > 125) {
                round.round11(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 12 && ennemycount < 43 && temps1 > 125) {
                round.round12(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 13 && ennemycount < 47 && temps1 > 125) {
                round.round13(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 14 && ennemycount < 48 && temps1 > 150) {
                player.getNuke().setNumber(player.getNuke().getNumber()+1);
                round.round14(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 15 && ennemycount < 53 && temps1 > 125) {
                round.round15(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 16 && ennemycount < 55 && temps1 > 125) {
                round.round16(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 17 && ennemycount < 58 && temps1 > 125) {
                round.round17(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 18 && ennemycount < 59 && temps1 > 125) {
                round.round18(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 19 && ennemycount < 60 && temps1 > 125) {
                round.round19(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 20 && ennemycount < 63 && temps1 > 125) {
                round.round20(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 21 && ennemycount < 68 && temps1 > 100) {
                round.round21(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 22 && ennemycount < 70 && temps1 > 125) {
                round.round22(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 23 && ennemycount < 73 && temps1 > 125) {
                round.round23(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 24 && ennemycount < 74 && temps1 > 125) {
                round.round24(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 25 && ennemycount < 78 && temps1 > 175) {
                round.round25(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 26 && ennemycount < 81 && temps1 > 150) {
                round.round26(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 27 && ennemycount < 82 && temps1 > 125) {
                round.round27(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 28 && ennemycount < 87 && temps1 > 125) {
                round.round28(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 29 && ennemycount < 88 && temps1 > 125) {
                player.getNuke().setNumber(player.getNuke().getNumber()+1);
                round.round29(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 30 && ennemycount < 93 && temps1 > 125) {
                round.round30(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 31 && ennemycount < 96 && temps1 > 125) {
                round.round31(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 32 && ennemycount < 98 && temps1 > 125) {
                round.round32(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 33 && ennemycount < 100 && temps1 > 125) {
                round.round33(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 34 && ennemycount < 101 && temps1 > 125) {
                round.round34(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 35 && ennemycount < 102 && temps1 > 125) {
                round.round35(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 36 && ennemycount < 105 && temps1 > 125) {
                round.round36(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 37 && ennemycount < 106 && temps1 > 125) {
                round.round37(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 38 && ennemycount < 111 && temps1 > 125) {
                round.round38(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 39 && ennemycount < 112 && temps1 > 125) {
                round.round39(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            else if (round.getRoundnbr() == 40 && ennemycount < 1000 && temps1 > 500) {
                if(!endcapture){
                    player.setEndlife(player.getLife());
                    endcapture = true;
                }
                round.finalround(temps, uiStage, world, ennemycount);
                ennemycount++;
                temps1 = 0;
            }
            temps1++;
            world.step(1 / 60f, 6, 2);

        }
    }

    /**
     * This function will detect a movement, check if the player has a nuke and then explode all ennemies
     * @param nuke
     * @param ennemies
     */
    public void nuke_detection (Nuke nuke, LinkedList<Ennemy> ennemies){
        if (nuke.detectMovement()){     //A movement has been detected
            if (nuke.isAvailable()){            //The player has at least 1 nuke
                nuke.explode(ennemies);
            }
        }
    }

    @Override
    public void show() {
        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(menuStage);
        im.addProcessor(uiStage);
        im.addProcessor(mainStage);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(34/255f, 34/255f, 34/255f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glViewport(0, 0, (int) GAME_WIDTH, (int) HEIGHT);
        drawGameArea(delta);

        Gdx.gl.glViewport((int) GAME_WIDTH, 0, (int) MENU_WIDTH, (int) HEIGHT);
        menuStage.act();
        menuStage.draw();

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

    private void propertiesButton(TextButton button,int x, int y, float scale){
        System.out.println("Height "+ HEIGHT);
        button.setPosition(x, y);
        button.setTransform(true);
        button.setScale(scale);
    }

    private void drawGameArea(float delta) {
        update(delta);

        gameAreaCamera.update();
        renderer.setView(gameAreaCamera);
        renderer.render();

        b2dr.render(world, gameAreaCamera.combined);

        batch.begin();
        if(dialogSellOrNot){
            sellButton.draw(batch, 1);
            cancelButton.draw(batch, 1);
            if(towerToSell.getUpgraded() < 3 && towerToSell.getID()==1) {
                upgradeButton1.draw(batch, 1);
            }
            else if(towerToSell.getUpgraded() < 3 && towerToSell.getID()==2) {
                upgradeButton2.draw(batch, 1);
            }
            else if(towerToSell.getUpgraded() < 3 && towerToSell.getID()==3) {
                upgradeButton3.draw(batch, 1);
            }
            else if(towerToSell.getUpgraded() < 3 && towerToSell.getID()==4) {
                upgradeButton4.draw(batch, 1);
            }
            else if(towerToSell.getUpgraded() < 3 && towerToSell.getID()==5) {
                upgradeButton5.draw(batch, 1);
            }
        }
        else if(round.getTemps() > 50 && !dialogSellOrNot && round.getRoundnbr() < 40){
            skipButton.draw(batch, 1);
        }
        font.setColor(Color.BLACK);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), Gdx.graphics.getWidth()-110, 30);
        //font.getData().setScale(1.8f);
        if(round.getRoundnbr()>-1 && round.getRoundnbr()<40) {
            font.draw(batch, "Round: " + (round.getRoundnbr()+1), 10, HEIGHT - 10);
        }
        else if(round.getRoundnbr() == 40){
            font.draw(batch, "BONUS ROUND" , 10, HEIGHT - 10);
        }
        font.getData().setScale(2f);
        if(round.getRoundnbr() != -1) {
            round.draw(batch);
        }
        if(!player.isEmptyWeapons()) {
            for (Tower tower : player.getWeapons()) {
                tower.getBase_sprite().setPosition(tower.getX(), tower.getY()-25);
                tower.getBase_sprite().draw(batch);
                tower.getWeapon_sprite().setPosition(tower.getX()-29, tower.getY()-27);
                tower.getWeapon_sprite().draw(batch);
                if(tower.getTempscercle() < 120){
                    tower.setTempscercle(tower.getTempscercle()+1);
                    circle.setScale(tower.getRange() / 200, tower.getRange() / 250);
                    circle.setPosition(tower.getX() - 240, tower.getY() - 250);
                    circle.draw(batch);
                }
            }
        }
        if (round.getRoundnbr() != -1) {
            for(Tower tower : player.getWeapons()) {
                round.shoot(tower, batch, delta, world, game, uiStage);
            }
            temps = 0;
        }
        if(round.getRoundnbr() != -1) {
            for(Tower tower : player.getWeapons()) {
                round.updateLaser(delta, batch, game, uiStage, tower);
            }
        }
        if(round.getRoundnbr() != -1){
            for(Tower tower : player.getWeapons()){
                round.updateTower(delta, batch, game, uiStage, tower);
            }
        }
        if(player.getNuke().getCooldown() > 300){
            transitionmoab = (float) ((player.getNuke().getCooldown() - 300)/300);
            moab.setScale(4);
            moab.setAlpha(transitionmoab);
            moab.draw(batch);
        }
        temps++;
        batch.end();
    }


    private void handleSelected(int i){
        switch (i) {
            case 1: {
                if (menuStage.getItem1().isSelected()) {
                    menuStage.getItem1().setSelected(false);
                    selectedTower = null;
                }
                else if (selectedTower != null) {
                    menuStage.deselect();
                    menuStage.getItem1().setSelected(true);
                    selectedTower = tower1;
                }
                else {
                    menuStage.getItem1().setSelected(true);
                    selectedTower = tower1;
                }
                break;
            }
            case 2: {
                if (menuStage.getItem2().isSelected()) {
                    menuStage.getItem2().setSelected(false);
                    selectedTower = null;
                }
                else if (selectedTower != null) {
                    menuStage.deselect();
                    menuStage.getItem2().setSelected(true);
                    selectedTower = tower2;
                }
                else {
                    menuStage.getItem2().setSelected(true);
                    selectedTower = tower2;
                }
                break;
            }
            case 3: {
                if (menuStage.getItem3().isSelected()) {
                    menuStage.getItem3().setSelected(false);
                    selectedTower = null;
                }
                else if (selectedTower != null) {
                    menuStage.deselect();
                    menuStage.getItem3().setSelected(true);
                    selectedTower = tower3;
                }
                else {
                    menuStage.getItem3().setSelected(true);
                    selectedTower = tower3;
                }
                break;
            }
            case 4: {
                if (menuStage.getItem4().isSelected()) {
                    menuStage.getItem4().setSelected(false);
                    selectedTower = null;
                }
                else if (selectedTower != null) {
                    menuStage.deselect();
                    menuStage.getItem4().setSelected(true);
                    selectedTower = tower4;
                }
                else {
                    menuStage.getItem4().setSelected(true);
                    selectedTower = tower4;
                }
                break;
            }
            case 5: {
                if (menuStage.getItem5().isSelected()) {
                    menuStage.getItem5().setSelected(false);
                    selectedTower = null;
                }
                else if (selectedTower != null) {
                    menuStage.deselect();
                    menuStage.getItem5().setSelected(true);
                    selectedTower = tower5;
                }
                else {
                    menuStage.getItem5().setSelected(true);
                    selectedTower = tower5;
                }
                break;
            }
            case 6: {
                if (menuStage.getItem6().isSelected()) {
                    menuStage.getItem6().setSelected(false);
                    selectedTower = null;
                }
                else if (selectedTower != null) {
                    menuStage.deselect();
                    menuStage.getItem6().setSelected(true);
                    selectedTower = tower6;
                }
                else {
                    menuStage.getItem6().setSelected(true);
                    selectedTower = tower6;
                }
                break;
            }
        }
    }

}