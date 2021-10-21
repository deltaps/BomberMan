package model;

import personnagesJeu.Personnage;

public class Action {
    protected final int HAUT = 0;
    protected final int BAS = 1;
    protected final int GAUCHE = 2;
    protected final int DROITE = 3;
    protected Plateau plateau;

    public Action(Plateau plateau) {
        this.plateau = plateau;
    }

    public void deplacement(Personnage joueur,int direction){
        //if()
    }

    public void poseMine(Personnage joueur,Case[][] position) {
        //position.getWeapon = LandMine;
        joueur.addEnergie(-1);
    }

    public void poseBombe() {

    }

    public void fire() {

    }

    public void bouclier() {

    }

    public void neRienFaire() {

    }

}
