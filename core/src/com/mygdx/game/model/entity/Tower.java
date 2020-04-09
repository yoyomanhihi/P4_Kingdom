package com.mygdx.game.model.entity;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.model.utils.BaseActor;

public class Tower extends BaseActor {

    private int ID;
    public static String name;
    private int damage;
    private int range;
    private int fireRate;
    private int price;
    private boolean locked;
    private Sprite base_sprite;
    private Sprite weapon_sprite;
    private Texture texture;
    private Sprite sprite;
    public Laser laser;
    private World world;
    private int ennemyinrange;
    public float temps;

    public Tower(int ID ,String nom, int degats, int portee, int cadence, int prix, float x, float y, Texture base_texture, Texture gun_texture, Texture texture2, float lasersize, Stage s, World world){
        super(x,y,s);
        this.ID = ID;
        this.name = nom;
        this.damage = degats;
        this.range = portee;
        this.fireRate = cadence;
        this.price = prix;
        this.locked = true;
        base_sprite = new Sprite(base_texture);
        weapon_sprite = new Sprite(gun_texture);
        this.world = world;
        this.ennemyinrange = 0;
        laser = new Laser(50000, 50000, lasersize, texture2, s, world);
        laser.defineLaser();
        temps = 0;
        long soundId;
    }

    public float getDistance(Ennemy Ennemy){ //calcule la distance avec l ennemi
        return (float)Math.sqrt(Math.pow(this.getX()-Ennemy.getX(),2) + Math.pow(this.getY()-Ennemy.getY(),2));
    }

    public boolean isInRange(Ennemy Ennemy){ //verifie si l ennemi est a portee de tir mais on utilise autre chose
        float distance = this.getDistance(Ennemy);
        if (this.getRange() >= distance)
            return true;
        return false;
    }

    /*public float getOrientation(Ennemy Ennemy){ // s oriente vers l ennemi mais on utiliser autre chose
        float hypotenuse = this.getDistance(Ennemy);
        float diff_x = this.getX()-Ennemy.getX();
        float degre = (float)Math.acos(diff_x/hypotenuse);

        if (this.getX() < Ennemy.getX() && this.getY() < Ennemy.getY()) { //L'ennemi est en haut à gauche de la tour
            degre += 90;
        }
        else if (this.getX() < Ennemy.getX() && this.getY() == Ennemy.getY()){ //L'ennemi est pile à gauche de la tour
            degre = 180;
        }
        else if (this.getX() < Ennemy.getX() && this.getY() > Ennemy.getY()){ //L'ennemi est en bas à gauche de la tour
            degre += 180;
        }
        else if (this.getX() == Ennemy.getX() && this.getY() > Ennemy.getY()){ //L'ennemmi est pile en bas de la tour
            degre = 270;
        }
        else if (this.getX() > Ennemy.getX() && this.getY() > Ennemy.getY()){ //L'ennemi est en bas à droite de la tour
            degre += 270;
        }
        return degre;
    }
    */


    //First checks if there is a Stage
    //Then checks if the ennemy is in range
    public void shoot(Ennemy ennemy, SpriteBatch batch, float dt, World world, Game game, Stage stage) //tire sur l ennemi
    {
        if (getStage() == null) {
            return;
        }
        laser.getSound().play(0.15f);
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

    public int getEnnemyinrange(){
        return ennemyinrange;
    }

    public void setEnnemyinrange(int ennemynbr){
        this.ennemyinrange = ennemynbr;
    }

    public float getTemps(){
        return temps;
    }

    public void setTemps(float temps){
        this.temps = temps;
    }

    public Sprite getBase_sprite() { return base_sprite;}

    public Sprite getWeapon_sprite() { return weapon_sprite;}

    public Texture getTexture() { return texture; }
}
