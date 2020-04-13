package com.mygdx.game.model.entity;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.model.utils.BaseActor;

public class Tower extends BaseActor {

    private int ID;
    private String name;
    private int damage;
    private int range;
    private int fireRate;
    private int price;
    private boolean locked;
    private Sprite base_sprite;
    private Sprite weapon_sprite;
    private Texture base_texture;
    private Texture weapon_texture;
    private Texture laserTexture;
    private Sprite sprite;
    Laser laser;
    private World world;
    private int ennemyinrange;
    public float temps;
    private Sound sound;

    public Tower(int ID ,String nom, int degats, int portee, int cadence, int prix, float x, float y, Texture base_texture, Texture gun_texture, Texture texture2, float lasersize, Stage s, World world){
        super(x,y,s);
        this.ID = ID;
        this.name = nom;
        this.damage = degats;
        this.range = portee;
        this.fireRate = cadence;
        this.price = prix;
        this.locked = true;
        this.base_texture = base_texture;
        this.weapon_texture = gun_texture;
        base_sprite = new Sprite(base_texture);
        weapon_sprite = new Sprite(gun_texture);
        this.world = world;
        this.laserTexture = texture2;
        if(prix == 400){
            sound = (Gdx.audio.newSound(Gdx.files.internal("tir.mp3")));
        }
        else {
            sound = (Gdx.audio.newSound(Gdx.files.internal("Laser1.wav")));
        }
        laser = new Laser(50000, 50000, lasersize, texture2, 2000, sound, s, world);
        laser.defineLaser();
        temps = 0;
        long soundId;
    }

    //First checks if there is a Stage
    //Then checks if the ennemy is in range
    public void shoot(Ennemy ennemy, SpriteBatch batch, float dt, World world, Game game, Stage stage) //tire sur l ennemi
    {
        //if (getStage() == null) {
        //    return;
        //}
        if(price != 400) {
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

    public int getRange(){
        return range;
    }

    public int getFireRate(){
        return fireRate;
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

    public Sprite getBase_sprite() { return base_sprite;}

    public Sprite getWeapon_sprite() { return weapon_sprite;}

    public Texture getBase_texture() { return base_texture;}

    public Texture getWeapon_texture() {return weapon_texture;}

    public Texture getLaserTexture() { return laserTexture;}

    public int getID(){
        return ID;
    }
}
