package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.awt.Rectangle;

public class Ennemy extends BaseActor{

    private int life;
    private int speed;
    private int point;
    private Texture texture;
    private Body b2body;
    private float stateTime;
    private float direction;
    public World world;


    //public int damage;  Si on veut faire en sorte qu'un ennemy puisse attaquer une tour

    public Ennemy(int life, int speed, int point, Texture texture, float direction, Stage s, World world){
        super(0, 1200, s);
        this.life = life;
        this.point = point;
        this.texture = texture;
        this.direction = direction;
        this.setSpeed(speed);
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        this.world = world;
    }

    public void defineEnnemy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(0, 1200);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);
        fdef.shape = shape;
        b2body.createFixture(fdef);
        setBoundaryRectangle();
    }


    public boolean isAlive(){
        if (getLife() <= 0)
            return false;
        return true;
    }

    public void update(float dt) {
        stateTime += dt;
        if(direction == 0) {
            setPosition(getX() + dt * speed, getY());
        }
        if(direction == 90) {
            setPosition(getX(), getY() + dt * speed);
        }
        if(direction == 180){
            setPosition(getX() - dt * speed, getY());
        }
        if(direction == 270){
            setPosition(getX(), getY() - dt * speed);
        }
    }

    public void act(float dt){
        super.act( dt );
        boundToWorld();
    }


    public int getLife(){
        return life;
    }

    public int getPoint(){
        return point;
    }

    public void setLife(int life){
        this.life = life;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public Texture getTexture(){
        return texture;
    }
}