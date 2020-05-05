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
    private int transition;
    private int finalroundbooster;

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
        finalroundbooster = 0;
    } //Put the good size for the first wave

    public void round0(int temps, Stage stage, World world, int ennemynbr){ //Un ennemi 1       8
        ennemies.add(new Ennemy(100, 120, 8, new Texture("ennemies_sprite/red2.png"), stage, world,directionsEnemy,startX,startY, 1));
        ennemies.get(ennemynbr-deadennemies).defineEnnemy();
        //ennemies.get(ennemynbr-deadennemies).beFast();
    }

    public void round1(int temps, Stage stage, World world, int ennemynbr){ //Deux ennemis 1    16  24
        if(ennemynbr < 2){
            ennemies1transition.getFirst().setLife(100);
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
            ennemies.add(new Ennemy(100, 120, 8, new Texture("ennemies_sprite/red2.png"), stage, world, directionsEnemy, startX, startY, 1));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round2(int temps, Stage stage, World world, int ennemynbr){ //Trois ennemis 1   24  40
        if(ennemynbr < 5){
            ennemies1transition.getFirst().setLife(100);
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
            ennemies.add(new Ennemy(100, 120, 8, new Texture("ennemies_sprite/red2.png"), stage, world, directionsEnemy, startX, startY, 1));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round3(int temps, Stage stage, World world, int ennemynbr){ //Un ennemi 2       30  70
        ennemies.add(new Ennemy(350, 75, 30, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 1));
        ennemies.get(ennemynbr - deadennemies).defineEnnemy();
    }

    public void round4(int temps, Stage stage, World world, int ennemynbr) { // Cinq ennemis 1
        if(ennemynbr < 10){
            ennemies1transition.getFirst().setLife(100);
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
            ennemies.add(new Ennemy(100, 120, 8, new Texture("ennemies_sprite/red2.png"), stage, world,directionsEnemy,startX,startY, 1));
            ennemies.get(ennemynbr-deadennemies).defineEnnemy();
        }
    }



    public void round5(int temps, Stage stage, World world, int ennemynbr) { //Trois ennemis 1 & Un ennemi 2    54  124
        if(ennemynbr < 15) {
            ennemies1transition.getFirst().setLife(100);
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
            ennemies2transition.getFirst().setLife(350);
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

    public void round6(int temps, Stage stage, World world, int ennemynbr){ // Deux ennemis 2   60  184
        if(ennemynbr < 17) {
            ennemies2transition.getFirst().setLife(350);
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
            ennemies.add(new Ennemy(350, 75, 30, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 1));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round7(int temps, Stage stage, World world, int ennemynbr) { //Cinq ennemis 1 et Trois ennemis 2    130     314
        if(ennemynbr < 23){
            ennemies1transition.getFirst().setLife(100);
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
            ennemies2transition.getFirst().setLife(350);
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
            ennemies.add(new Ennemy(350, 75, 30, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 1));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round8(int temps, Stage stage, World world, int ennemynbr) { //Dix ennemis 1 rapides    120     434
        if(ennemynbr < 31){
            ennemies1transition.getFirst().setLife(100);
            ennemies1transition.getFirst().setPosition(startX, startY);
            ennemies1transition.getFirst().setDirection(0);
            ennemies1transition.getFirst().setTarget(0);
            ennemies1transition.getFirst().setAttackPlayer(false);
            ennemies1transition.getFirst().beFast();
            ennemies1transition.getFirst().setPoint(12);
            ennemies1transition.getFirst().setDoreload(false);
            ennemies.add(ennemies1transition.getFirst());
            ennemies1transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(100, 120, 8, new Texture("ennemies_sprite/red2.png"), stage, world,directionsEnemy,startX,startY, 1));
            ennemies.get(ennemynbr-deadennemies).defineEnnemy();
            ennemies.get(ennemynbr-deadennemies).beFast();
            ennemies.get(ennemynbr-deadennemies).setPoint(12);
        }
    }

    public void round9(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 2 rapide      40      474
        ennemies2transition.getFirst().setLife(350);
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().beFast();
        ennemies2transition.getFirst().setPoint(40);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }

    public void round10(int temps, Stage stage, World world, int ennemynbr) { //Quatre ennemis 2        120     594
        if(ennemynbr < 39){
            ennemies2transition.getFirst().setLife(350);
            ennemies2transition.getFirst().setPosition(startX, startY);
            ennemies2transition.getFirst().setDirection(0);
            ennemies2transition.getFirst().setTarget(0);
            ennemies2transition.getFirst().setAttackPlayer(false);
            ennemies2transition.getFirst().setSpeed(ennemies2transition.getFirst().getNormalspeed());
            ennemies2transition.getFirst().setIsfast(false);
            ennemies2transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies2transition.getFirst().setPoint(ennemies2transition.getFirst().getNormalpoint());
            ennemies2transition.getFirst().setDoreload(false);
            ennemies.add(ennemies2transition.getFirst());
            ennemies2transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(350, 75, 30, new Texture("ennemies_sprite/red3.png"), stage, world,directionsEnemy,startX,startY, 1));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }



    public void round11(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 3        70      664
        ennemies.add(new Ennemy(1400, 65, 70, new Texture("ennemies_sprite/red4.png"), stage, world,directionsEnemy,startX,startY, 2));
        ennemies.get(ennemynbr - deadennemies).defineEnnemy();
    }

    public void round12(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 2 avec recharge  60  724
        ennemies2transition.getFirst().setLife(350);
        ennemies2transition.getFirst().setSpeed(ennemies2transition.getFirst().getNormalspeed());
        ennemies2transition.getFirst().setIsfast(false);
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().beReload();
        ennemies2transition.getFirst().setPoint(ennemies2transition.getFirst().getNormalpoint()*2);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }


    public void round13(int temps, Stage stage, World world, int ennemynbr) { //Trois ennemis 2 et un ennemi 3  160     884
        if(ennemynbr < 46){
            ennemies2transition.getFirst().setLife(350);
            ennemies2transition.getFirst().setPosition(startX, startY);
            ennemies2transition.getFirst().setDirection(0);
            ennemies2transition.getFirst().setTarget(0);
            ennemies2transition.getFirst().setAttackPlayer(false);
            ennemies2transition.getFirst().setSpeed(ennemies2transition.getFirst().getNormalspeed());
            ennemies2transition.getFirst().setIsfast(false);
            ennemies2transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies2transition.getFirst().setPoint(ennemies2transition.getFirst().getNormalpoint());
            ennemies2transition.getFirst().setDoreload(false);
            ennemies.add(ennemies2transition.getFirst());
            ennemies2transition.removeFirst();
        }
        else{
            ennemies3transition.getFirst().setLife(1400);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().setIsfast(false);
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
    }

    public void round14(int temps, Stage stage, World world, int ennemynbr) { //Un ennemi 2 rapide avec recharge    60      964
        ennemies2transition.getFirst().setLife(350);
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().beReload();
        ennemies2transition.getFirst().beFast();
        ennemies2transition.getFirst().setPoint(60);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }

    public void round15(int temps, Stage stage, World world, int ennemynbr) { //Cinq ennemis 2 rapides      200     1164
        ennemies2transition.getFirst().setLife(350);
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().setDoreload(false);
        ennemies2transition.getFirst().beFast();
        ennemies2transition.getFirst().setPoint(40);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }

    public void round16(int temps, Stage stage, World world, int ennemynbr) { // Deux ennemis 3         140     1304
        if(ennemynbr < 54) {
            ennemies3transition.getFirst().setLife(1400);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().setIsfast(false);
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1400, 65, 70, new Texture("ennemies_sprite/red4.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }


    public void round17(int temps, Stage stage, World world, int ennemynbr) { // Trois ennemis 2 rapides avec recharge      180     1544
        ennemies2transition.getFirst().setLife(350);
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().beReload();
        ennemies2transition.getFirst().beFast();
        ennemies2transition.getFirst().setPoint(60);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }


    public void round18(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 4        100     1644
        ennemies.add(new Ennemy(1700, 85, 100, new Texture("ennemies_sprite/red1.png"), stage, world,directionsEnemy,startX,startY, 2));
        ennemies.get(ennemynbr - deadennemies).defineEnnemy();
    }

    public void round19(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 3 rapide         90      1734
        ennemies3transition.getFirst().setLife(1400);
        ennemies3transition.getFirst().setDirection(0);
        ennemies3transition.getFirst().setPosition(startX, startY);
        ennemies3transition.getFirst().setTarget(0);
        ennemies3transition.getFirst().setAttackPlayer(false);
        ennemies3transition.getFirst().beFast();
        ennemies3transition.getFirst().setPoint(80);
        ennemies.add(ennemies3transition.getFirst());
        ennemies3transition.removeFirst();
    }


    public void round20(int temps, Stage stage, World world, int ennemynbr) { // Trois ennemis 3        210     1944
        if(ennemynbr < 62) {
            ennemies3transition.getFirst().setLife(1400);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().setIsfast(false);
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1400, 65, 70, new Texture("ennemies_sprite/red4.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round21(int temps, Stage stage, World world, int ennemynbr) { // Cinq ennemis 2 rapides avec recharge       300     2344
        ennemies2transition.getFirst().setLife(350);
        ennemies2transition.getFirst().setPosition(startX, startY);
        ennemies2transition.getFirst().setDirection(0);
        ennemies2transition.getFirst().setTarget(0);
        ennemies2transition.getFirst().setAttackPlayer(false);
        ennemies2transition.getFirst().beReload();
        ennemies2transition.getFirst().beFast();
        ennemies2transition.getFirst().setPoint(60);
        ennemies.add(ennemies2transition.getFirst());
        ennemies2transition.removeFirst();
    }

    public void round22(int temps, Stage stage, World world, int ennemynbr) { // Deux ennemis 4     200     2544
        if(ennemynbr < 69) {
            ennemies4transition.getFirst().setLife(1700);
            ennemies4transition.getFirst().setPosition(startX, startY);
            ennemies4transition.getFirst().setDirection(0);
            ennemies4transition.getFirst().setTarget(0);
            ennemies4transition.getFirst().setAttackPlayer(false);
            ennemies4transition.getFirst().setSpeed(ennemies4transition.getFirst().getNormalspeed());
            ennemies4transition.getFirst().setIsfast(false);
            ennemies4transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies4transition.getFirst().setPoint(ennemies4transition.getFirst().getNormalpoint());
            ennemies4transition.getFirst().setDoreload(false);
            ennemies.add(ennemies4transition.getFirst());
            ennemies4transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1700, 85, 100, new Texture("ennemies_sprite/red1.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round23(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 4 et deux ennemis 3      240     2784
        if(ennemynbr < 71) {
            ennemies4transition.getFirst().setLife(1700);
            ennemies4transition.getFirst().setPosition(startX, startY);
            ennemies4transition.getFirst().setDirection(0);
            ennemies4transition.getFirst().setTarget(0);
            ennemies4transition.getFirst().setAttackPlayer(false);
            ennemies4transition.getFirst().setSpeed(ennemies4transition.getFirst().getNormalspeed());
            ennemies4transition.getFirst().setIsfast(false);
            ennemies4transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies4transition.getFirst().setPoint(ennemies4transition.getFirst().getNormalpoint());
            ennemies4transition.getFirst().setDoreload(false);
            ennemies.add(ennemies4transition.getFirst());
            ennemies4transition.removeFirst();
        }
        else{
            ennemies3transition.getFirst().setLife(1400);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().setIsfast(false);
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
    }

    public void round24(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 3 avec recharge      120  2924
        ennemies3transition.getFirst().setLife(1400);
        ennemies3transition.getFirst().setPosition(startX, startY);
        ennemies3transition.getFirst().setDirection(0);
        ennemies3transition.getFirst().setTarget(0);
        ennemies3transition.getFirst().setAttackPlayer(false);
        ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
        ennemies3transition.getFirst().setIsfast(false);
        ennemies3transition.getFirst().beReload();
        ennemies3transition.getFirst().setPoint(120);
        ennemies.add(ennemies3transition.getFirst());
        ennemies3transition.removeFirst();
    }

    public void round25(int temps, Stage stage, World world, int ennemynbr) { // Quatre ennemis 3       280     3204
        if(ennemynbr < 77) {
            ennemies3transition.getFirst().setLife(1400);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().setIsfast(false);
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1400, 65, 70, new Texture("ennemies_sprite/red4.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round26(int temps, Stage stage, World world, int ennemynbr) { // Trois ennemis 4       300      3504
        if(ennemynbr < 80) {
            ennemies4transition.getFirst().setLife(1700);
            ennemies4transition.getFirst().setPosition(startX, startY);
            ennemies4transition.getFirst().setDirection(0);
            ennemies4transition.getFirst().setTarget(0);
            ennemies4transition.getFirst().setAttackPlayer(false);
            ennemies4transition.getFirst().setSpeed(ennemies4transition.getFirst().getNormalspeed());
            ennemies4transition.getFirst().setIsfast(false);
            ennemies4transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies4transition.getFirst().setPoint(ennemies4transition.getFirst().getNormalpoint());
            ennemies4transition.getFirst().setDoreload(false);
            ennemies.add(ennemies4transition.getFirst());
            ennemies4transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1700, 85, 100, new Texture("ennemies_sprite/red1.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round27(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 5        150     3704
        ennemies.add(new Ennemy(4200, 50, 150, new Texture("ennemies_sprite/red2.png"), stage, world,directionsEnemy,startX,startY, 3));
        ennemies.get(ennemynbr - deadennemies).defineEnnemy();
    }

    public void round28(int temps, Stage stage, World world, int ennemynbr) { //Cinq ennemis 3      350     4054
        if(ennemynbr < 86) {
            ennemies3transition.getFirst().setLife(1400);
            ennemies3transition.getFirst().setPosition(startX, startY);
            ennemies3transition.getFirst().setDirection(0);
            ennemies3transition.getFirst().setTarget(0);
            ennemies3transition.getFirst().setAttackPlayer(false);
            ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
            ennemies3transition.getFirst().setIsfast(false);
            ennemies3transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies3transition.getFirst().setPoint(ennemies3transition.getFirst().getNormalpoint());
            ennemies3transition.getFirst().setDoreload(false);
            ennemies.add(ennemies3transition.getFirst());
            ennemies3transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1400, 65, 70, new Texture("ennemies_sprite/red4.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round29(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 4 rapide     130     4204
        ennemies4transition.getFirst().setLife(1700);
        ennemies4transition.getFirst().setPosition(startX, startY);
        ennemies4transition.getFirst().setDirection(0);
        ennemies4transition.getFirst().setTarget(0);
        ennemies4transition.getFirst().setAttackPlayer(false);
        ennemies4transition.getFirst().beFast();
        ennemies4transition.getFirst().setPoint(130);
        ennemies4transition.getFirst().setDoreload(false);
        ennemies.add(ennemies4transition.getFirst());
        ennemies4transition.removeFirst();
    }

    public void round30(int temps, Stage stage, World world, int ennemynbr) { // Cinq ennemis 4   500     4704
        if(ennemynbr < 91) {
            ennemies4transition.getFirst().setLife(1700);
            ennemies4transition.getFirst().setPosition(startX, startY);
            ennemies4transition.getFirst().setDirection(0);
            ennemies4transition.getFirst().setTarget(0);
            ennemies4transition.getFirst().setAttackPlayer(false);
            ennemies4transition.getFirst().setSpeed(ennemies4transition.getFirst().getNormalspeed());
            ennemies4transition.getFirst().setIsfast(false);
            ennemies4transition.getFirst().getSprite().setColor(Color.WHITE);
            ennemies4transition.getFirst().setPoint(ennemies4transition.getFirst().getNormalpoint());
            ennemies4transition.getFirst().setDoreload(false);
            ennemies.add(ennemies4transition.getFirst());
            ennemies4transition.removeFirst();
        }
        else{
            ennemies.add(new Ennemy(1700, 85, 100, new Texture("ennemies_sprite/red1.png"), stage, world,directionsEnemy,startX,startY, 2));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round31(int temps, Stage stage, World world, int ennemynbr) { // Trois ennemis 3 avec recharge   360     5124
        ennemies3transition.getFirst().setLife(1400);
        ennemies3transition.getFirst().setPosition(startX, startY);
        ennemies3transition.getFirst().setDirection(0);
        ennemies3transition.getFirst().setTarget(0);
        ennemies3transition.getFirst().setAttackPlayer(false);
        ennemies3transition.getFirst().setSpeed(ennemies3transition.getFirst().getNormalspeed());
        ennemies3transition.getFirst().setIsfast(false);
        ennemies3transition.getFirst().beReload();
        ennemies3transition.getFirst().setPoint(120);
        ennemies.add(ennemies3transition.getFirst());
        ennemies3transition.removeFirst();
    }

    public void round32(int temps, Stage stage, World world, int ennemynbr) { // Deux ennemis 5     300        5324
        if(ennemynbr < 97) {
            ennemies5transition.getFirst().setLife(4200);
            ennemies5transition.getFirst().setPosition(startX, startY);
            ennemies5transition.getFirst().setDirection(0);
            ennemies5transition.getFirst().setTarget(0);
            ennemies5transition.getFirst().setAttackPlayer(false);
            ennemies5transition.getFirst().setSpeed(ennemies5transition.getFirst().getNormalspeed());
            ennemies5transition.getFirst().setIsfast(false);
            ennemies5transition.getFirst().setDoreload(false);
            ennemies5transition.getFirst().setPoint(ennemies5transition.getFirst().getNormalpoint());
            ennemies.add(ennemies5transition.getFirst());
            ennemies5transition.removeFirst();
        }
        else {
            ennemies.add(new Ennemy(4200, 50, 150, new Texture("ennemies_sprite/red2.png"), stage, world, directionsEnemy, startX, startY, 3));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }

    public void round33(int temps, Stage stage, World world, int ennemynbr) { // Deux ennemis 4 rapides     240        5584
        ennemies4transition.getFirst().setLife(1700);
        ennemies4transition.getFirst().setPosition(startX, startY);
        ennemies4transition.getFirst().setDirection(0);
        ennemies4transition.getFirst().setTarget(0);
        ennemies4transition.getFirst().setAttackPlayer(false);
        ennemies4transition.getFirst().beFast();
        ennemies4transition.getFirst().setPoint(120);
        ennemies4transition.getFirst().setDoreload(false);
        ennemies.add(ennemies4transition.getFirst());
        ennemies4transition.removeFirst();
    }

    public void round34(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 4 avec recharge     180        5844
        ennemies4transition.getFirst().setLife(1700);
        ennemies4transition.getFirst().setPosition(startX, startY);
        ennemies4transition.getFirst().setDirection(0);
        ennemies4transition.getFirst().setTarget(0);
        ennemies4transition.getFirst().setAttackPlayer(false);
        ennemies4transition.getFirst().setSpeed(ennemies4transition.getFirst().getNormalspeed());
        ennemies4transition.getFirst().setPoint(180);
        ennemies4transition.getFirst().beReload();
        ennemies.add(ennemies4transition.getFirst());
        ennemies4transition.removeFirst();
    }

    public void round35(int temps, Stage stage, World world, int ennemynbr) { // Un ennemi 5 rapide    180        6024
        ennemies5transition.getFirst().setLife(4200);
        ennemies5transition.getFirst().setPosition(startX, startY);
        ennemies5transition.getFirst().setDirection(0);
        ennemies5transition.getFirst().setTarget(0);
        ennemies5transition.getFirst().setAttackPlayer(false);
        ennemies5transition.getFirst().beFast();
        ennemies5transition.getFirst().setDoreload(false);
        ennemies5transition.getFirst().setPoint(180);
        ennemies.add(ennemies5transition.getFirst());
        ennemies5transition.removeFirst();
    }

    public void round36(int temps, Stage stage, World world, int ennemynbr) { // Trois ennemis 5     450        6504
        if(ennemynbr < 104) {
            ennemies5transition.getFirst().setLife(4200);
            ennemies5transition.getFirst().setPosition(startX, startY);
            ennemies5transition.getFirst().setDirection(0);
            ennemies5transition.getFirst().setTarget(0);
            ennemies5transition.getFirst().setAttackPlayer(false);
            ennemies5transition.getFirst().setSpeed(ennemies5transition.getFirst().getNormalspeed());
            ennemies5transition.getFirst().setIsfast(false);
            ennemies5transition.getFirst().setDoreload(false);
            ennemies5transition.getFirst().setPoint(ennemies5transition.getFirst().getNormalpoint());
            ennemies.add(ennemies5transition.getFirst());
            ennemies5transition.removeFirst();
        }
        else {
            ennemies.add(new Ennemy(4200, 50, 150, new Texture("ennemies_sprite/red2.png"), stage, world, directionsEnemy, startX, startY, 3));
            ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        }
    }





    public void finalround(int temps, Stage stage, World world, int ennemynbr) { // Liste d'ennemis de plus en plus forts
        ennemies.add(new Ennemy(2000+(finalroundbooster*200), 120, 0, new Texture("ennemies_sprite/blue2.png"), stage, world,directionsEnemy,startX,startY, 10));
        ennemies.get(ennemynbr - deadennemies).defineEnnemy();
        finalroundbooster++;
    }



    public void update(float dt, Game game, Player player, Stage stage){ //update la position et l etat des ennemis
        if(ennemies.size()==0 && roundnbr < 37){
            temps++;

            if(temps > 1800){
                roundnbr++;
                temps = 0;
            }
        }
        else {
            for (int i = 0; i < ennemies.size(); i++) {
                ennemies.get(i).update(dt,this.endRect);
                if(ennemies.get(i).getAttackPlayer()){
                    player.loseLife(ennemies.get(i).getDamage());
                    if(ennemies.get(i).getInitiallife() == 100){
                        ennemies1transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 350){
                        ennemies2transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 1400){
                        ennemies3transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 1700){
                        ennemies4transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 4200){
                        ennemies5transition.addLast(ennemies.get(i));
                    }
                    deadennemies++;
                    ennemies.remove(i);
                }
                if (0 < ennemies.size() && !ennemies.get(i).isAlive()) {
                    transition = (int) ((ennemies.get(i).getPoint()) * (1 + (player.getMoneyboost()/10)));
                    player.setMoney(player.getMoney() + transition);
                    player.setScore(player.getScore() + ennemies.get(i).getPoint());
                    if(roundnbr == 37){
                        player.setBonusennemies(player.getBonusennemies()+1);
                    }
                    else if(ennemies.get(i).getInitiallife() == 100){
                        ennemies1transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 350){
                        ennemies2transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 1400){
                        ennemies3transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 1700){
                        ennemies4transition.addLast(ennemies.get(i));
                    }
                    else if(ennemies.get(i).getInitiallife() == 4200){
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


            if(ennemies.get(i).isIsfast() && ennemies.get(i).getDirection() == 90){ //vers le haut
                ennemies.get(i).getSpeedsprite().setPosition(ennemies.get(i).getX()-1035,ennemies.get(i).getY()-375);
                ennemies.get(i).getSpeedsprite().draw(batch);
                ennemies.get(i).getSpeedsprite().setRotation(100);
            }
            else if(ennemies.get(i).isIsfast() && ennemies.get(i).getDirection() == 270){//vers le bas
                ennemies.get(i).getSpeedsprite().setRotation(270);
                ennemies.get(i).getSpeedsprite().setPosition(ennemies.get(i).getX()-1038,ennemies.get(i).getY()-300);
                ennemies.get(i).getSpeedsprite().draw(batch);
            }
            else if(ennemies.get(i).isIsfast() && ennemies.get(i).getDirection() == 0){//vers la droite
                ennemies.get(i).getSpeedsprite().setRotation(0);
                ennemies.get(i).getSpeedsprite().setPosition(ennemies.get(i).getX()-1080,ennemies.get(i).getY()-338);
                ennemies.get(i).getSpeedsprite().draw(batch);
            }
            if(ennemies.get(i).getNormalspeed() == 120) {
                ennemies.get(i).getSprite().setPosition(ennemies.get(i).getX() - 65, ennemies.get(i).getY() - 32);
                ennemies.get(i).getSprite().draw(batch);
            }
            else if(ennemies.get(i).getInitiallife() == 350) {
                ennemies.get(i).getSprite().setPosition(ennemies.get(i).getX() - 62, ennemies.get(i).getY() - 30);
                ennemies.get(i).getSprite().draw(batch);
            }
            else if(ennemies.get(i).getInitiallife() == 1400) {
                ennemies.get(i).getSprite().setPosition(ennemies.get(i).getX() - 74, ennemies.get(i).getY() - 28);
                ennemies.get(i).getSprite().draw(batch);
            }
            else if(ennemies.get(i).getInitiallife() == 1700) {
                ennemies.get(i).getSprite().setPosition(ennemies.get(i).getX() - 74, ennemies.get(i).getY() - 30);
                ennemies.get(i).getSprite().draw(batch);
            }
            else if(ennemies.get(i).getInitiallife() == 4200) {
                ennemies.get(i).getSprite().setPosition(ennemies.get(i).getX() - 62, ennemies.get(i).getY() - 30);
                ennemies.get(i).getSprite().draw(batch);
            }
            if(ennemies.get(i).getPercentageOfLife() > 0.25){
                if(ennemies.get(i).isDoreload()){
                    batch.setColor(Color.BLUE);
                }
                else {
                    batch.setColor(Color.GREEN);
                }
            }
            else{
                batch.setColor(Color.RED);
            }
            batch.draw(healthbar, ennemies.get(i).getX(), ennemies.get(i).getY() + 70, 60*ennemies.get(i).getPercentageOfLife(), 5);
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