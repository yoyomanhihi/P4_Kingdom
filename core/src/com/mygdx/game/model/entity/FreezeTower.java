package com.mygdx.game.model.entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class FreezeTower extends Tower{

    private int slower;

    public FreezeTower(String nom, int degats, int portee, int cadence, int prix, float x, float y, Texture
            texture, Texture texture2, float lasersize, Stage s, World world, int slower) {
        super(nom, degats, portee, cadence, prix, x, y, texture, texture2, lasersize, s, world);
        this.slower = slower;
    }

    public int getSlower(){
        return this.slower;
    }

    public void updateLaser(float dt, SpriteBatch batch, Ennemy Ennemy, Game game, Stage stage){ // fait que le laser suit l ennemi
        if(laser != null) {
            if (laser.getX() != this.getX() && laser.getY() != this.getY()) {
                laser.update(dt, batch, Ennemy, game, stage);
            }
            if (laser.overlaps(Ennemy)) { //verifie si le laser touche l ennemi
                Ennemy.beFreezed(2);
                laser.setPosition(500000, 500000);
            }
            temps++;
        }
    }
}
