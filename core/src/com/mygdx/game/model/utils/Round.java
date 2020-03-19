package com.mygdx.game.model.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.model.entity.Ennemy;
import com.mygdx.game.model.entity.Tower;
import com.mygdx.game.view.screen.PlayScreen;

public class Round {
    private Ennemy [] ennemies; //le tableau d ennemis
    private int ennemiesleft; // le nombre d ennemis en vie
    private int roundnbr;

    public Round(){
        ennemies = new Ennemy[3];
        ennemiesleft = 3;
        roundnbr = 0;
    } //Put the good size for the first wave

    public void preRound2(){
        ennemies = new Ennemy[5];
        ennemiesleft = 5;
    }

    public void round1(int temps, Stage stage, World world, int ennemynbr){ //met les ennemis dans le tableau
        ennemies[ennemynbr] = new Ennemy(20, 80, 20, new Texture("Tank.png"), stage, world);
        ennemies[ennemynbr].defineEnnemy();
    }

    public void round2(int temps, Stage stage, World world, int ennemynbr){ //met les ennemis dans le tableau
        ennemies[ennemynbr] = new Ennemy(20, 80, 20, new Texture("Tank.png"), stage, world);
        ennemies[ennemynbr].defineEnnemy();
    }

    public void update(float dt, Game game){ //update la position et l etat des ennemis
        if(ennemiesleft == 0){
            roundnbr++;
        }
        for(int i = 0; i < ennemies.length; i++){
            if(ennemies[i] != null) {
                ennemies[i].update(dt, game);
                if(!ennemies[i].isAlive()){
                    ennemies[i] = null;
                    ennemiesleft--;
                }
            }
        }
    }

    public void draw(Batch batch){ // draw les ennemis du tableau
        for(int i = 0; i < ennemies.length; i++){
            if(ennemies[i] != null) {
                batch.draw(ennemies[i].getTexture(), ennemies[i].getX(), ennemies[i].getY());
            }
        }
    }


    public void shoot(Tower tour, SpriteBatch batch, float delta, World world, Game game, Stage uiStage){ // Prend une arme et lui fait tirer sur le bon ennemi
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

    public void updateLaser(float delta, SpriteBatch batch, Game game, Stage uiStage, Tower tour){ // Fait bouger le laser
        for(int i = 0; i < ennemies.length; i++){
            if(ennemies[i] != null) {
                tour.updateLaser(delta, batch, ennemies[i], game, uiStage);
            }
        }
    }

    public void setRoundnbr(int n){
        roundnbr = n;
    }

    public int getRoundnbr(){
        return roundnbr;
    }


}