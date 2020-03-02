package com.mygdx.game;

public class Ennemy {

    public int life;
    public int speed;
    public int current_life;
    public int point;
    //public int damage;  Si on veut faire en sorte qu'un ennemy puisse attaquer une tour

    public Ennemy(int life, int speed, int current_life, int point){
        this.life = life;
        this.speed = speed;
        this.current_life = current_life;
        this.point = point;
    }

    //Pas sur que je doive faire comme ca, je pense qu il faut creer une nouvelle classe qui herite
    public Ennemy slow_walker = new Ennemy(100,5,100,20);
    public Ennemy fast_walker = new Ennemy(80,10,80,25);
    public Ennemy boss = new Ennemy(500,3,500,100);
    public Ennemy[] type_list = new Ennemy[] {slow_walker,fast_walker,boss};

    public boolean isAlive(Ennemy Ennemy){
        if (Ennemy.current_life <= 0)
            return false;
        return true;
    }

    public void spawnEnnemy(int pos_x, int pos_y, Ennemy Ennemy){
        //Je sais pas vraiment comment faire
    }
}
