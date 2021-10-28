package model;

import personnagesJeu.Personnage;

public class LandMine implements Weapon{

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
    public void detonation() {
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
