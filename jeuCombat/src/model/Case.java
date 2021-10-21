package model;

import personnagesJeu.Personnage;

public class Case {

    protected boolean wall;
    protected boolean capsule;
    protected Weapon weapon;


    public Case(boolean wall, boolean capsule){
        this.wall = wall;
        this.capsule = capsule;
    }

    public boolean getWall(){
        return this.wall;
    }

    public boolean getCapsule() {
        return this.capsule;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public void setCapsule(boolean capsule) {
        this.capsule = capsule;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}