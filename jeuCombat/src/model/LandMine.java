package model;

import personnagesJeu.Personnage;

public class LandMine implements Weapon {

    protected Personnage owner;

    public LandMine(Personnage owner) {
        this.owner = owner;
    }

    public Personnage getOwner() {
        return this.owner;
    }

    public void detonation(){
        /*if() {

        }*/
    }
}
