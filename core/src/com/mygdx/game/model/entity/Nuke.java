package com.mygdx.game.model.entity;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.model.entity.Ennemy;

import java.util.LinkedList;

public class Nuke {

    private int number;
    private float cooldown;
    private float damage;
    private Sound sound;

    public Nuke(int number){
        this.number = number;
        this.damage = 1000;
        cooldown = 0;
        this.sound = (Gdx.audio.newSound(Gdx.files.internal("explosion.mp3")));
    }

    public boolean isAvailable(){
        if (getNumber() > 0){
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
        if (gForce() >= 38)
            return true;
        return false;
    }

    public void explode(LinkedList<Ennemy> ennemies){
        if(cooldown == 0) {
            sound.play();
            this.cooldown = 600;
            number--;
            for (int i = 0; i < ennemies.size(); i++) {
                ennemies.get(i).setLife(hp_calculator(ennemies.get(i).getLife(), this.damage));
            }
        }

    }

    //This allows us to decide if we want to 1shot the ennemy or not
    public float hp_calculator(float hp, float damage){
        if (hp - damage < 0)
            return 0;
        return hp-damage;
    }

    public int getNumber()
    { return number;}

    public void setNumber(int number){
        this.number = number;
    }

    public float getDamage(Nuke nuke){ return nuke.damage;}

    public float getCooldown(){
        return cooldown;
    }

    public void setCooldown(float cooldown){
        this.cooldown = cooldown;
    }
}