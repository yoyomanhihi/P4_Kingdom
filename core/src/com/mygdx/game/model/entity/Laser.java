package com.mygdx.game.model.entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.model.utils.BaseActor;
import com.badlogic.gdx.graphics.Color;

import javax.swing.plaf.ColorUIResource;

public class Laser extends BaseActor
{

    private int speed;
    private Sprite sprite;
    private Body b2body;
    public World world;
    private float angle;
    private Sound sound;
    private float conversion2 = 0.0174533f;


    public Laser(float x, float y, float lasersize, Texture texture, int speed, Sound sound, Stage s, World world)
    {
        super(x,y,s);
        this.sprite = new Sprite(texture);
        sprite.setScale(lasersize);
        addAction( Actions.delay(1) );
        addAction( Actions.after( Actions.fadeOut(0.5f) ) );
        addAction( Actions.after( Actions.removeActor() ) );
        setDeceleration(0);
        this.world = world;
        this.speed = speed;
        this.sound = sound;
    }

    public void defineLaser(){
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

    public void update(float dt, SpriteBatch batch, Ennemy Ennemy, Game game, Stage stage) { // met a jour le laser
        if(this.getX() < 4500)
        angle = this.getOrientation(Ennemy); //calcule ou le laser doit aller
        move(angle, dt); // fait bouger le laser
        sprite.setPosition(this.getX(), this.getY());
        sprite.draw(batch);
    }

    public void move(float angle, float dt){ //permet la gestion de deplacement du laser
        float a = (float) this.getX();
        float b = (float) Math.cos(angle*conversion2);
        float c = (float) this.getY();
        float d = (float) Math.sin(angle*conversion2);
        this.setPosition(a + b * speed *dt ,c + d * speed * dt);
    }

    public Sound getSound(){
        return sound;
    }


    public void act(float dt)
    {
        super.act(dt);
    }

    public Sprite getSprite(){
        return sprite;
    }

    public void yellow(){
        sprite.setColor(Color.YELLOW);
    }

    public void red(){
        sprite.setColor(Color.RED);
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

}