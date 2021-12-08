package model;

public class Case { //Représentation d'une case du plateau.
    //Une case peut avoir un mur, une pastille et une arme sur elle.
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