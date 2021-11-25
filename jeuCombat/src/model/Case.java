package model;

import observer.AbstractListenableModel;
import personnagesJeu.Personnage;

public class Case {

    protected boolean wall;
    protected boolean pastille;
    protected Weapon weapon;

    public Case(boolean wall, boolean pastille){
        this.wall = wall;
        this.pastille = pastille;
        this.weapon = null;
    }

    public boolean getWall(){
        return this.wall;
    }

    public boolean getPastille() {
        return this.pastille;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public void setPastille(boolean pastille) {
        this.pastille = pastille;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}