package model;

import personnagesJeu.Personnage;

public class Bomb implements Weapon{
    public int compteARebourt;
    public int x;
    public int y;
    public Personnage owner;

    public Bomb(Personnage currentPlayer, int x, int y, Personnage owner) {
        this.compteARebourt=3;
        this.x=x;
        this.y=y;
        this.owner = owner;

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getCompteARebourt() {
        return this.compteARebourt;
    }

    public void detonation(Case[][] bombePosition){
        /*
        this.compteARebourt--;
        if(compteARebourt==0){
            for(this.x = -1; x<= 1; x++){
                for(this.y = -1; y <=1; y++){
                    if(bombePosition[x][y] == Plateau.getJoueurs()).position[x,y]{

                    }
                }
            }
        }

         */
    }

    @Override
    public void detonation() {
    }
}
