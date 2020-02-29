package com.mygdx.game;

import java.util.concurrent.TimeUnit;

public class Armes extends Player {

    public int degats;
    public int portee;
    public int cadence;
    public int prix;
    int distance; // ne doit pas etre ici mais permet de compiler en attendant, distance a calculer avec getX et getY

    public Armes(int degats, int portee, int cadence, int prix){
        this.degats = degats;
        this.portee = portee;
        this.cadence = cadence;
        this.prix = prix;
    }

    public void Tir(){
        while (distance < portee){
            try {
                TimeUnit.SECONDS.sleep(cadence);
            }
            catch(InterruptedException ex){
                break;
            }
        }
    }

}
