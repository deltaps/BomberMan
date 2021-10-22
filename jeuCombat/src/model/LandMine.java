package model;

import personnagesJeu.Personnage;

public class LandMine implements Weapon {

    protected int[] position;
    protected Personnage owner;
    protected Plateau plateau;

    public void detonation(Personnage owner, int[] position, Plateau plateau) {
        this.owner = owner;
        this.position = position;
        this.plateau = plateau;
    }

    public Personnage getOwner() {
        return this.owner;
    }

    public void detonation(){
        /*if() {

        }*/
    }
}
