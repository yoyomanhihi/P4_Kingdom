package com.mygdx.game.model.entity;

import com.badlogic.gdx.Gdx;

import com.mygdx.game.model.entity.Ennemy;

import java.util.LinkedList;

public class Nuke {

    private int number;
    private float cooldown;
    private float damage;
    private Ennemy[] ennemies;

    public Nuke(int number){
        this.number = number;
        //this.cooldown = cooldown;
        this.damage = 500000;
    }

    public boolean isAvailable(){
        if (getNumber(this) > 0){
            return true;
        }
        return false;
    }

    public float gForce(){
        float xGrav = Gdx.input.getAccelerometerX();
        float yGrav = Gdx.input.getAccelerometerY();
        float zGrav = Gdx.input.getAccelerometerZ();

        // gForce will be close to 1 when there is no movement.
        float gForce = (float)Math.sqrt((xGrav * xGrav) + (yGrav * yGrav) + (zGrav * zGrav));

        return gForce;
    }

    public boolean detectMovement(){
        if (gForce() >= 1.7)
            return true;
        return false;
    }

    public void explode(LinkedList<Ennemy> ennemies){
        this.number--;
        this.cooldown = 30;
        for (int i = 0 ; i<ennemies.size();i++){
            ennemies.get(i).setLife(hp_calculator(ennemies.get(i).getLife(),this.damage));
        }

    }

    //This allows us to decide if we want to 1shot the ennemy or not
    public float hp_calculator(float hp, float damage){
        if (hp - damage < 0)
            return 0;
        return hp-damage;
    }

    public int getNumber(Nuke nuke){ return nuke.number;}

    public float getDamage(Nuke nuke){ return nuke.damage;}
}