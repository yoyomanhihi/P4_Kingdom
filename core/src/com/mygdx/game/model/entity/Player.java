package com.mygdx.game.model.entity;

import com.badlogic.gdx.graphics.Color;

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
    private Nuke nuke;

    public Player(){ //donne un budget et un nombre de vie au joueur au début de la partie
        this.nuke = new Nuke(1);
        this.money = 1000;
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

    public void upgradeWeapon(Tower tower){
        if(tower.getID() == 1) {
            if (this.money >= 40) {
                if (tower.getUpgraded() == 0) {
                    setMoney(getMoney() - 40);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(10);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.BLUE);
                    weapons.get(indexTower).setUpgraded(1);
                    //weapons.get(indexTower).getWeapon_sprite().setColor(Color.BLUE);
                }
                else if (tower.getUpgraded() == 1) {
                    setMoney(getMoney() - 40);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(14);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.PURPLE);
                    weapons.get(indexTower).setUpgraded(2);
                    //weapons.get(indexTower).getWeapon_sprite().setColor(Color.BLUE);
                }
                else if (tower.getUpgraded() == 2) {
                    setMoney(getMoney() - 40);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(20);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.GOLD);
                    weapons.get(indexTower).setUpgraded(3);
                    //weapons.get(indexTower).getWeapon_sprite().setColor(Color.BLUE);
                }
            }
        }

        else if(tower.getID() == 2) {
            if (this.money >= 120) {
                if (tower.getUpgraded() == 0) {
                    setMoney(getMoney() - 120);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(12);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.BLUE);
                    weapons.get(indexTower).setUpgraded(1);
                    //weapons.get(indexTower).getWeapon_sprite().setColor(Color.BLUE);
                }
                else if (tower.getUpgraded() == 1) {
                    setMoney(getMoney() - 120);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(16);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.PURPLE);
                    weapons.get(indexTower).setUpgraded(2);
                    //weapons.get(indexTower).getWeapon_sprite().setColor(Color.BLUE);
                }
                else if (tower.getUpgraded() == 2) {
                    setMoney(getMoney() - 120);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(22);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.GOLD);
                    weapons.get(indexTower).setUpgraded(3);
                    //weapons.get(indexTower).getWeapon_sprite().setColor(Color.BLUE);
                }
            }
        }

        else if(tower.getID() == 3) {
            if (this.money >= 300) {
                if (tower.getUpgraded() == 0) {
                    setMoney(getMoney() - 300);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(195);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.BLUE);
                    weapons.get(indexTower).setUpgraded(1);
                    //weapons.get(indexTower).getWeapon_sprite().setColor(Color.BLUE);
                }
                else if (tower.getUpgraded() == 1) {
                    setMoney(getMoney() - 300);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(275);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.PURPLE);
                    weapons.get(indexTower).setUpgraded(2);
                    //weapons.get(indexTower).getWeapon_sprite().setColor(Color.BLUE);
                }
                else if (tower.getUpgraded() == 2) {
                    setMoney(getMoney() - 300);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(365);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.GOLD);
                    weapons.get(indexTower).setUpgraded(3);
                    //weapons.get(indexTower).getWeapon_sprite().setColor(Color.BLUE);
                }
            }
        }

        else if(tower.getID() == 4) {
            if (this.money >= 640) {
                if (tower.getUpgraded() == 0) {
                    setMoney(getMoney() - 640);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(195);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.BLUE);
                    weapons.get(indexTower).setUpgraded(1);
                }
                else if (tower.getUpgraded() == 1) {
                    setMoney(getMoney() - 640);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(275);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.PURPLE);
                    weapons.get(indexTower).setUpgraded(2);
                }
                else if (tower.getUpgraded() == 2) {
                    setMoney(getMoney() - 640);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(365);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.GOLD);
                    weapons.get(indexTower).setUpgraded(3);
                }
            }
        }

        else if(tower.getID() == 5) {
            if (this.money >= 200) {
                if (tower.getUpgraded() == 0) {
                    setMoney(getMoney() - 200);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setFireRate(80);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.BLUE);
                    weapons.get(indexTower).setUpgraded(1);
                    //weapons.get(indexTower).getWeapon_sprite().setColor(Color.BLUE);
                }
                else if (tower.getUpgraded() == 1) {
                    setMoney(getMoney() - 200);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(50);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.PURPLE);
                    weapons.get(indexTower).setUpgraded(2);
                    //weapons.get(indexTower).getWeapon_sprite().setColor(Color.BLUE);
                }
                else if (tower.getUpgraded() == 2) {
                    setMoney(getMoney() - 200);
                    int indexTower = weapons.indexOf(tower);
                    weapons.get(indexTower).setDamage(25);
                    weapons.get(indexTower).getBase_sprite().setColor(Color.GOLD);
                    weapons.get(indexTower).setUpgraded(3);
                }
            }
        }
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

    public Nuke getNuke(){
        return nuke;
    }



}
