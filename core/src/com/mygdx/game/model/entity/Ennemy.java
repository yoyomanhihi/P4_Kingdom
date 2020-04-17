package com.mygdx.game.model.entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.model.utils.BaseActor;
import com.mygdx.game.model.utils.Direction;

import java.util.ArrayList;

public class Ennemy extends BaseActor {

    private float life;
    private int speed;
    private int point; // l argent qu il rapporte s il meurt
    //private Texture texture;
    private Body b2body;
    private float direction;
    private float old_direction = 0;
    private World world;
    private ArrayList<Direction> directions;
    private int target = 0;
    private ArrayList<Rectangle> directionsRectangle = new ArrayList<>();
    private boolean attackPlayer = false;
    private int damage;
    private float initiallife;
    private Sprite sprite;
    private int timefreezed;
    private int normalspeed;
    private boolean isfreezed;
    private boolean isfast;
    private boolean doreload;
    private int normalpoint;

    //public int damage;  Si on veut faire en sorte qu'un ennemy puisse attaquer une tour

    public Ennemy(int life, int speed, int point, Texture texture, Stage s, World world, ArrayList<Direction> directions, float startX, float startY, int damage){
        super(startX,startY,s);
        this.life = life;
        this.initiallife = life;
        this.point = point;
        this.normalpoint = point;
        this.sprite = new Sprite(texture);
        this.direction = 0;
        this.isfreezed = false;
        this.isfast = false;
        this.doreload = false;
        this.speed = speed;
        this.normalspeed = speed;
        this.directions = directions;
        this.damage = damage;
        this.world = world;
        initRectangleList();
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

    public void initRectangleList(){
        for (Direction dir: directions) {
            directionsRectangle.add(new Rectangle(dir.getPoint().getX(),dir.getPoint().getY(),10,10));
        }
    }

    public void setTarget(int newTarget){
        this.target = newTarget;

    }

    public boolean isAlive(){ // verifie si l'ennemi est vivant
        if (this.getLife() <= 0)
            return false;
        return true;
    }

    public boolean getAttackPlayer(){
        return this.attackPlayer;

    }

    public void setAttackPlayer(boolean attackPlayer){
        this.attackPlayer = false;
    }

    public void update(float dt,Rectangle endRect) { // Fait bouger l ennemi

        if(endRect.contains(this.getX(), this.getY())){
            this.attackPlayer = true;
        }
        else {
            if(doreload && life < initiallife){
                life = life+(initiallife/1000);
            }
            if(isfreezed){
                timefreezed++;
                if(timefreezed > 40){
                    if(sprite.getColor().equals(Color.RED)){
                        this.speed = 2*normalspeed;
                    }
                    else {
                        speed = normalspeed;
                    }
                    isfreezed = false;
                    //sprite.setColor(Color.WHITE);
                }
            }

            if (target < directionsRectangle.size() && directionsRectangle.get(target).contains(this.getX(), this.getY())) {
                direction = directions.get(target).getRotation();
                target++;
            }


            if (direction == 0) {
                this.sprite.rotate(direction-old_direction);
                setPosition(getX() + dt * speed, getY());
            }
            if (direction == 90) {
                this.sprite.rotate(direction-old_direction);
                setPosition(getX(), getY() + dt * speed);
            }
            if (direction == 180) {
                this.sprite.rotate(direction-old_direction);
                setPosition(getX() - dt * speed, getY());
            }
            if (direction == 270) {
                this.sprite.rotate(direction-old_direction);
                setPosition(getX(), getY() - dt * speed);
            }
            old_direction = direction;
        }
    }

    public float getPercentageOfLife(){
        return life/initiallife;
    }


    public void act(float dt){
        super.act( dt );
        boundToWorld();
    }


    public float getLife(){
        return life;
    }

    public int getPoint(){
        return point;
    }

    public void setLife(float life){
        this.life = life;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    /*
    public Texture getTexture(){
        return texture;
    }

     */

    public void setDirection(float direction){
        this.direction = direction;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public float getDirection(){
        return direction;
    }

    public int getDamage(){
        return this.damage;
    }

    public void setDirections(ArrayList<Direction> directions){
        this.directions = directions;
    }

    public void beFreezed(int divider){
        isfreezed = true;
        if(sprite.getColor().equals(Color.RED)){
            this.speed = normalspeed;
        }
        else {
            this.speed = normalspeed / divider;
        }
        //sprite.setColor(Color.BLUE);
        timefreezed = 0;
    }

    public boolean isIsfreezed(){
        return isfreezed;
    }

    public float getInitiallife(){
        return initiallife;
    }

    public void beFast(){
        this.speed = normalspeed*2;
        sprite.setColor(Color.RED);
    }

    public int getNormalspeed(){
        return normalspeed;
    }

    public boolean isDoreload(){
        return doreload;
    }

    public void setDoreload(boolean doreload){
        this.doreload = doreload;
    }

    public void beReload(){
        doreload = true;
        sprite.setColor(Color.GREEN);
    }

    public void setPoint(int point){
        this.point = point;
    }

    public int getNormalpoint(){
        return normalpoint;
    }

}