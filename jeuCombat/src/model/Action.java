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
        switch(direction) {
            case HAUT:
                if(!(plateau.getPlateau()[joueur.getPosition()[0]-1][joueur.getPosition()[1]].getWall())){
                    int[] nouvellePosition = new int[]{joueur.getPosition()[0],joueur.getPosition()[1]-1};
                    joueur.setPosition(nouvellePosition);
                }
                break;
            case BAS:
                if(!(plateau.getPlateau()[joueur.getPosition()[0]+1][joueur.getPosition()[1]].getWall())){
                    int[] nouvellePosition = new int[]{joueur.getPosition()[0],joueur.getPosition()[1]+1};
                    joueur.setPosition(nouvellePosition);
                }
                break;
            case GAUCHE:
                if(!(plateau.getPlateau()[joueur.getPosition()[0]][joueur.getPosition()[1]-1].getWall())){
                    int[] nouvellePosition = new int[]{joueur.getPosition()[0]-1,joueur.getPosition()[1]};
                    joueur.setPosition(nouvellePosition);
                }
                break;
            case DROITE:
                if(!(plateau.getPlateau()[joueur.getPosition()[0]][joueur.getPosition()[1]+1].getWall())){
                    int[] nouvellePosition = new int[]{joueur.getPosition()[0]+1,joueur.getPosition()[1]};
                    joueur.setPosition(nouvellePosition);
                }
                break;
        }
    }

    public void poseMine(Personnage joueur, int[] position) {
        /*
    }
        int positionX = position[0];
        int positionY = position[1];
        int[] positionJoueur = joueur.getPosition();
        int positionJoueurX = positionJoueur[0];
        int positionJoueurY = positionJoueur[1];


        if(positionJoueur == position
        || (positionX+1 == positionJoueurX && positionY+1 == positionJoueurY)
        || (positionX+1 == positionJoueurX && positionY-1 == positionJoueurY)
        || (positionX-1 == positionJoueurX && positionY+1 == positionJoueurY)
        || (positionX-1 == positionJoueurX && positionY-1 == positionJoueurY)) {
            joueur.addEnergie(-1);
            this.plateau.getCase(positionX, positionY).setWeapon(new LandMine(joueur, position, this.plateau));
        */
    }

    public void poseBombe(){

    }

    public void fire() {

    }

    public void bouclier() {

    }

    public void neRienFaire() {

    }

}
