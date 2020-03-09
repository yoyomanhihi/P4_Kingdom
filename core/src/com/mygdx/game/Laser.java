package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.BaseActor;

public class Laser extends BaseActor
{

    private float stateTime;
    private int speed;
    private Texture texture;
    private float direction;

    public Laser(float x, float y, Stage s)
    {
        super(x,y,s);
        this.texture = new Texture("Bullet.png");
        addAction( Actions.delay(1) );
        addAction( Actions.after( Actions.fadeOut(0.5f) ) );
        addAction( Actions.after( Actions.removeActor() ) );
        setSpeed(60);
        setDeceleration(0);
        this.speed = 200;
    }

    public void update(float dt, SpriteBatch batch) {
        stateTime += dt;
        setPosition(getX() + dt * speed, getY());
        batch.draw(texture, this.getX(), this.getY());
    }


    public void act(float dt){
        super.act(dt);
        boundToWorld();
    }

    public Texture getTexture(){
        return texture;
    }
}