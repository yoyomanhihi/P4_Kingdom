package com.mygdx.game;
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
        setSpeed(60);
        setDeceleration(0);
        this.world = world;
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

    public void update(float dt, SpriteBatch batch, Ennemy ennemy) {
        stateTime += dt;
        setPosition(getX() + dt * speed, getY());
        batch.draw(texture, this.getX(), this.getY());
        if(this.overlaps(ennemy)){
            ennemy.setPosition(1000, 1000);
        }
    }


    public void act(float dt)
    {
        super.act(dt);
    }

    public Texture getTexture(){
        return texture;
    }
}