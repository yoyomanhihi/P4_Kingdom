package com.mygdx.game.model.entity;

import com.badlogic.gdx.Gdx;

public class Nuke {

    private int number;
    private float cooldown;
    private int damage;
    private Ennemy[] ennemies;

    public Nuke(int number, float cooldown){
        this.number = number;
        this.cooldown = cooldown;
        this.damage = 500000;
    }

    public boolean isAvailable(){
        if (getNumber(this) != 0 && this.cooldown == 0){
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

    public void detectMovement(Ennemy[] ennemies){
        if (gForce() >= 1.7) {
            if (this.isAvailable()){
                explode(ennemies);
            }
        }
    }

    public void explode(Ennemy[] ennemies){
        this.number--;
        this.cooldown = 30;
        for (int i = 0; i<ennemies.length;i++){
            ennemies[i].setLife(0);
        }
    }

    public int getNumber(Nuke nuke){ return nuke.number;}

    public int getDamage(Nuke nuke){ return nuke.damage;}
}