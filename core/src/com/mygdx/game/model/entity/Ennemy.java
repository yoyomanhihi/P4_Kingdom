package com.mygdx.game.model.entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.model.utils.BaseActor;
import com.mygdx.game.view.screen.WinScreen;

public class Ennemy extends BaseActor {

    private int life;
    private int speed;
    private int point; // l argent qu il rapporte s il meurt
    private Texture texture;
    private Body b2body;
    private float direction;
    private World world;


    //public int damage;  Si on veut faire en sorte qu'un ennemy puisse attaquer une tour

    public Ennemy(int life, int speed, int point, Texture texture, Stage s, World world){
        super(0, 888, s);
        this.life = life;
        this.point = point;
        this.texture = texture;
        this.direction = 0;
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



    public boolean isAlive(){ // verifie si l'ennemi est vivant
        if (this.getLife() <= 0)
            return false;
        return true;
    }

    public void update(float dt, Game game) { // Fait bouger l ennemi
        if(this.getX() > 570 && getY() == 888){
            direction = 270;
        }
        else if(this.getX() > 570 && getX() < 700 && getY() < 240){
            direction = 0;
        }
        else if(this.getX() > 700 && this.getY() < 240 && getY() > 140 && getX() < 960){
            direction = 270;
        }
        else if(this.getX() > 700 && getY() < 140 && this.getX() < 960){
            direction = 0;
        }
        else if(this.getX() > 960 && this.getY() < 240 && this.getX() < 1080){
            direction = 90;
        }
        else if(this.getX() > 960 && this.getY() > 240 && this.getX() < 1080){
            direction = 0;
        }
        else if(this.getX() > 1100 && this.getY() < 888){
            direction = 90;
        }
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

    public void setDirection(float direction){
        this.direction = direction;
    }

    public float getDirection(){
        return direction;
    }
}