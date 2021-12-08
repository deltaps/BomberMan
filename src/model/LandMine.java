package model;

import personnagesJeu.Personnage;

public class LandMine implements Weapon{//Notre class pour représenter une mine.

    protected Personnage owner;
    protected ConcretePlateau plateau;
    protected boolean visible;

    public LandMine(Personnage owner, ConcretePlateau plateau,boolean visible) {
        this.owner = owner;
        this.visible = visible;
        this.plateau = plateau;
    }

    @Override
    public Personnage getOwner() {
        return this.owner;
    }

    @Override
    public boolean isVisible(){
        return this.visible;
    }

    @Override
    public void detonation() { //Méthode qui vérifie si la mine doit être déclenchée.
        for(Personnage joueur : this.plateau.getJoueurs()) { //On regarde si un joueur est sur la mine.
            int joueurX = joueur.getPosition()[0];
            int joueurY = joueur.getPosition()[1];
            if(this == this.plateau.getCase(joueurX, joueurY).getWeapon()) {
                boomboom(joueurX, joueurY);//Si le joueur est sur la mine on déclenche la mine.
                break;
            }
        }
    }

    public void boomboom(int bombX, int bombY) { //Méthode qui déclenche la mine.
        for(Personnage joueur : this.plateau.getJoueurs()) { //On vérifie pour chaque joueur s'ils peuvent recevoir des dégâts.
            int joueurX = joueur.getPosition()[0];
            int joueurY = joueur.getPosition()[1];
            if(joueur.getBouclier()){
                continue;//S'il a son bouclier d'activer, il ne recevra pas de dégât.
            }
            //On vérifie si le joueur est a porté de la bombe.
            if(joueurX == bombX && joueurY == bombY) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == bombX && joueurY == bombY + 1) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == bombX && joueurY == bombY - 1) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == bombX + 1 && joueurY == bombY) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == bombX - 1 && joueurY == bombY) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == bombX + 1 && joueurY == bombY + 1) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == bombX + 1 && joueurY == bombY - 1) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == bombX - 1 && joueurY == bombY + 1) {
                joueur.addEnergie(-1);
            }
            else if(joueurX == bombX - 1 && joueurY == bombY - 1) {
                joueur.addEnergie(-1);
            }
        }
    }
}
