package model;

import personnagesJeu.Personnage;

public class Bomb implements Weapon{ //Notre class pour représenter une bombe.

    protected Personnage owner;
    protected int compteARebourt;
    protected ConcretePlateau plateau;
    protected boolean visible;
    protected int[] position;

    public Bomb(Personnage owner, ConcretePlateau plateau,boolean visible,int[] position) {
        this.owner = owner;
        this.compteARebourt=3; // Le compte à rebours est initialisé à trois tours complets de tous les joueurs
        this.plateau = plateau;
        this.visible = visible;
        this.position = position;
    }

    @Override
    public void detonation() { //Méthode qui "déclenche" la bombe
        for(Personnage joueur : this.plateau.getJoueurs()) { //On vérifie pour chaque joueur s'ils peuvent recevoir des dégâts.
            int joueurX = joueur.getPosition()[0];
            int joueurY = joueur.getPosition()[1];
            if(joueur.getBouclier()){
                continue; //S'il a son bouclier d'activer, il ne recevra pas de dégât.
            }
            //On vérifie si le joueur est a porté de la bombe.
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

    public void tictac(){ //Méthode permettant d'incrémenter le compte à rebours, et de vérifier s'il est fini, auquel cas on fait exploser la bombe.
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
    @Override
    public Personnage getOwner(){
        return this.owner;
    }
    @Override
    public boolean isVisible(){
        return this.visible;
    }
}
