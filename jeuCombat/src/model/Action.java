package model;

import observer.AbstractListenableModel;
import observer.ModelListener;
import personnagesJeu.Personnage;

public class Action {
    protected ConcretePlateau concretePlateau;

    public Action(ConcretePlateau concretePlateau) {
        this.concretePlateau = concretePlateau;
    }

    public void deplacement(Personnage joueur,int[] direction){
        Boolean pasSurjoueur = true;
        for(Personnage joueurAdv : this.concretePlateau.getJoueurs()){
            if(joueurAdv != joueur){
                if(joueur.getPosition()[0] + direction[0] == joueurAdv.getPosition()[0] && joueur.getPosition()[1] + direction[1] == joueurAdv.getPosition()[1]){
                    pasSurjoueur = false;
                }
            }
        }
        if(joueur.getEnergie() > 1 && pasSurjoueur){
            if(!(concretePlateau.getPlateau()[joueur.getPosition()[0]+direction[0]][joueur.getPosition()[1]+direction[1]].getWall())){
                int[] nouvellePosition = new int[]{joueur.getPosition()[0]+direction[0],joueur.getPosition()[1]+direction[1]};
                joueur.setPosition(nouvellePosition);
                joueur.setEnergie(joueur.getEnergie()-1);
            }
        }
    }

    public void poseMine(Personnage joueur,int[] direction, boolean visible) {
        int positionJoueurX = joueur.getPosition()[0];
        int positionJoueurY = joueur.getPosition()[1];
        if(joueur.getEnergie() > 1){
            if(!this.concretePlateau.getCase(positionJoueurX + direction[0],positionJoueurY+direction[1]).getWall()){
                joueur.addEnergie(-1);
                if(!visible){
                    joueur.addEnergie(-1);
                }
                this.concretePlateau.getCase(positionJoueurX + direction[0],positionJoueurY + direction[1]).setWeapon(new LandMine(joueur, this.concretePlateau,visible));
            }
        }
    }

    public void poseBombe(Personnage joueur, int[] direction,boolean visible){
        int positionJoueurX = joueur.getPosition()[0];
        int positionJoueurY = joueur.getPosition()[1];
        if(joueur.getEnergie() > 1){
            if(!this.concretePlateau.getCase(positionJoueurX + direction[0],positionJoueurY+direction[1]).getWall()){
                joueur.addEnergie(-1);
                if(!visible){
                    joueur.addEnergie(-1);
                }
                this.concretePlateau.getCase(positionJoueurX + direction[0],positionJoueurY + direction[1]).setWeapon(new Bomb(joueur, this.concretePlateau,visible,new int[]{positionJoueurX + direction[0],positionJoueurY + direction[1]}));
            }
        }
    }

    public void fire(Personnage joueur, int[] direction){
        if(joueur.getMunition() > 0){
            joueur.setMunition(joueur.getMunition() - 1);
            boolean finBoucle = true;
            int[] positionFire = new int[]{joueur.getPosition()[0],joueur.getPosition()[1]};
            while(finBoucle){
                positionFire[0] += direction[0];
                positionFire[1] += direction[1];
                if(!this.concretePlateau.getPlateau()[positionFire[0]][positionFire[1]].getWall()){
                    for(Personnage joueurEnemie : this.concretePlateau.getJoueurs()){
                        if(joueurEnemie.getPosition()[0] == positionFire[0] && joueurEnemie.getPosition()[1] == positionFire[1]){
                            if(!joueurEnemie.getBouclier()){
                                joueurEnemie.addEnergie(-2);
                                finBoucle = false;
                            }
                            else{
                                finBoucle = false;
                            }
                        }
                    }
                }
                else{
                    finBoucle = false;
                }
            }
        }
    }

    public void bouclier(Personnage joueur){
        if(joueur.getEnergie() > 1){
            joueur.setBouclier(true);
            joueur.addEnergie(-1);
        }
    }

    public void neRienFaire(Personnage joueur){
        joueur.addEnergie(1);
    }
}
