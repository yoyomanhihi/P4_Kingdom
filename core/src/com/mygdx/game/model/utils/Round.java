package com.mygdx.game.model.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.model.entity.Ennemy;
import com.mygdx.game.model.entity.Player;
import com.mygdx.game.model.entity.Tower;

import java.util.ArrayList;
import java.util.LinkedList;

public class Round {
    private LinkedList <Ennemy> ennemies; //le tableau d ennemis
    private LinkedList <Ennemy> ennemiestransition;
    private int roundnbr;
    private ArrayList<Direction> directionsEnemy;
    private final float startX;
    private final float startY;
    private final Rectangle endRect;

    public Round(ArrayList<Direction> directionsEnemy, float startX , float startY, Rectangle endRect){
        roundnbr = 0;
        this.directionsEnemy = directionsEnemy;
        ennemies = new LinkedList<Ennemy>();
        ennemiestransition = new LinkedList<Ennemy>();
        this.startX = startX;
        this.startY = startY;
        this.endRect = endRect;
    } //Put the good size for the first wave

    public void round1(int temps, Stage stage, World world, int ennemynbr){ //met les ennemis dans le tableau
        ennemies.add(new Ennemy(10, 100, 15, new Texture("Tank.png"), stage, world,directionsEnemy,startX,startY));
        ennemies.get(ennemynbr).defineEnnemy();
    }

    public void round2(int temps, Stage stage, World world, int ennemynbr){ //met les ennemis dans le tableau
        if(ennemiestransition.size() != 0){
            ennemiestransition.getFirst().setLife(350);
            ennemiestransition.getFirst().setPosition(startX, startY);
            ennemiestransition.getFirst().setDirection(0);
            ennemiestransition.getFirst().setDirections(directionsEnemy);
            ennemiestransition.getFirst().initRectangleList();
            ennemies.add(ennemiestransition.getFirst());
            ennemiestransition.removeFirst();
        }
        else {
            ennemies.add(new Ennemy(350, 100, 15, new Texture("Tank.png"), stage, world, directionsEnemy, startX, startY));
            ennemies.get(ennemynbr).defineEnnemy();
        }
    }

    public void round3(int temps, Stage stage, World world, int ennemynbr){ //met les ennemis dans le tableau
        ennemies.add(new Ennemy(3000, 60, 100, new Texture("RedTank.png"), stage, world,directionsEnemy,startX,startY));
        ennemies.get(ennemynbr).defineEnnemy();
    }

    public void update(float dt, Game game, Player player){ //update la position et l etat des ennemis
        if(ennemies.size()==0){
            roundnbr++;
            for(Tower tower : player.getWeapons()) {
                tower.setEnnemyinrange(0);
            }
        }
        else {
            for (int i = 0; i < ennemies.size(); i++) {
                ennemies.get(i).update(dt,this.endRect);
                if(ennemies.get(i).getAttackPlayer()){
                    player.loseLife(ennemies.get(i).getDamage());
                    ennemies.remove(i);
                }
                if (0 < ennemies.size() && !ennemies.get(i).isAlive()) {
                    player.setMoney(player.getMoney() + ennemies.get(i).getPoint());
                    ennemiestransition.add(ennemies.get(i));
                    ennemies.remove(i);
                    for(Tower tower : player.getWeapons()) {
                        tower.setEnnemyinrange(0);
                    }
                }
            }
        }
    }

    public void draw(Batch batch){ // draw les ennemis du tableau
        for(int i = 0; i < ennemies.size(); i++){
            batch.draw(ennemies.get(i).getTexture(), ennemies.get(i).getX(), ennemies.get(i).getY());
        }
    }


    public void shoot(Tower tour, SpriteBatch batch, float delta, World world, Game game, Stage uiStage){ // Prend une arme et lui fait tirer sur le bon ennemi
        if(ennemies.size() != 0) {
            if (ennemies.get(tour.getEnnemyinrange()).isInRange(tour)) {
                tour.shoot(ennemies.get(tour.getEnnemyinrange()), batch, delta, world, game, uiStage);
            } else {
                for (int i = 0; i < ennemies.size(); i++) {
                    if (ennemies.get(i) != null) {
                        if (ennemies.get(i).isInRange(tour)) {
                            tour.setEnnemyinrange(i);
                            tour.shoot(ennemies.get(i), batch, delta, world, game, uiStage);
                            i = 1000;
                        }
                    }
                }
            }
        }
    }

    public void updateLaser(float delta, SpriteBatch batch, Game game, Stage uiStage, Tower tour){ // Fait bouger le laser
        if(ennemies.size()!=0) {
            if (ennemies.get(tour.getEnnemyinrange()).isInRange(tour)) {
                tour.updateLaser(delta, batch, ennemies.get(tour.getEnnemyinrange()), game, uiStage);
            }
            else {
                for (int i = 0; i < ennemies.size(); i++) {
                    tour.updateLaser(delta, batch, ennemies.get(i), game, uiStage);
                    i = 1000;
                }
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