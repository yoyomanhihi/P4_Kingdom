package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Ennemy extends BaseActor{

    public int life;
    public int speed;
    public int current_life;
    public int point;
    //public int damage;  Si on veut faire en sorte qu'un ennemy puisse attaquer une tour

    public Ennemy(int life, int speed, int current_life, int point, Stage s){
        super(0, 1, s);
        this.life = life;
        this.speed = speed;
        this.current_life = current_life;
        this.point = point;
    }


    public boolean isAlive(Ennemy Ennemy){
        if (Ennemy.current_life <= 0)
            return false;
        return true;
    }

    public void spawnEnnemy(int pos_x, int pos_y, Ennemy Ennemy){
        //Je sais pas vraiment comment faire
    }

    public int getLife(Ennemy Ennemy){
        return Ennemy.life;
    }

    public int getSpeed(Ennemy Ennemy){
        return Ennemy.speed;
    }

    public int getCurrent_life(Ennemy Ennemy){
        return Ennemy.current_life;
    }

    public int getPoint(Ennemy Ennemy){
        return Ennemy.point;
    }

    public void setLife(Ennemy Ennemy, int current_life){
        Ennemy.current_life = current_life;
    }

    public void setSpeed(Ennemy Ennemy, int speed){
        Ennemy.speed = speed;
    }
}