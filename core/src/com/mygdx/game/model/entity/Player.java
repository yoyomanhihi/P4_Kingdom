package com.mygdx.game.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int money;
    private int life;
    private List<Tower> weapons;
    private int score;

    public Player(){ //donne un budget et un nombre de vie au joueur au début de la partie
        this.money = 150;
        this.life = 1;
        this.score = 0;
        weapons = new ArrayList<>();
    }

    public void buyWeapons(Tower tour){ //Si le joueur a assez d'argent, enleve le prix de l arme a son budget
        if(tour.getPrice() <= money){
            tour.setLocked(false);
            setMoney(getMoney() - tour.getPrice());
            weapons.add(tour);
        }
    }

    public boolean isEmptyWeapons(){
        return weapons.isEmpty();
    }

    public List<Tower> getWeapons() {
        return weapons;
    }
    public void sellWeapon(Tower tour){
        setMoney(getMoney() + tour.getPrice()/2);
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLife() {return this.life;}

    public void setLife(int life) {
        this.life = life;
    }

    public int getMoney() {return this.money;}

    public void setMoney(int money) {
        this.money = money;
    }

    public void loseLife(int damage){
        this.life = this.life - damage;
    }
}
