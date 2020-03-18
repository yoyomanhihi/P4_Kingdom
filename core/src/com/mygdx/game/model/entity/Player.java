package com.mygdx.game.model.entity;

import java.util.LinkedList;

public class Player {

    private int money;
    private int life;
    private LinkedList<Tower> weapons;
    private int score;

    public Player(){ //donne un budget et un nombre de vie au joueur au début de la partie
        money = 150;
        life = 1;
        setScore(0);
        weapons = new LinkedList<>();
    }

    public void buyWeapons(Tower tour){ //Si le joueur a assez d'argent, enleve le prix de l arme a son budget
        if(tour.getPrice() < money){
            tour.setLocked(false);
            money = money - tour.getPrice();
            weapons.add(tour);
        }
    }

    public void sellWeapon(Tower tour){
        money = money + tour.getPrice()/2;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
