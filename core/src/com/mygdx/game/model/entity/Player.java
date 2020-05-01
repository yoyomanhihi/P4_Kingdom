package com.mygdx.game.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int money;
    private int life;
    private List<Tower> weapons;
    private int score;
    private float moneyboost;
    private int endlife;
    private int bonusennemies;

    public Player(){ //donne un budget et un nombre de vie au joueur au début de la partie
        this.money = 225;
        this.life = 10;
        this.score = 0;
        this.moneyboost = 0;
        endlife = 0;
        bonusennemies = 0;
        weapons = new ArrayList<>();
    }

    public boolean canBuyNot(Tower tower){
        return tower.getPrice() <= this.money;
    }

    public void buyWeapons(Tower tour){ //Si le joueur a assez d'argent, enleve le prix de l arme a son budget
        if(canBuyNot(tour)){
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

        if(tour.getPrice() == 500){
            this.setMoneyboost(moneyboost - 1);
        }
        setMoney(getMoney() + tour.getPrice()/2);
        weapons.remove(tour);

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

    public float getMoneyboost(){
        return moneyboost;
    }

    public void boostMoney(float moneyboost){
        this.moneyboost = this.moneyboost*moneyboost;
    }

    public void setMoneyboost(float moneyboost){ this.moneyboost = moneyboost; }

    public void setEndlife(int endlife){
        this.endlife = endlife;
    }

    public int getEndlife(){
        return this.endlife;
    }

    public void setBonusennemies(int bonusennemies){
        this.bonusennemies = bonusennemies;
    }

    public int getBonusennemies(){
        return bonusennemies;
    }



}
