package model;

import personnagesJeu.Personnage;

public class LandMine implements Weapon {

    int x;
    int y;
    protected Personnage owner;
    protected Plateau plateau;

    public void detonation(Personnage owner, Plateau plateau, int x, int y) {
        this.owner = owner;
        this.plateau = plateau;
        this.x = x;
        this.y = y;
    }

    public Personnage getOwner() {
        return this.owner;
    }

    public void detonation(){
        /*if() {

        }*/
    }
}
