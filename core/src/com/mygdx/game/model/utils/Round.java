package com.mygdx.game.model.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    private int deadennemies;
    private Texture healthbar;
    private float temps;

    public Round(ArrayList<Direction> directionsEnemy, float startX , float startY, Rectangle endRect){
        roundnbr = 0;
        this.directionsEnemy = directionsEnemy;
        ennemies = new LinkedList<Ennemy>();
        ennemiestransition = new LinkedList<Ennemy>();
        this.startX = startX;
        this.startY = startY;
        this.endRect = endRect;
        this.deadennemies = 0;
        healthbar = new Texture("Healthbar.png");
        temps = 0;
    } //Put the good size for the first wave

    public void round1(int temps, Stage stage, World world, int ennemynbr){ //met les ennemis dans le tableau
        ennemies.add(new Ennemy(500, 100, 15, new Texture("Tank.png"), stage, world,directionsEnemy,startX,startY, 1));
        ennemies.get(ennemynbr-deadennemies).defineEnnemy();
    }

    public void round2(int temps, Stage stage, World world, int ennemynbr){ //met les ennemis dans le tableau
        if(ennemynbr < 3){
            ennemiestransition.getFirst().setLife(500);
            ennemiestransition.getFirst().setPosition(startX, startY);
            ennemiestransition.getFirst().setDirection(0);
            //ennemiestransition.getFirst().setDirections(directionsEnemy);
            //ennemiestransition.getFirst().initRectangleList();
            ennemiestransition.getFirst().setTarget(0);
            ennemiestransition.getFirst().setAttackPlayer(false);
            ennemies.add(ennemiestransition.getFirst());
            ennemiestransition.removeFirst();
        }
        else {
            ennemies.add(new Ennemy(500, 100, 15, new Texture("Tank.png"), stage, world, directionsEnemy, startX, startY, 1));
            ennemies.get(ennemynbr-deadennemies+3).defineEnnemy();
        }
    }

    public void round3(int temps, Stage stage, World world, int ennemynbr){ //met les ennemis dans le tableau
        ennemies.add(new Ennemy(3000, 60, 100, new Texture("RedTank.png"), stage, world,directionsEnemy,startX,startY, 2));
        ennemies.get(ennemynbr).defineEnnemy();
    }

    public void update(float dt, Game game, Player player, Stage stage){ //update la position et l etat des ennemis
        if(ennemies.size()==0){
            temps++;

            if(temps > 10){
                roundnbr++;
                temps = 0;
            }
        }
        else {
            for (int i = 0; i < ennemies.size(); i++) {
                ennemies.get(i).update(dt,this.endRect);
                if(ennemies.get(i).getAttackPlayer()){
                    player.loseLife(ennemies.get(i).getDamage());
                    ennemiestransition.addLast(ennemies.get(i));
                    deadennemies++;
                    ennemies.remove(i);
                }
                if (0 < ennemies.size() && !ennemies.get(i).isAlive()) {
                    player.setMoney(player.getMoney() + ennemies.get(i).getPoint());
                    ennemiestransition.addLast(ennemies.get(i));
                    deadennemies++;
                    ennemies.remove(i);
                }
            }
        }
    }

    public void draw(Batch batch){ // draw les ennemis du tableau
        for(int i = 0; i < ennemies.size(); i++){
            batch.draw(ennemies.get(i).getSprite(), ennemies.get(i).getX(), ennemies.get(i).getY());
            if(ennemies.get(i).getPercentageOfLife() > 0.25){
                batch.setColor(Color.GREEN);
            }
            else{
                batch.setColor(Color.RED);
            }
            batch.draw(healthbar, ennemies.get(i).getX(), ennemies.get(i).getY() + 60, 60*ennemies.get(i).getPercentageOfLife(), 5);
            batch.setColor(Color.WHITE);
        }
    }


    public void shoot(Tower tour, SpriteBatch batch, float delta, World world, Game game, Stage uiStage){ // Prend une arme et lui fait tirer sur le bon ennemi
        if(ennemies.size() != 0) {
            for (int i = 0; i < ennemies.size(); i++) {
                if (ennemies.get(i) != null) {
                    if (ennemies.get(i).isInRange(tour)) {
                        if(tour.getTemps() > tour.getFireRate()) {
                            tour.shoot(ennemies.get(i), batch, delta, world, game, uiStage);
                            tour.setTemps(0);
                            i = 1000;
                        }
                    }
                }
            }
        }
    }

    public void updateLaser(float delta, SpriteBatch batch, Game game, Stage uiStage, Tower tour){ // Fait bouger le laser
        if(ennemies.size()!=0) {
            for (int i = 0; i < ennemies.size(); i++) {
                if(ennemies.get(i).isInRange(tour)) {
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

    public float getTemps(){
        return temps;
    }

    public void setTemps(float temps){
        this.temps = temps;
    }

}