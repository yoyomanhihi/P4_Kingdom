package com.mygdx.game.model.entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.model.utils.BaseActor;

public class Laser extends BaseActor
{

    private float stateTime;
    private int speed;
    private Texture texture;
    private float direction;
    private Body b2body;
    public World world;
    private float angle;


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

    public void update(float dt, SpriteBatch batch, Ennemy Ennemy, Game game, Stage stage) {
        stateTime += dt;
        angle = this.getOrientation(Ennemy);
        move(angle, dt);
        batch.draw(texture, this.getX(), this.getY());
        if (this.overlaps(Ennemy)) {
            Ennemy.setLife(Ennemy.getLife() - 30);
            this.setPosition(100000, 0);
        }
    }

    public void move(float angle, float dt){
        float a = (float) this.getX() ;
        float b = (float) Math.cos(angle);
        float c = (float) this.getY();
        float d = (float) Math.sin(angle);
        this.setPosition(a - b * speed *dt ,c - d * speed * dt);
    }


    public void act(float dt)
    {
        super.act(dt);
    }

    public Texture getTexture(){
        return texture;
    }
}