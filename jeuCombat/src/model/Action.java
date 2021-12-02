package model;

import observer.AbstractListenableModel;
import observer.ModelListener;
import personnagesJeu.Personnage;

public class Action { //Class permettant au model de faire des actions
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
                joueur.setPosition(nouvellePosition); //On lui donne ça nouvelle position.
                joueur.setEnergie(joueur.getEnergie()-1);
            }
        }
    }

    public void poseMine(Personnage joueur,int[] direction, boolean visible) {
        int positionJoueurX = joueur.getPosition()[0];
        int positionJoueurY = joueur.getPosition()[1];
        if(joueur.getEnergie() > 1 || (!visible && joueur.getEnergie() > 2)){ //On vérifie si le joueur possède suffisamment d'énergie pour effectuer l'action.
            if(!this.concretePlateau.getCase(positionJoueurX + direction[0],positionJoueurY+direction[1]).getWall()){ //On vérifie s'il n'y a pas de mur à cet endroit.
                joueur.addEnergie(-1);
                if(!visible){
                    joueur.addEnergie(-1);
                }
                this.concretePlateau.getCase(positionJoueurX + direction[0],positionJoueurY + direction[1]).setWeapon(new LandMine(joueur, this.concretePlateau,visible)); //On place la mine sur le plateau.
            }
        }
    }

    public void poseBombe(Personnage joueur, int[] direction,boolean visible){
        int positionJoueurX = joueur.getPosition()[0];
        int positionJoueurY = joueur.getPosition()[1];
        if(joueur.getEnergie() > 1 || (!visible && joueur.getEnergie() > 2)){//On vérifie si le joueur possède suffisamment d'énergie pour effectuer l'action.
            if(!this.concretePlateau.getCase(positionJoueurX + direction[0],positionJoueurY+direction[1]).getWall()){//On vérifie s'il n'y a pas de mur à cet endroit.
                joueur.addEnergie(-1);
                if(!visible){
                    joueur.addEnergie(-1);
                }
                this.concretePlateau.getCase(positionJoueurX + direction[0],positionJoueurY + direction[1]).setWeapon(new Bomb(joueur, this.concretePlateau,visible,new int[]{positionJoueurX + direction[0],positionJoueurY + direction[1]}));//On place la bombe sur le plateau.
            }
        }
    }

    public void fire(Personnage joueur, int[] direction){ //Méthode pour tirer
        if(joueur.getMunition() > 0){ //On vérifie s'il possède assez de munitions
            joueur.setMunition(joueur.getMunition() - 1); //On lui retire une munition
            //On va boucler sur toutes les cases que doit parcourir le tir, et vérifié s'il y a un mur ou un joueur, auquel cas on sort de la boucle.
            boolean finBoucle = true;
            int[] positionFire = new int[]{joueur.getPosition()[0],joueur.getPosition()[1]}; //Position du tir au moment
            while(finBoucle){
                positionFire[0] += direction[0];
                positionFire[1] += direction[1];
                if(!this.concretePlateau.getPlateau()[positionFire[0]][positionFire[1]].getWall()){//On vérifie qu'il n'arrive pas à un mur.
                    for(Personnage joueurEnemie : this.concretePlateau.getJoueurs()){//On vérifie qu'il ne soit pas sur un joueur.
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
        if(joueur.getEnergie() > 1){ //Le bouclier est activé seulement si le joueur possède assez d'énergie.
            joueur.setBouclier(true);
            joueur.addEnergie(-1);
        }
    }

    public void neRienFaire(Personnage joueur){
        joueur.addEnergie(1);
    }
}
