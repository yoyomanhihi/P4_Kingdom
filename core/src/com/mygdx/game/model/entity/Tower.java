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

    private int damage;
    private int range;
    private int fireRate;
    private int price;
    private boolean locked;
    //private Texture texture;
    private Sprite sprite;
    private Laser [] laser1;
    private World world;
    private int ennemyinrange;
    private float temps;

    public Tower(int degats, int portee, int cadence, int prix, float x, float y, Texture texture, Stage s, World world){
        super(x,y,s);
        this.damage = degats;
        this.range = portee;
        this.fireRate = cadence;
        this.price = prix;
        this.locked = true;
        //this.texture = texture;
        sprite = new Sprite(texture);
        sprite.rotate(180);
        this.world = world;
        this.ennemyinrange = 0;
        laser1 = new Laser[1];
        laser1[0]= new Laser(50000, 50000, s, world);
        laser1[0].defineLaser();
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

    public float getOrientation(Ennemy Ennemy){ // s oriente vers l ennemi mais on utiliser autre chose
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

    //First checks if there is a Stage
    //Then checks if the ennemy is in range
    public void shoot(Ennemy ennemy, SpriteBatch batch, float dt, World world, Game game, Stage stage) //tire sur l ennemi
    {
        if (getStage() == null) {
            return;
        }
        laser1[0].getSound().play(0.15f);
        laser1[0].centerAtActor(this);
        laser1[0].update(dt, batch, ennemy, game, stage);
    }

    public void updateLaser(float dt, SpriteBatch batch, Ennemy Ennemy, Game game, Stage stage){ // fait que le laser suit l ennemi
        if(laser1[0] != null) {
            if (laser1[0].getX() != this.getX() && laser1[0].getY() != this.getY()) {
                laser1[0].update(dt, batch, Ennemy, game, stage);
            }
            if (laser1[0].overlaps(Ennemy)) { //verifie si le laser touche l ennemi
                Ennemy.setLife(Ennemy.getLife() - 30);
                laser1[0].setPosition(500000, 500000);
            }
            temps++;
        }
    }


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

    /*
    public Texture getTexture(){
        return this.texture;
    }
    */

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

    public Sprite getSprite(){
        return sprite;
    }

}
