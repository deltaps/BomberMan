package model;

import personnagesJeu.Personnage;

public class Bomb implements Weapon{

    protected Personnage owner;
    protected int compteARebourt;
    protected ConcretePlateau plateau;
    protected boolean visible;
    protected int[] position;

    public Bomb(Personnage owner, ConcretePlateau plateau,boolean visible,int[] position) {
        this.owner = owner;
        this.compteARebourt=3;
        this.plateau = plateau;
        this.visible = visible;
        this.position = position;
    }

    @Override
    public Personnage getOwner(){
        return this.owner;
    }
    @Override
    public boolean isVisible(){
        return this.visible;
    }
    @Override
    public void detonation() {
        for(Personnage joueur : this.plateau.getJoueurs()) {
            int joueurX = joueur.getPosition()[0];
            int joueurY = joueur.getPosition()[1];

            if(joueurX == this.position[0] && joueurY == this.position[1]) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == this.position[0] && joueurY == this.position[1] + 1) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == this.position[0] && joueurY == this.position[1] - 1) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == this.position[0] + 1 && joueurY == this.position[1]) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == this.position[0] - 1 && joueurY == this.position[1]) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == this.position[0] + 1 && joueurY == this.position[1] + 1) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == this.position[0] + 1 && joueurY == this.position[1] - 1) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == this.position[0] - 1 && joueurY == this.position[1] + 1) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == this.position[0] - 1 && joueurY == this.position[1] - 1) {
                joueur.addEnergie(-1);
            }
        }
    }

    public void tictac(){
        this.compteARebourt--;
        if(this.compteARebourt == 0){
            for(Personnage joueur : this.plateau.getJoueurs()){
                int joueurX = joueur.getPosition()[0];
                int joueurY = joueur.getPosition()[1];
                if(this == this.plateau.getCase(joueurX, joueurY).getWeapon()) {
                    detonation();
                    break;
                }
            }
        }
    }
    public int getCompteARebourt(){
        return this.compteARebourt;
    }
}
