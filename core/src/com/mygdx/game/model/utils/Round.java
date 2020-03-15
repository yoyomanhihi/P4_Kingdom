package com.mygdx.game.model.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.model.entity.Ennemy;
import com.mygdx.game.model.entity.Tower;

public class Round {
    private Ennemy [] ennemies;

    public Round(){
        ennemies = new Ennemy[5];
    }

    public void round1(int temps, Stage stage, World world, int ennemynbr){
        ennemies[ennemynbr] = new Ennemy(50, 125, 20, new Texture("Tank.png"), stage, world);
        ennemies[ennemynbr].defineEnnemy();
    }

    public void update(float dt, Game game){
        for(int i = 0; i < ennemies.length; i++){
            if(ennemies[i] != null) {
                ennemies[i].update(dt, game);
                if(!ennemies[i].isAlive()){
                    ennemies[i] = null;
                }
            }
        }
    }

    public void draw(Batch batch){
        for(int i = 0; i < ennemies.length; i++){
            if(ennemies[i] != null) {
                batch.draw(ennemies[i].getTexture(), ennemies[i].getX(), ennemies[i].getY());
            }
        }
    }


    public void shoot(Tower tour, SpriteBatch batch, float delta, World world, Game game, Stage uiStage){
        if(ennemies[tour.getEnnemyinrange()] != null){
            if(ennemies[tour.getEnnemyinrange()].isInRange(tour)){
                tour.shoot(ennemies[tour.getEnnemyinrange()], batch, delta, world, game, uiStage);
            }
        }
        else {
            for (int i = tour.getEnnemyinrange()+1; i < ennemies.length; i++) {
                if (ennemies[i] != null) {
                    if (ennemies[i].isInRange(tour)) {
                        tour.shoot(ennemies[i], batch, delta, world, game, uiStage);
                        tour.setEnnemyinrange(i);
                        i = 1000;
                    }
                }
            }
        }
    }

    public void updateLaser(float delta, SpriteBatch batch, Game game, Stage uiStage, Tower tour){
        for(int i = 0; i < ennemies.length; i++){
            if(ennemies[i] != null) {
                tour.updateLaser(delta, batch, ennemies[i], game, uiStage);
            }
        }
    }





}