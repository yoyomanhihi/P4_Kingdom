package com.mygdx.game;

import java.util.concurrent.TimeUnit;

public class Tower {

    public int damage;
    public int range;
    public int fireRate;
    public int price;
    public boolean locked;
    int distance; // ne doit pas etre ici mais permet de compiler en attendant, distance a calculer avec getX et getY

    public Tower(int degats, int portee, int cadence, int prix){
        this.damage = degats;
        this.range = portee;
        this.fireRate = cadence;
        this.price = prix;
        this.locked = true;
    }

    public void Shoot(){
        while (distance < getRange()){
            try {
                TimeUnit.SECONDS.sleep(fireRate);
            }
            catch(InterruptedException ex){
                break;
            }
        }
    }

    public int getPrice(){
        return price;
    }

    public int getRange(){
        return range;
    }

    public int getFireRate(){
        return fireRate;
    }

    public int getDamage(){
        return damage;
    }

}
