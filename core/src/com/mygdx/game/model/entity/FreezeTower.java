package com.mygdx.game.model.entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class FreezeTower extends Tower{

    private float slower;

    public FreezeTower(int ID, String nom, int degats, float portee, int cadence, int prix, float x, float y, Texture
            base_texture, Texture gun_texture, Texture texture2, float lasersize, Stage s, World world, float slower) {
        super(ID ,nom, degats, portee, cadence, prix, x, y, base_texture, base_texture, gun_texture, texture2, lasersize, s, world);
        this.slower = slower;
    }

    public float getSlower(){
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
            this.setTemps(this.getTemps()+1);
        }
    }
}
