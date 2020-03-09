package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.concurrent.TimeUnit;

public class Tower extends BaseActor{

    private int damage;
    private int shoot;
    private int range;
    private int fireRate;
    private int price;
    private boolean locked;
    private Texture texture;
    int distance; // ne doit pas etre ici mais permet de compiler en attendant, distance a calculer avec getX et getY

    public Tower(int degats, int portee, int cadence, int prix, float x, float y, Texture texture, Stage s){
        super(x,y,s);
        this.damage = degats;
        this.range = portee;
        this.fireRate = cadence;
        this.price = prix;
        this.locked = true;
        this.texture = texture;
    }

    //First checks if there is a Stage
    //Then checks if the ennemy is in range
    public void shoot(Ennemy ennemy, SpriteBatch batch, float dt, World world, Game game, Laser laser) //est-ce qu'on utilise toujours un "ennemy" ?
    {
        if ( getStage() == null )
            return;

        if (ennemy.isInRange(this)) {
            laser.defineLaser();
            laser.centerAtActor(this);
            this.shoot = 1;
        }
    }

    public boolean isShooting(){
        if(this.shoot == 1){
            this.shoot = 0;
            return true;
        }
        return false;
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

    public Texture getTexture(){
        return this.texture;
    }

    public boolean isLocked(){
        return this.locked;
    }

    public void setLocked(boolean locked){
        this.locked = locked;
    }

}
