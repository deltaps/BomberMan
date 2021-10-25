package model;

import personnagesJeu.Personnage;

public class Bomb implements Weapon{

    protected Personnage owner;
    protected int compteARebourt;
    protected ConcretePlateau plateau;

    public Bomb(Personnage owner, ConcretePlateau plateau) {
        this.owner = owner;
        this.compteARebourt=3;
        this.plateau = plateau;
    }

    @Override
    public Personnage getOwner(){
        return this.owner;
    }

    @Override
    public void detonation() {
        this.compteARebourt--;
        if(compteARebourt == 0){
            for(int x = 0; x < this.plateau.getTaille(); x++) {
                for (int y = 0; y < this.plateau.getTaille(); y++) {
                    if(this == this.plateau.getCase(x,y).getWeapon()) {
                        boomboom(x, y);
                        break;
                    }
                }
            }
        }
        for(Personnage joueur : this.plateau.getJoueurs()) {
            int joueurX = joueur.getPosition()[0];
            int joueurY = joueur.getPosition()[1];
            if(this == this.plateau.getCase(joueurX, joueurY).getWeapon()) {
                boomboom(joueurX, joueurY);
                break;
            }
        }
    }

    public void boomboom(int bombX, int bombY) {
        for(Personnage joueur : this.plateau.getJoueurs()) {
            int joueurX = joueur.getPosition()[0];
            int joueurY = joueur.getPosition()[1];

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
