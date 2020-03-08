package com.mygdx.game;

import java.util.LinkedList;
import java.util.List;

public class Player {

    public int money;
    public int life;
    public LinkedList<Tower> weapons;
    public int score;

    public Player(){ //donne un budget et un nombre de vie au joueur au début de la partie
        money = 150;
        life = 1;
        score = 0;
        weapons = new LinkedList<>();
    }

    public void buyWeapons(Tower tour){ //Si le joueur a assez d'argent, enleve le prix de l arme a son budget
        if(tour.getPrice() < money){
            tour.setLocked(false);
            money = money - tour.getPrice(); // Il faut aussi faire comprendre que dans ce cas la il recupere l arme
        }
    }

    public void sellWeapon(Tower tour){
        money = money + tour.getPrice()/2;
    }

}
