package com.mygdx.game.model.entity;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.model.utils.BaseActor;

public class Tower extends BaseActor {

    private int ID;
    private String name;
    private int damage;
    private float range;
    private int fireRate;
    private int price;
    private boolean locked;
    private Sprite globalSprite;
    private Sprite base_sprite;
    private Sprite gun_sprite;
    private Texture base_texture;
    private Texture gun_texture;
    private Texture laserTexture;
    private Texture globalTexture;
    private Sprite sprite;
    Laser laser;
    private World world;
    private float temps;
    private Sound sound;
    private float lasersize;
    private float tempscercle;
    private boolean isOrigin = true;
    private int xOrigin;
    private int yOrigin;
    private Vector3 posOrigin;
    private int upgraded;


    /**
     * Default constructor of Tower.
     * Create a empty Tower to simulate the cell of the Tower.
     * @param x the origin cell-x of the Tower.
     * @param y the origin cell-y of the Tower.
     */
    public Tower(int x, int y, Vector3 posOrigin){
        this.name = "empty";
        this.isOrigin = false;
        this.xOrigin = x;
        this.yOrigin = y;
        this.posOrigin = posOrigin;
    }

    public Tower(int ID ,String nom, int degats, float portee, int cadence, int prix, float x, float y, Texture globalTexture, Texture base_texture, Texture gun_texture, Texture texture2, float lasersize, Stage s, World world){
        super(x,y,s);
        this.ID = ID;
        this.name = nom;
        this.damage = degats;
        this.range = portee;
        this.fireRate = cadence;
        this.price = prix;
        this.locked = true;
        this.globalTexture = globalTexture;
        this.base_texture = base_texture;
        this.gun_texture = gun_texture;
        this.lasersize = lasersize;
        globalSprite = new Sprite(globalTexture);
        base_sprite = new Sprite(base_texture);
        gun_sprite = new Sprite(gun_texture);
        base_sprite.setScale(1.25f, 1);
        gun_sprite.setScale(1.25f, 1);
        this.world = world;
        this.laserTexture = texture2;
        this.upgraded = 0;
        if(prix == 375){
            sound = (Gdx.audio.newSound(Gdx.files.internal("tir.mp3")));
        }
        else if(prix == 250){
            sound = (Gdx.audio.newSound(Gdx.files.internal("ice.wav")));
        }
        else if(prix == 300){
            base_sprite.setScale(.625f, .5f);
        }
        else {
            sound = (Gdx.audio.newSound(Gdx.files.internal("Laser1.wav")));
        }
        laser = new Laser(50000, 50000, lasersize, texture2, 2000, sound, s, world);
        if(prix == 150){
            laser.purple();
        }
        if(prix == 375){
            laser.yellow();
            laser.setSpeed(3000);
        }
        if(prix == 800){
            laser.red();
            laser.setSpeed(1200);
        }
        laser.defineLaser();
        temps = 0;
        long soundId;
        tempscercle = 0;
    }

    //First checks if there is a Stage
    //Then checks if the ennemy is in range
    public void shoot(Ennemy ennemy, SpriteBatch batch, float dt, World world, Game game, Stage stage) //tire sur l ennemi
    {
        //if (getStage() == null) {
        //    return;
        //}
        if(price != 375 && price != 250) {
            laser.getSound().play(0.15f);
        }
        else{
            laser.getSound().play();
        }
        laser.centerAtActor(this);
        laser.update(dt, batch, ennemy, game, stage);
    }

    public void updateLaser(float dt, SpriteBatch batch, Ennemy Ennemy, Game game, Stage stage){ // fait que le laser suit l ennemi
        if(laser != null) {
            if (laser.getX() != this.getX() && laser.getY() != this.getY()) {
                laser.update(dt, batch, Ennemy, game, stage);
            }
            if (laser.overlaps(Ennemy)) { //verifie si le laser touche l ennemi
                Ennemy.setLife(Ennemy.getLife() - getDamage());
                laser.setPosition(500000, 500000);
            }
            temps++;
        }
    }

    public void updateTower(float dt, SpriteBatch batch, Ennemy ennemy, Game game, Stage stage){
        this.getWeapon_sprite().setRotation(getOrientation(ennemy)-90);
    }

    public String getName() {return name; }

    public int getPrice(){
        return price;
    }

    public float getRange(){
        return range;
    }

    public int getFireRate(){
        return fireRate;
    }

    public void setFireRate(int fireRate){
        this.fireRate = fireRate;
    }

    public int getDamage(){
        return damage;
    }

    public boolean isLocked(){
        return this.locked;
    }

    public void setLocked(boolean locked){
        this.locked = locked;
    }

    public float getTemps(){
        return temps;
    }

    public void setTemps(float temps){
        this.temps = temps;
    }

    public float getLasersize(){
        return lasersize;
    }

    public Sprite getGlobalSprite() {
        return globalSprite;
    }

    public Sprite getBase_sprite() { return base_sprite;}

    public Sprite getWeapon_sprite() { return gun_sprite;}

    public Texture getGlobalTexture() {
        return globalTexture;
    }

    public Texture getBase_texture() { return base_texture;}

    public Texture getWeapon_texture() {return gun_texture;}

    public Texture getLaserTexture() { return laserTexture;}

    public int getID(){
        return ID;
    }

    public float getTempscercle(){
        return tempscercle;
    }

    public void setTempscercle(float tempscercle){
        this.tempscercle = tempscercle;
    }

    public boolean isOrigin() {
        return isOrigin;
    }

    public void setOrigin(boolean origin) {
        isOrigin = origin;
    }

    public int getxOrigin() {
        return xOrigin;
    }

    public void setxOrigin(int xOrigin) {
        this.xOrigin = xOrigin;
    }

    public int getyOrigin() {
        return yOrigin;
    }

    public void setyOrigin(int yOrigin) {
        this.yOrigin = yOrigin;
    }

    public Vector3 getPosOrigin() {
        return posOrigin;
    }

    public void setPosOrigin(Vector3 posOrigin) {
        this.posOrigin = posOrigin;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setUpgraded(int upgraded){
        this.upgraded = upgraded;
    }

    public int getUpgraded(){
        return this.upgraded;
    }


}
