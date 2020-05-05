package com.mygdx.game.model.utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.model.entity.Tower;

/**
 * Extend functionality of the LibGDX Actor class.
 */
public class BaseActor extends Animations
{

    private Vector2 velocityVec;
    private float maxSpeed;
    private float deceleration;
    private TextureRegion textureRegion;
    private Rectangle rectangle;
    private static Rectangle worldBounds;
    private Polygon boundaryPolygon;
    private float conversion = 57.2958f;

    public BaseActor(){

    }

    public BaseActor(float x, float y, Stage s)
    {
        // call constructor from Actor class
        super();
        // perform additional initialization tasks
        setPosition(x,y);
        s.addActor(this);
        velocityVec = new Vector2(0,0);
        maxSpeed = 1000;
        deceleration = 0;
        textureRegion = new TextureRegion();
        rectangle = new Rectangle();
    }

    public void setSpeed(float speed)
    {
        // if length is zero, then assume motion angle is zero degrees
        if (velocityVec.len() == 0)
            velocityVec.set(speed, 0);
        else
            velocityVec.setLength(speed);
    }

    public float getSpeed()
    {
        return velocityVec.len();
    }
    public void setMotionAngle(float angle)
    {
        velocityVec.setAngle(angle);
    }
    public float getMotionAngle()
    {
        return velocityVec.angle();
    }

    public void setMaxSpeed(float ms)
    {
        maxSpeed = ms;
    }

    public void setDeceleration(float dec)
    {
        deceleration = dec;
    }

    public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames,float frameDuration, boolean loop)
    {
        int fileCount = fileNames.length;
        com.badlogic.gdx.utils.Array<TextureRegion> textureArray = new Array<TextureRegion>();
        for (int n = 0; n < fileCount; n++) {
            String fileName = fileNames[n];
            Texture texture = new Texture(Gdx.files.internal(fileName));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            textureArray.add(new TextureRegion(texture));
        }
        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);
        if (loop)
            anim.setPlayMode(Animation.PlayMode.LOOP);
        else
            anim.setPlayMode(Animation.PlayMode.NORMAL);
        if (animation == null) {
            setAnimation(anim);
            if (boundaryPolygon == null)
                setBoundaryRectangle();
        }
        return anim;
    }

    public Animation<TextureRegion> loadTexture(String fileName)
    {
        String[] fileNames = new String[1];
        fileNames[0] = fileName;
        return loadAnimationFromFiles(fileNames, 1, true);
    }

    public float getDistance(Tower Tower){
        return (float)Math.sqrt(Math.pow((this.getX() - Tower.getX())/1.25 ,2) + Math.pow((this.getY() - Tower.getY()),2));
    }

    public boolean isInRange(Tower Tower){
        float distance = this.getDistance(Tower);
        if (Tower.getRange() >= distance)
            return true;
        return false;
    }

    public float getOrientation(BaseActor BA){
        float diff_x = this.getX()-BA.getX();
        float diff_y = this.getY()-BA.getY();
        float degre = (float)Math.atan(diff_y/diff_x)*conversion;

        if (this.getX() < BA.getX() && this.getY() > BA.getY()) { //L'ennemi est en bas à droite de la tour
            degre -= 0;
        }
        else if (this.getX() < BA.getX() && this.getY() <= BA.getY()){ //L'ennemi est en haut à droite de la tour
            degre -= 0;
        }
        else if (this.getX() > BA.getX() && this.getY() < BA.getY()){ //L'ennemi est en haut à gauche
            degre +=180;
        }
        else{
            degre +=180;
        }
        return degre;
    }


    public void centerAtPosition(float x, float y)
    {
        setPosition( x - getWidth()/2 , y - getHeight()/2 );
    }

    public void centerAtActor(BaseActor other)
    {
        centerAtPosition( other.getX() + other.getWidth()/2 , other.getY() + other.getHeight()/2 );
    }

    public void setOpacity(float opacity)
    {
        this.getColor().a = opacity;
    }

    public void setTexture(Texture t)
    {
        textureRegion.setRegion(t);
        setSize( t.getWidth(), t.getHeight() );
        rectangle.setSize( t.getWidth(), t.getHeight() );
    }
    public Rectangle getRectangle()
    {
        rectangle.setPosition( getX(), getY() );
        return rectangle;
    }
    public boolean overlaps(ActorBeta other)
    {
        return this.getRectangle().overlaps( other.getRectangle() );
    }
    public void act(float dt)
    {
        super.act(dt);
    }
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw( batch, parentAlpha );
        Color c = getColor(); // used to apply tint color effect
        batch.setColor(c.r, c.g, c.b, c.a);
        if ( isVisible() )
            batch.draw( textureRegion,
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
    }

    public static void setWorldBounds(float width, float height)
    {
        worldBounds = new Rectangle( 0,0, width, height );
    }

    public static void setWorldBounds(BaseActor ba)
    {
        setWorldBounds( ba.getWidth(), ba.getHeight() );
    }

    public void boundToWorld()
    {
        // check left edge
        if (getX() < 0)
            setX(0);
        // check right edge
        if (getX() + getWidth() > worldBounds.width)
            setX(worldBounds.width - getWidth());
        // check bottom edge
        if (getY() < 0)
            setY(0);
        // check top edge
        if (getY() + getHeight() > worldBounds.height)
            setY(worldBounds.height - getHeight());
    }

    public void setBoundaryRectangle()
    {
        float w = getWidth()+35;
        float h = getHeight()+35;
        float[] vertices = {0,0, w,0, w,h, 0,h};
        boundaryPolygon = new Polygon(vertices);
    }

    public void setBoundaryPolygon(int numSides)
    {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = new float[2*numSides];
        for (int i = 0; i < numSides; i++)
        {
            float angle = i * 6.28f / numSides;
            // x-coordinate
            vertices[2*i] = w/2 * MathUtils.cos(angle) + w/2;
            // y-coordinate
            vertices[2*i+1] = h/2 * MathUtils.sin(angle) + h/2;
        }
        boundaryPolygon = new Polygon(vertices);
    }

    public Polygon getBoundaryPolygon()
    {
        boundaryPolygon.setPosition( getX(), getY() );
        boundaryPolygon.setOrigin( getOriginX(), getOriginY() );
        boundaryPolygon.setRotation ( getRotation() );
        boundaryPolygon.setScale( getScaleX(), getScaleY() );
        return boundaryPolygon;
    }


    public boolean overlaps(BaseActor other)
    {
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();
        // initial test to improve performance
        if ( !poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()) )
            return false;
        return Intersector.overlapConvexPolygons( poly1, poly2 );
    }

}