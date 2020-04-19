package com.mygdx.game.model.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MoneyTower extends Tower {

    private float moneyboost;

    public MoneyTower(int ID, String nom, int degats, float portee, int cadence, int prix, float x, float y, Texture
            base_texture, Texture gun_texture, Texture texture2, float lasersize, Stage s, World world, float moneyboost, Player player) {

        super(ID ,nom, degats, portee, cadence, prix, x, y, base_texture, base_texture, gun_texture, texture2, lasersize, s, world);
        player.setMoneyboost(player.getMoneyboost()+1);
    }

    public float getMoneyboost(){
        return moneyboost;
    }


}
