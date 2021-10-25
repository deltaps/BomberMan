package model;

import personnagesJeu.Personnage;

public class Bomb implements Weapon{
    public int compteARebourt;
    public Personnage owner;

    public Bomb(Personnage owner) {
        this.owner = owner;
        this.compteARebourt=3;
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
    @Override
    public Personnage getOwner(){
        return this.owner;
    }
}
