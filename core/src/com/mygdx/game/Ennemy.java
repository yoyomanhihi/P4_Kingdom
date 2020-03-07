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

public class Ennemy extends BaseActor{

    public int life;
    public int speed;
    public int point;
    public Texture texture;
    public Body b2body;


    //public int damage;  Si on veut faire en sorte qu'un ennemy puisse attaquer une tour

    public Ennemy(int life, int speed, int current_life, int point, Texture texture, Stage s){
        super(200, 1200, s);
        this.life = life;
        this.point = point;
        this.texture = texture;
        this.setSpeed(speed);
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;
    }

    public void defineEnnemy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(0, 1200);
        bdef.type = BodyDef.BodyType.DynamicBody;
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }


    public boolean isAlive(){
        if (getLife() <= 0)
            return false;
        return true;
    }

    public void update(float dt) {
        this.setSpeed(20);
        setPosition(getX(), getY());

    }


    public int getLife(){
        return life;
    }

    public int getPoint(){
        return point;
    }

    public void setLife(Ennemy Ennemy, int current_life){
        Ennemy.life = life;
    }

    public void setSpeed(Ennemy Ennemy, int speed){
        Ennemy.speed = speed;
    }

    public Texture getTexture(){
        return texture;
    }
}