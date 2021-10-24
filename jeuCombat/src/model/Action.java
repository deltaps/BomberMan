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
        if(joueur.getEnergie() >= 1){
            switch(direction) {
                case GAUCHE:
                    if(!(plateau.getPlateau()[joueur.getPosition()[0]][joueur.getPosition()[1]-1].getWall())){
                        int[] nouvellePosition = new int[]{joueur.getPosition()[0],joueur.getPosition()[1]-1};
                        joueur.setPosition(nouvellePosition);
                        joueur.setEnergie(joueur.getEnergie()-1);
                    }
                    break;
                case DROITE:
                    if(!(plateau.getPlateau()[joueur.getPosition()[0]][joueur.getPosition()[1]+1].getWall())){
                        int[] nouvellePosition = new int[]{joueur.getPosition()[0],joueur.getPosition()[1]+1};
                        joueur.setPosition(nouvellePosition);
                        joueur.setEnergie(joueur.getEnergie()-1);
                    }
                    break;
                case HAUT:
                    if(!(plateau.getPlateau()[joueur.getPosition()[0]-1][joueur.getPosition()[1]].getWall())){
                        int[] nouvellePosition = new int[]{joueur.getPosition()[0]-1,joueur.getPosition()[1]};
                        joueur.setPosition(nouvellePosition);
                        joueur.setEnergie(joueur.getEnergie()-1);
                    }
                    break;
                case BAS:
                    if(!(plateau.getPlateau()[joueur.getPosition()[0]+1][joueur.getPosition()[1]].getWall())){
                        int[] nouvellePosition = new int[]{joueur.getPosition()[0]+1,joueur.getPosition()[1]};
                        joueur.setPosition(nouvellePosition);
                        joueur.setEnergie(joueur.getEnergie()-1);
                    }
                    break;
            }
        }
    }

    public void poseMine(Personnage joueur,int direction) {
        int positionJoueurX = joueur.getPosition()[0];
        int positionJoueurY = joueur.getPosition()[1];
        if(joueur.getEnergie() >= 1){
            switch (direction){
                case HAUT:
                    if(positionJoueurY != 0){
                        if(!this.plateau.getCase(positionJoueurX,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX,positionJoueurY-1).setWeapon(new LandMine(joueur));
                        }
                    }
                    break;
                case HAUTDROITE:
                    if(positionJoueurX != this.plateau.getTaille() && positionJoueurY != 0){
                        if(!this.plateau.getCase(positionJoueurX+1,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX+1,positionJoueurY-1).setWeapon(new LandMine(joueur));
                        }
                    }
                    break;
                case DROITE:
                    if(positionJoueurX != this.plateau.getTaille()){
                        if(!this.plateau.getCase(positionJoueurX+1,positionJoueurY).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX+1,positionJoueurY).setWeapon(new LandMine(joueur));
                        }
                    }
                    break;
                case BASDROITE:
                    if(positionJoueurX != this.plateau.getTaille() && positionJoueurY != this.plateau.getTaille()){
                        if(!this.plateau.getCase(positionJoueurX+1,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX+1,positionJoueurY+1).setWeapon(new LandMine(joueur));
                        }
                    }
                    break;
                case BAS:
                    if(positionJoueurY != this.plateau.getTaille()){
                        if(!this.plateau.getCase(positionJoueurX,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX,positionJoueurY+1).setWeapon(new LandMine(joueur));
                        }
                    }
                    break;
                case BASGAUCHE:
                    if(positionJoueurX != 0 && positionJoueurY != this.plateau.getTaille()){
                        if(!this.plateau.getCase(positionJoueurX-1,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX-1,positionJoueurY+1).setWeapon(new LandMine(joueur));
                        }
                    }
                    break;
                case GAUCHE:
                    if(positionJoueurX != 0){
                        if(!this.plateau.getCase(positionJoueurX-1,positionJoueurY).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX-1,positionJoueurY).setWeapon(new LandMine(joueur));
                        }
                    }
                    break;
                case HAUTGAUCHE:
                    if(positionJoueurX != 0 && positionJoueurY != 0){
                        if(!this.plateau.getCase(positionJoueurX-1,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX-1,positionJoueurY-1).setWeapon(new LandMine(joueur));
                        }
                    }
                    break;
            }
        }
    }

    public void poseBombe(Personnage joueur, int direction){
        int positionJoueurX = joueur.getPosition()[0];
        int positionJoueurY = joueur.getPosition()[1];
        if(joueur.getEnergie() >= 1){
            switch (direction){
                case HAUT:
                    if(positionJoueurY != 0){
                        if(!this.plateau.getCase(positionJoueurX,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX,positionJoueurY-1).setWeapon(new Bomb(joueur));
                        }
                    }
                    break;
                case HAUTDROITE:
                    if(positionJoueurX != this.plateau.getTaille() && positionJoueurY != 0){
                        if(!this.plateau.getCase(positionJoueurX+1,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX+1,positionJoueurY-1).setWeapon(new Bomb(joueur));
                        }
                    }
                    break;
                case DROITE:
                    if(positionJoueurX != this.plateau.getTaille()){
                        if(!this.plateau.getCase(positionJoueurX+1,positionJoueurY).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX+1,positionJoueurY).setWeapon(new Bomb(joueur));
                        }
                    }
                    break;
                case BASDROITE:
                    if(positionJoueurX != this.plateau.getTaille() && positionJoueurY != this.plateau.getTaille()){
                        if(!this.plateau.getCase(positionJoueurX+1,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX+1,positionJoueurY+1).setWeapon(new Bomb(joueur));
                        }
                    }
                    break;
                case BAS:
                    if(positionJoueurY != this.plateau.getTaille()){
                        if(!this.plateau.getCase(positionJoueurX,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX,positionJoueurY+1).setWeapon(new Bomb(joueur));
                        }
                    }
                    break;
                case BASGAUCHE:
                    if(positionJoueurX != 0 && positionJoueurY != this.plateau.getTaille()){
                        if(!this.plateau.getCase(positionJoueurX-1,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX-1,positionJoueurY+1).setWeapon(new Bomb(joueur));
                        }
                    }
                    break;
                case GAUCHE:
                    if(positionJoueurX != 0){
                        if(!this.plateau.getCase(positionJoueurX-1,positionJoueurY).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX-1,positionJoueurY).setWeapon(new Bomb(joueur));
                        }
                    }
                    break;
                case HAUTGAUCHE:
                    if(positionJoueurX != 0 && positionJoueurY != 0){
                        if(!this.plateau.getCase(positionJoueurX-1,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            this.plateau.getCase(positionJoueurX-1,positionJoueurY-1).setWeapon(new Bomb(joueur));
                        }
                    }
                    break;
            }
        }
    }
    //TODO faire le reste des action (faire les tests avant!)
    public void fire(Personnage joueur,int direction){
        if(joueur.getMunition() > 0){
            joueur.setMunition(joueur.getMunition() - 1);
            switch (direction){
                case HAUT:
                    fireBis(joueur,new int[]{0,-1});
                    break;
                case BAS:
                    fireBis(joueur,new int[]{0,1});
                    break;
                case GAUCHE:
                    fireBis(joueur,new int[]{-1,0});
                    break;
                case DROITE:
                    fireBis(joueur,new int[]{1,0});
            }
        }
    }
    public void fireBis(Personnage joueur, int[] direction){
        boolean finBoucle = true;
        int[] positionFire = new int[]{joueur.getPosition()[0],joueur.getPosition()[1]};
        while(finBoucle){
            positionFire[0] += direction[0];
            positionFire[1] += direction[1];
            if(!this.plateau.getPlateau()[positionFire[0]][positionFire[1]].getWall()){
                for(Personnage joueurEnemie : this.plateau.getJoueurs()){
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
