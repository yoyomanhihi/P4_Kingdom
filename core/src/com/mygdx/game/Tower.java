package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.concurrent.TimeUnit;

public class Tower extends BaseActor{

    public int damage;
    public int range;
    public int fireRate;
    public int price;
    public boolean locked;
    public Texture texture;
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


    public void shoot()
    {
        if ( getStage() == null )
            return;
        Laser laser = new Laser(0,0, this.getStage());
        laser.centerAtActor(this);
        laser.setRotation( this.getRotation() );
        laser.setMotionAngle( this.getRotation() );
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

}
