package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TexturedActor extends Actor
{
    private Texture image;
    // constructor
    public TexturedActor()
    { super(); }
    public void setTexture(Texture t)
    { image = t; }
    public Texture getTexture()
    { return image; }
    public void draw(Batch b)
    {
        b.draw( getTexture(), getX(), getY() );
    }
}
