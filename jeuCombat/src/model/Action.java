package model;

import personnagesJeu.Personnage;

public class Action {
    protected final int HAUT = 0;
    protected final int BAS = 1;
    protected final int GAUCHE = 2;
    protected final int DROITE = 3;
    protected final int HAUTDROITE = 4;
    protected final int HAUTGAUCHE = 5;
    protected final int BASDROITE = 6;
    protected final int BASGAUCHE = 7;
    protected Plateau plateau;

    public Action(Plateau plateau) {
        this.plateau = plateau;
    }
    public void deplacement(Personnage joueur,int direction){
        switch(direction) {
            case GAUCHE:
                if(!(plateau.getPlateau()[joueur.getPosition()[0]][joueur.getPosition()[1]-1].getWall())){
                    int[] nouvellePosition = new int[]{joueur.getPosition()[0],joueur.getPosition()[1]-1};
                    joueur.setPosition(nouvellePosition);
                }
                break;
            case DROITE:
                if(!(plateau.getPlateau()[joueur.getPosition()[0]][joueur.getPosition()[1]+1].getWall())){
                    int[] nouvellePosition = new int[]{joueur.getPosition()[0],joueur.getPosition()[1]+1};
                    joueur.setPosition(nouvellePosition);
                }
                break;
            case HAUT:
                if(!(plateau.getPlateau()[joueur.getPosition()[0]-1][joueur.getPosition()[1]].getWall())){
                    int[] nouvellePosition = new int[]{joueur.getPosition()[0]-1,joueur.getPosition()[1]};
                    joueur.setPosition(nouvellePosition);
                }
                break;
            case BAS:
                if(!(plateau.getPlateau()[joueur.getPosition()[0]+1][joueur.getPosition()[1]].getWall())){
                    int[] nouvellePosition = new int[]{joueur.getPosition()[0]+1,joueur.getPosition()[1]};
                    joueur.setPosition(nouvellePosition);
                }
                break;
        }
    }

    public void poseMine(Personnage joueur,int direction) {
        //TODO faire passer le test
        int positionJoueurX = joueur.getPosition()[0];
        int positionJoueurY = joueur.getPosition()[1];
        switch (direction){
            case HAUT:
                if(this.plateau.getCase(positionJoueurX,positionJoueurY-1).getWeapon() != null){
                    joueur.addEnergie(-1);
                    this.plateau.getCase(positionJoueurX,positionJoueurY-1).setWeapon(new LandMine(joueur));
                }
               break;
            case HAUTDROITE:
                if(this.plateau.getCase(positionJoueurX+1,positionJoueurY-1).getWeapon() != null){
                    joueur.addEnergie(-1);
                    this.plateau.getCase(positionJoueurX+1,positionJoueurY-1).setWeapon(new LandMine(joueur));
                }
                break;
            case DROITE:
                if(this.plateau.getCase(positionJoueurX+1,positionJoueurY).getWeapon() != null){
                    joueur.addEnergie(-1);
                    this.plateau.getCase(positionJoueurX+1,positionJoueurY).setWeapon(new LandMine(joueur));
                }
                break;
            case BASDROITE:
                if(this.plateau.getCase(positionJoueurX+1,positionJoueurY+1).getWeapon() != null){
                    joueur.addEnergie(-1);
                    this.plateau.getCase(positionJoueurX+1,positionJoueurY+1).setWeapon(new LandMine(joueur));
                }
                break;
            case BAS:
                if(this.plateau.getCase(positionJoueurX,positionJoueurY+1).getWeapon() != null){
                    joueur.addEnergie(-1);
                    this.plateau.getCase(positionJoueurX,positionJoueurY+1).setWeapon(new LandMine(joueur));
                }
                break;
            case BASGAUCHE:
                if(this.plateau.getCase(positionJoueurX-1,positionJoueurY+1).getWeapon() != null){
                    joueur.addEnergie(-1);
                    this.plateau.getCase(positionJoueurX-1,positionJoueurY+1).setWeapon(new LandMine(joueur));
                }
                break;
            case GAUCHE:
                if(this.plateau.getCase(positionJoueurX-1,positionJoueurY).getWeapon() != null){
                    joueur.addEnergie(-1);
                    this.plateau.getCase(positionJoueurX-1,positionJoueurY).setWeapon(new LandMine(joueur));
                }
                break;
            case HAUTGAUCHE:
                if(this.plateau.getCase(positionJoueurX-1,positionJoueurY-1).getWeapon() != null){
                    joueur.addEnergie(-1);
                    this.plateau.getCase(positionJoueurX-1,positionJoueurY-1).setWeapon(new LandMine(joueur));
                }
                break;
        }
    }
    //TODO faire le reste des action (faire les tests avant!)
    public void poseBombe(){

    }

    public void fire() {

    }

    public void bouclier() {

    }

    public void neRienFaire() {

    }

}
