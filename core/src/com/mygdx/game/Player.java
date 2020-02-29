package com.mygdx.game;

public class Player {

    public int argent;
    public int nombredevies;

    public Player(){ //donne un budget et un nombre de vie au joueur au début de la partie
        argent = 150;
        nombredevies = 1;
    }

    public void Achat(int prix){ //Si le joueur a assez d'argent, enleve le prix de l arme a son budget
        if(prix < argent){
            argent = argent - prix; // Il faut aussi faire comprendre que dans ce cas la il recupere l arme
        }
    }

    public void Vente(int prix){
        argent = argent + prix/2;
    }

}
