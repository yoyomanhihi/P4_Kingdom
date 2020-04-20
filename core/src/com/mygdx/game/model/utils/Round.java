package com.mygdx.game.model.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private LinkedList <Ennemy> ennemies1transition;
    private LinkedList <Ennemy> ennemies2transition;
    private LinkedList <Ennemy> ennemies3transition;
    private LinkedList <Ennemy> ennemies4transition;
    private LinkedList <Ennemy> ennemies5transition;
    private int roundnbr;
    private ArrayList<Direction> directionsEnemy;
    private final float startX;
    private final float startY;
    private final Rectangle endRect;
    private int deadennemies;
    private Texture healthbar;
    private float temps;
    int transition;

    public Round(ArrayList<Direction> directionsEnemy, float startX , float startY, Rectangle endRect){
        roundnbr = -1;
        this.directionsEnemy = directionsEnemy;
        ennemies = new LinkedList<Ennemy>();
        ennemies1transition = new LinkedList<Ennemy>();
        ennemies2transition = new LinkedList<Ennemy>();
        ennemies3transition = new LinkedList<Ennemy>();
        ennemies4transition = new LinkedList<Ennemy>();
        ennemies5transition = new LinkedList<Ennemy>();
        this.startX = startX;
        this.startY = startY;
        this.endRect = endRect;
        this.deadennemies = 0;
        healthbar = new Texture("Healthbar.png");
        temps = 0;
    } //Put the good size for the first wave

    public void round0(int temps, Stage stage, World world, int ennemynbr){ //Un ennemi 1
        //ennemies.add(new Ennemy(5000, 50, 120, new Texture("Tank4.png"), stage, world,directionsEnemy,startX,startY, 4));
        //ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        ennemies.add(new Ennemy(80, 120, 8, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 1));
        ennemies.get(ennemynbr-deadennemies).defineEnnemy();
    }

    public void round1(int temps, Stage stage, World world, int ennemynbr){ //Deux ennemis 1
        if(ennemynbr < 2){
            ennemies1transition.getFirst().setLife(80);
            ennemies1transition.getFirst().setPosition(startX, startY);
            ennemies1transition.getFirst().setDirection(0);
            ennemies1transition.getFirst().setTarget(0);
            ennemies1transition.getFirst().setAttackPlayer(false);
            ennemies1transition.getFirst().setSpeed(ennemies1transition.getFirst().getNormalspeed());
            ennemies1transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies1transition.getFirst().setDoreload(false);
            ennemies.add(ennemies1transition.getFirst());
            ennemies1transition.removeFirst();
        }
        else {
            ennemies.add(new Ennemy(80, 120, 8, new Texture("ennemies_sprite/red3.png"), stage, world, directionsEnemy, startX, startY, 1));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round2(int temps, Stage stage, World world, int ennemynbr){ //Trois ennemis 1
        if(ennemynbr < 5){
            ennemies1transition.getFirst().setLife(80);
            ennemies1transition.getFirst().setPosition(startX, startY);
            ennemies1transition.getFirst().setDirection(0);
            ennemies1transition.getFirst().setTarget(0);
            ennemies1transition.getFirst().setAttackPlayer(false);
            ennemies1transition.getFirst().setSpeed(ennemies1transition.getFirst().getNormalspeed());
            ennemies1transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies1transition.getFirst().setDoreload(false);
            ennemies.add(ennemies1transition.getFirst());
            ennemies1transition.removeFirst();
        }
        else {
            ennemies.add(new Ennemy(80, 120, 8, new Texture("ennemies_sprite/red3.png"), stage, world, directionsEnemy, startX, startY, 1));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round3(int temps, Stage stage, World world, int ennemynbr){ //Un ennemi 2
        ennemies.add(new Ennemy(300, 75, 30, new Texture("ennemies_sprite/red2.png"), stage, world,directionsEnemy,startX,startY, 1));
        ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        ennemies.get(ennemynbr - deadennemies).getSprite().setScale(2, 2.5f);
    }

    public void round4(int temps, Stage stage, World world, int ennemynbr) { // Cinq ennemis 1
        if(ennemynbr < 10){
            ennemies1transition.getFirst().setLife(80);
            ennemies1transition.getFirst().setPosition(startX, startY);
            ennemies1transition.getFirst().setDirection(0);
            ennemies1transition.getFirst().setTarget(0);
            ennemies1transition.getFirst().setAttackPlayer(false);
            ennemies1transition.getFirst().setSpeed(ennemies1transition.getFirst().getNormalspeed());
            ennemies1transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies1transition.getFirst().setDoreload(false);
            ennemies.add(ennemies1transition.getFirst());
            ennemies1transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(80, 120, 8, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 1));
            ennemies.get(ennemynbr-deadennemies).defineEnnemy();
        }
    }



    public void round5(int temps, Stage stage, World world, int ennemynbr) { //Trois ennemis 1 & Un ennemi 2
        if(ennemynbr < 15) {
            ennemies1transition.getFirst().setLife(80);
            ennemies1transition.getFirst().setPosition(startX, startY);
            ennemies1transition.getFirst().setDirection(0);
            ennemies1transition.getFirst().setTarget(0);
            ennemies1transition.getFirst().setAttackPlayer(false);
            ennemies1transition.getFirst().setSpeed(ennemies1transition.getFirst().getNormalspeed());
            ennemies1transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies1transition.getFirst().setDoreload(false);
            ennemies.add(ennemies1transition.getFirst());
            ennemies1transition.removeFirst();
        }
        else {
            ennemies2transition.getFirst().setLife(300);
            ennemies2transition.getFirst().setPosition(startX, startY);
            ennemies2transition.getFirst().setDirection(0);
            ennemies2transition.getFirst().setTarget(0);
            ennemies2transition.getFirst().setAttackPlayer(false);
            ennemies2transition.getFirst().setSpeed(ennemies2transition.getFirst().getNormalspeed());
            ennemies2transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies2transition.getFirst().setDoreload(false);
            ennemies.add(ennemies2transition.getFirst());
            ennemies2transition.removeFirst();
        }
    }

    public void round6(int temps, Stage stage, World world, int ennemynbr){ // Deux ennemis 2
        if(ennemynbr < 17) {
            ennemies2transition.getFirst().setLife(300);
            ennemies2transition.getFirst().setPosition(startX, startY);
            ennemies2transition.getFirst().setDirection(0);
            ennemies2transition.getFirst().setTarget(0);
            ennemies2transition.getFirst().setAttackPlayer(false);
            ennemies2transition.getFirst().setSpeed(ennemies2transition.getFirst().getNormalspeed());
            ennemies2transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies2transition.getFirst().setDoreload(false);
            ennemies.add(ennemies2transition.getFirst());
            ennemies2transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(300, 75, 30, new Texture("ennemies_sprite/red2.png"), stage, world,directionsEnemy,startX,startY, 1));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
            ennemies.get(ennemynbr - deadennemies).getSprite().setScale(2, 2.5f);
        }
    }

    public void round7(int temps, Stage stage, World world, int ennemynbr) { //Cinq ennemis 1 et Trois ennemis 2
        if(ennemynbr < 23){
            ennemies1transition.getFirst().setLife(80);
            ennemies1transition.getFirst().setPosition(startX, startY);
            ennemies1transition.getFirst().setDirection(0);
            ennemies1transition.getFirst().setTarget(0);
            ennemies1transition.getFirst().setAttackPlayer(false);
            ennemies1transition.getFirst().setSpeed(ennemies1transition.getFirst().getNormalspeed());
            ennemies1transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies1transition.getFirst().setDoreload(false);
            ennemies.add(ennemies1transition.getFirst());
            ennemies1transition.removeFirst();
        }
        else if(ennemynbr < 25){
            ennemies2transition.getFirst().setLife(300);
            ennemies2transition.getFirst().setPosition(startX, startY);
            ennemies2transition.getFirst().setDirection(0);
            ennemies2transition.getFirst().setTarget(0);
            ennemies2transition.getFirst().setAttackPlayer(false);
            ennemies2transition.getFirst().setSpeed(ennemies2transition.getFirst().getNormalspeed());
            ennemies2transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies2transition.getFirst().setDoreload(false);
            ennemies.add(ennemies2transition.getFirst());
            ennemies2transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(300, 75, 30, new Texture("ennemies_sprite/red2.png"), stage, world,directionsEnemy,startX,startY, 1));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
            ennemies.get(ennemynbr - deadennemies).getSprite().setScale(2, 2.5f);
        }
    }

    public void round8(int temps, Stage stage, World world, int ennemynbr) { //Dix ennemis 1 rapides
        if(ennemynbr < 31){
            ennemies1transition.getFirst().setLife(80);
            ennemies1transition.getFirst().setPosition(startX, startY);
            ennemies1transition.getFirst().setDirection(0);
            ennemies1transition.getFirst().setTarget(0);
            ennemies1transition.getFirst().setAttackPlayer(false);
            ennemies1transition.getFirst().beFast();
            ennemies1transition.getFirst().setPoint(10);
            ennemies1transition.getFirst().setDoreload(false);
            ennemies.add(ennemies1transition.getFirst());
            ennemies1transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(80, 120, 8, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 1));
            ennemies.get(ennemynbr-deadennemies).defineEnnemy();
            ennemies.get(ennemynbr-deadennemies).beFast();
            ennemies.get(ennemynbr-deadennemies).setPoint(10);
        }
    }

    public void round9(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 2 rapide
        ennemies2transition.getFirst().setLife(300);
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().beFast();
        ennemies2transition.getFirst().setPoint(35);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }

    public void round10(int temps, Stage stage, World world, int ennemynbr) { //Quatre ennemis 2
        if(ennemynbr < 39){
            ennemies2transition.getFirst().setLife(300);
            ennemies2transition.getFirst().setPosition(startX, startY);
            ennemies2transition.getFirst().setDirection(0);
            ennemies2transition.getFirst().setTarget(0);
            ennemies2transition.getFirst().setAttackPlayer(false);
            ennemies2transition.getFirst().setSpeed(ennemies2transition.getFirst().getNormalspeed());
            ennemies2transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies2transition.getFirst().setPoint(ennemies2transition.getFirst().getNormalpoint());
            ennemies2transition.getFirst().setDoreload(false);
            ennemies.add(ennemies2transition.getFirst());
            ennemies2transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(300, 75, 30, new Texture("ennemies_sprite/red2.png"), stage, world,directionsEnemy,startX,startY, 1));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
            ennemies.get(ennemynbr - deadennemies).getSprite().setScale(2, 2.5f);
        }
    }



    public void round11(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 3
        ennemies.add(new Ennemy(1200, 65, 70, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 2));
        ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        ennemies.get(ennemynbr - deadennemies).getSprite().setScale(2, 2.5f);
    }

    public void round12(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 2 avec recharge
        ennemies2transition.getFirst().setLife(300);
        ennemies2transition.getFirst().setSpeed(ennemies2transition.getFirst().getNormalspeed());
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().beReload();
        ennemies2transition.getFirst().setPoint(ennemies2transition.getFirst().getNormalpoint()*2);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }


    public void round13(int temps, Stage stage, World world, int ennemynbr) { //Trois ennemis 2 et un ennemi 3 A CORRIGER
        if(ennemynbr < 46){
            ennemies2transition.getFirst().setLife(300);
            ennemies2transition.getFirst().setPosition(startX, startY);
            ennemies2transition.getFirst().setDirection(0);
            ennemies2transition.getFirst().setTarget(0);
            ennemies2transition.getFirst().setAttackPlayer(false);
            ennemies2transition.getFirst().setSpeed(ennemies2transition.getFirst().getNormalspeed());
            ennemies2transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies2transition.getFirst().setPoint(ennemies2transition.getFirst().getNormalpoint());
            ennemies2transition.getFirst().setDoreload(false);
            ennemies.add(ennemies2transition.getFirst());
            ennemies2transition.removeFirst();
        }
        else{
            ennemies3transition.getFirst().setLife(1200);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
    }

    public void round14(int temps, Stage stage, World world, int ennemynbr) { //Un ennemi 2 rapide avec recharge
        ennemies2transition.getFirst().setLife(300);
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().beReload();
        ennemies2transition.getFirst().beFast();
        ennemies2transition.getFirst().setPoint(70);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }

    public void round15(int temps, Stage stage, World world, int ennemynbr) { //Cinq ennemis 2 rapides
        ennemies2transition.getFirst().setLife(300);
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().setDoreload(false);
        ennemies2transition.getFirst().beFast();
        ennemies2transition.getFirst().setPoint(35);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }

    public void round16(int temps, Stage stage, World world, int ennemynbr) { // Deux ennemis 3
        if(ennemynbr < 54) {
            ennemies3transition.getFirst().setLife(1200);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1200, 65, 70, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
            ennemies.get(ennemynbr - deadennemies).getSprite().setScale(2, 2.5f);
        }
    }


    public void round17(int temps, Stage stage, World world, int ennemynbr) { // Trois ennemis 2 rapides avec recharge
        ennemies2transition.getFirst().setLife(300);
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().beReload();
        ennemies2transition.getFirst().beFast();
        ennemies2transition.getFirst().setPoint(70);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }


    public void round18(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 4
        ennemies.add(new Ennemy(1500, 85, 100, new Texture("ennemies_sprite/red2.png"), stage, world,directionsEnemy,startX,startY, 2));
        ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        ennemies.get(ennemynbr - deadennemies).getSprite().setScale(4, 5);
    }

    public void round19(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 3 rapide
        ennemies3transition.getFirst().setLife(1200);
        ennemies3transition.getFirst().setDirection(0);
        ennemies3transition.getFirst().setPosition(startX, startY);
        ennemies3transition.getFirst().setTarget(0);
        ennemies3transition.getFirst().setAttackPlayer(false);
        ennemies3transition.getFirst().beFast();
        ennemies3transition.getFirst().setPoint(80);
        ennemies.add(ennemies3transition.getFirst());
        ennemies3transition.removeFirst();
    }


    public void round20(int temps, Stage stage, World world, int ennemynbr) { // Trois ennemis 3
        if(ennemynbr < 62) {
            ennemies3transition.getFirst().setLife(1200);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1200, 65, 70, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
            ennemies.get(ennemynbr - deadennemies).getSprite().setScale(2, 2.5f);
        }
    }

    public void round21(int temps, Stage stage, World world, int ennemynbr) { // Cinq ennemis 2 rapides avec recharge
        ennemies2transition.getFirst().setLife(300);
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().beReload();
        ennemies2transition.getFirst().beFast();
        ennemies2transition.getFirst().setPoint(70);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }

    public void round22(int temps, Stage stage, World world, int ennemynbr) { // Deux ennemis 4
        if(ennemynbr < 69) {
            ennemies4transition.getFirst().setLife(1500);
            ennemies4transition.getFirst().setPosition(startX, startY);
            ennemies4transition.getFirst().setDirection(0);
            ennemies4transition.getFirst().setTarget(0);
            ennemies4transition.getFirst().setAttackPlayer(false);
            ennemies4transition.getFirst().setSpeed(ennemies4transition.getFirst().getNormalspeed());
            ennemies4transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies4transition.getFirst().setPoint(ennemies4transition.getFirst().getNormalpoint());
            ennemies4transition.getFirst().setDoreload(false);
            ennemies.add(ennemies4transition.getFirst());
            ennemies4transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1500, 85, 100, new Texture("ennemies_sprite/red2.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
            ennemies.get(ennemynbr - deadennemies).getSprite().setScale(4, 5);
        }
    }

    public void round23(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 4 et deux ennemis 3
        if(ennemynbr < 71) {
            ennemies4transition.getFirst().setLife(1500);
            ennemies4transition.getFirst().setPosition(startX, startY);
            ennemies4transition.getFirst().setDirection(0);
            ennemies4transition.getFirst().setTarget(0);
            ennemies4transition.getFirst().setAttackPlayer(false);
            ennemies4transition.getFirst().setSpeed(ennemies4transition.getFirst().getNormalspeed());
            ennemies4transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies4transition.getFirst().setPoint(ennemies4transition.getFirst().getNormalpoint());
            ennemies4transition.getFirst().setDoreload(false);
            ennemies.add(ennemies4transition.getFirst());
            ennemies4transition.removeFirst();
        }
        else{
            ennemies3transition.getFirst().setLife(1200);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
    }

    public void round24(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 3 avec recharge
        ennemies3transition.getFirst().setLife(1200);
        ennemies3transition.getFirst().setPosition(startX, startY);
        ennemies3transition.getFirst().setDirection(0);
        ennemies3transition.getFirst().setTarget(0);
        ennemies3transition.getFirst().setAttackPlayer(false);
        ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
        ennemies3transition.getFirst().beReload();
        ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint() * 2);
        ennemies3transition.getFirst().setDoreload(false);
        ennemies.add(ennemies3transition.getFirst());
        ennemies3transition.removeFirst();
    }

    public void round25(int temps, Stage stage, World world, int ennemynbr) { // Quatre ennemis 3
        if(ennemynbr < 77) {
            ennemies3transition.getFirst().setLife(1200);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1200, 65, 70, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
            ennemies.get(ennemynbr - deadennemies).getSprite().setScale(2, 2.5f);
        }
    }

    public void round26(int temps, Stage stage, World world, int ennemynbr) { // Trois ennemis 4
        if(ennemynbr < 80) {
            ennemies4transition.getFirst().setLife(1500);
            ennemies4transition.getFirst().setPosition(startX, startY);
            ennemies4transition.getFirst().setDirection(0);
            ennemies4transition.getFirst().setTarget(0);
            ennemies4transition.getFirst().setAttackPlayer(false);
            ennemies4transition.getFirst().setSpeed(ennemies4transition.getFirst().getNormalspeed());
            ennemies4transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies4transition.getFirst().setPoint(ennemies4transition.getFirst().getNormalpoint());
            ennemies4transition.getFirst().setDoreload(false);
            ennemies.add(ennemies4transition.getFirst());
            ennemies4transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1500, 85, 100, new Texture("ennemies_sprite/red2.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
            ennemies.get(ennemynbr - deadennemies).getSprite().setScale(4, 5);
        }
    }

    public void round27(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 5
        ennemies.add(new Ennemy(3500, 50, 125, new Texture("Tank4.png"), stage, world,directionsEnemy,startX,startY, 3));
        ennemies.get(ennemynbr - deadennemies).defineEnnemy();
    }

    public void round28(int temps, Stage stage, World world, int ennemynbr) { //Cinq ennemis 3
        if(ennemynbr < 86) {
            ennemies3transition.getFirst().setLife(1200);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1200, 65, 70, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
            ennemies.get(ennemynbr - deadennemies).getSprite().setScale(2, 2.5f);
        }
    }

    public void round29(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 4 rapide
        ennemies4transition.getFirst().setLife(1500);
        ennemies4transition.getFirst().setPosition(startX, startY);
        ennemies4transition.getFirst().setDirection(0);
        ennemies4transition.getFirst().setTarget(0);
        ennemies4transition.getFirst().setAttackPlayer(false);
        ennemies4transition.getFirst().beFast();
        ennemies4transition.getFirst().setPoint(115);
        ennemies4transition.getFirst().setDoreload(false);
        ennemies.add(ennemies4transition.getFirst());
        ennemies4transition.removeFirst();
    }




    public void update(float dt, Game game, Player player, Stage stage){ //update la position et l etat des ennemis
        if(ennemies.size()==0){
            temps++;

            if(temps > 1500){
                roundnbr++;
                temps = 0;
            }
        }
        else {
            for (int i = 0; i < ennemies.size(); i++) {
                ennemies.get(i).update(dt,this.endRect);
                if(ennemies.get(i).getAttackPlayer()){
                    player.loseLife(ennemies.get(i).getDamage());
                    if(ennemies.get(i).getInitiallife() == 80){
                        ennemies1transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 300){
                        ennemies2transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 1200){
                        ennemies3transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 1500){
                        ennemies4transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 3500){
                        ennemies5transition.addLast(ennemies.get(i));
                    }
                    deadennemies++;
                    ennemies.remove(i);
                }
                if (0 < ennemies.size() && !ennemies.get(i).isAlive()) {
                    transition = (int) ((ennemies.get(i).getPoint()) * (1 + (player.getMoneyboost()/10)));
                    player.setMoney(player.getMoney() + transition);
                    player.setScore(player.getScore() + ennemies.get(i).getPoint());
                    if(ennemies.get(i).getInitiallife() == 80){
                        ennemies1transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 300){
                        ennemies2transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 1200){
                        ennemies3transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 1500){
                        ennemies4transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 3500){
                        ennemies5transition.addLast(ennemies.get(i));
                    }
                    deadennemies++;
                    ennemies.remove(i);
                }
            }
        }
    }

    public void draw(Batch batch){ // draw les ennemis du tableau
        for(int i = 0; i < ennemies.size(); i++){

            ennemies.get(i).getSprite().setPosition(ennemies.get(i).getX()-62,ennemies.get(i).getY()-30);
            ennemies.get(i).getSprite().draw(batch);

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

    public void updateTower(float delta, SpriteBatch batch, Game game, Stage uiStage, Tower tour) {
        if(ennemies.size() != 0){
            for (int i = 0; i < ennemies.size(); i++){
                if (ennemies.get(i) != null) {
                    if (ennemies.get(i).isInRange(tour)) {
                        tour.updateTower(delta, batch, ennemies.get(i), game, uiStage);
                        i = 1000;
                    }
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

    public LinkedList<Ennemy> getEnnemies(){
        return ennemies;
    }

}