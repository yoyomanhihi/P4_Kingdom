package com.mygdx.game.model.entity;

import com.badlogic.gdx.Gdx;

public class Nuke {

    private int number;
    private float cooldown;
    private int damage;

    public Nuke(int number, float cooldown){
        this.number = number;
        this.cooldown = cooldown;
        this.damage = 500000;
    }

    public boolean isAvailable(Nuke nuke){
        if (getNumber(nuke) != 0 && nuke.cooldown == 0){
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

    public void detectMovement(Nuke nuke){
        if (gForce() >= 1.7) {
            if (isAvailable(nuke)){
                explode();
            }
        }
    }

    public void explode(){
        this.number--;
        this.cooldown = 30;
    }

    public int getNumber(Nuke nuke){ return nuke.number;}

    public int getDamage(Nuke nuke){ return nuke.damage;}
}