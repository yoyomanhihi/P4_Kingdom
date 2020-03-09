package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.BaseActor;
import com.mygdx.game.Screens.WinScreen;

public class Laser extends BaseActor
{

    private float stateTime;
    private int speed;
    private Texture texture;
    private float direction;
    private Body b2body;
    public World world;


    public Laser(float x, float y, Stage s, World world)
    {
        super(x,y,s);
        this.texture = new Texture("Bullet.png");
        addAction( Actions.delay(1) );
        addAction( Actions.after( Actions.fadeOut(0.5f) ) );
        addAction( Actions.after( Actions.removeActor() ) );
        setSpeed(2000);
        setDeceleration(0);
        this.world = world;
        this.speed = 2000;
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

    public void update(float dt, SpriteBatch batch, Ennemy Ennemy, Game game) {
        stateTime += dt;
        /*
        if (this.getX() > Ennemy.getX() && this.getY() > Ennemy.getY()) { //L'ennemi est en haut à gauche de la tour
            setPosition(getX()-dt*speed*Math.cos(distance), getY()-dt*speed*Math.sin(distance));
        }
        else if (this.getX() > Ennemy.getX() && this.getY() == Ennemy.getY()){ //L'ennemi est pile à gauche de la tour
            setPosition(getX()-dt*speed*Math.cos(distance), getY());
        }
        else if (this.getX() > Ennemy.getX() && this.getY() < Ennemy.getY()){ //L'ennemi est en bas à gauche de la tour
            setPosition(getX()-dt*speed*Math.cos(distance), getY()+dt*speed*Math.sin(distance));
        }
        else if (this.getX() == Ennemy.getX() && this.getY() < Ennemy.getY()){ //L'ennemmi est pile en bas de la tour
            setPosition(getX(), getY()+dt*speed*Math.sin(distance));
        }

        else if (this.getX() < Ennemy.getX() && this.getY() < Ennemy.getY()){ //L'ennemi est en bas à droite de la tour
            setPosition(getX()+dt*speed*Math.cos(distance), getY()+dt*speed*Math.sin(distance));
        }
        else if (this.getX() < Ennemy.getX() && this.getY() == Ennemy.getY()){ //L'ennemi pile à droite de la tour
            setPosition(getX()+dt*speed*Math.cos(distance), getY());
        }
        else if (this.getX() < Ennemy.getX() && this.getY() > Ennemy.getY()){ //L'ennemi est en haut à droite de la tour
            setPosition(getX()+dt*speed*Math.cos(distance), getY()-dt*speed*Math.sin(distance));
        }
        else if (this.getX() == Ennemy.getX() && this.getY() > Ennemy.getY()){ //L'ennemi est en pile en haut de la tour
            setPosition(getX(), getY()-dt*speed*Math.sin(distance));
        }
        */

        setPosition(getX() + dt * speed, getY());
        batch.draw(texture, this.getX(), this.getY());
        if(this.overlaps(Ennemy)){
            game.setScreen(new WinScreen(game));
        }
    }

    public void Orientation (Ennemy Ennemy){

    }


    public void act(float dt)
    {
        super.act(dt);
    }

    public Texture getTexture(){
        return texture;
    }
}