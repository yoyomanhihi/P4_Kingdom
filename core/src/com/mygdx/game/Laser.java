package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.BaseActor;

public class Laser extends BaseActor
{

    private float stateTime;
    private int speed;
    private Texture texture;

    public Laser(float x, float y, Stage s)
    {
        super(x,y,s);
        this.texture = new Texture("Bullet.png");
        addAction( Actions.delay(1) );
        addAction( Actions.after( Actions.fadeOut(0.5f) ) );
        addAction( Actions.after( Actions.removeActor() ) );
        setSpeed(400);
        setMaxSpeed(400);
        setDeceleration(0);
        this.speed = 1000;
    }

    public void update(float dt) {
        stateTime += dt;
        setPosition(getX() + dt * speed, getY());
    }

    public void act(float dt)
    {
        super.act(dt);
    }

    public Texture getTexture(){
        return texture;
    }
}