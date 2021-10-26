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
    protected ConcretePlateau concretePlateau;

    public Action(ConcretePlateau concretePlateau) {
        this.concretePlateau = concretePlateau;
    }
    public void deplacement(Personnage joueur,int direction){
        if(joueur.getEnergie() > 1){
            switch(direction) {
                case GAUCHE:
                    if(!(concretePlateau.getPlateau()[joueur.getPosition()[0]][joueur.getPosition()[1]-1].getWall())){
                        int[] nouvellePosition = new int[]{joueur.getPosition()[0],joueur.getPosition()[1]-1};
                        joueur.setPosition(nouvellePosition);
                        joueur.setEnergie(joueur.getEnergie()-1);
                    }
                    break;
                case DROITE:
                    if(!(concretePlateau.getPlateau()[joueur.getPosition()[0]][joueur.getPosition()[1]+1].getWall())){
                        int[] nouvellePosition = new int[]{joueur.getPosition()[0],joueur.getPosition()[1]+1};
                        joueur.setPosition(nouvellePosition);
                        joueur.setEnergie(joueur.getEnergie()-1);
                    }
                    break;
                case HAUT:
                    if(!(concretePlateau.getPlateau()[joueur.getPosition()[0]-1][joueur.getPosition()[1]].getWall())){
                        int[] nouvellePosition = new int[]{joueur.getPosition()[0]-1,joueur.getPosition()[1]};
                        joueur.setPosition(nouvellePosition);
                        joueur.setEnergie(joueur.getEnergie()-1);
                    }
                    break;
                case BAS:
                    if(!(concretePlateau.getPlateau()[joueur.getPosition()[0]+1][joueur.getPosition()[1]].getWall())){
                        int[] nouvellePosition = new int[]{joueur.getPosition()[0]+1,joueur.getPosition()[1]};
                        joueur.setPosition(nouvellePosition);
                        joueur.setEnergie(joueur.getEnergie()-1);
                    }
                    break;
            }
        }
    }

    public void poseMine(Personnage joueur,int direction, boolean visible) {
        int positionJoueurX = joueur.getPosition()[0];
        int positionJoueurY = joueur.getPosition()[1];
        if(joueur.getEnergie() > 1){
            switch (direction){
                case HAUT:
                    if(positionJoueurY != 0){
                        if(!this.concretePlateau.getCase(positionJoueurX,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX,positionJoueurY-1).setWeapon(new LandMine(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case HAUTDROITE:
                    if(positionJoueurX != this.concretePlateau.getTaille() && positionJoueurY != 0){
                        if(!this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY-1).setWeapon(new LandMine(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case DROITE:
                    if(positionJoueurX != this.concretePlateau.getTaille()){
                        if(!this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY).setWeapon(new LandMine(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case BASDROITE:
                    if(positionJoueurX != this.concretePlateau.getTaille() && positionJoueurY != this.concretePlateau.getTaille()){
                        if(!this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY+1).setWeapon(new LandMine(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case BAS:
                    if(positionJoueurY != this.concretePlateau.getTaille()){
                        if(!this.concretePlateau.getCase(positionJoueurX,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX,positionJoueurY+1).setWeapon(new LandMine(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case BASGAUCHE:
                    if(positionJoueurX != 0 && positionJoueurY != this.concretePlateau.getTaille()){
                        if(!this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY+1).setWeapon(new LandMine(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case GAUCHE:
                    if(positionJoueurX != 0){
                        if(!this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY).setWeapon(new LandMine(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case HAUTGAUCHE:
                    if(positionJoueurX != 0 && positionJoueurY != 0){
                        if(!this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY-1).setWeapon(new LandMine(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
            }
        }
    }

    public void poseBombe(Personnage joueur, int direction,boolean visible){
        int positionJoueurX = joueur.getPosition()[0];
        int positionJoueurY = joueur.getPosition()[1];
        if(joueur.getEnergie() > 1){
            switch (direction){
                case HAUT:
                    if(positionJoueurY != 0){
                        if(!this.concretePlateau.getCase(positionJoueurX,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX,positionJoueurY-1).setWeapon(new Bomb(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case HAUTDROITE:
                    if(positionJoueurX != this.concretePlateau.getTaille() && positionJoueurY != 0){
                        if(!this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY-1).setWeapon(new Bomb(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case DROITE:
                    if(positionJoueurX != this.concretePlateau.getTaille()){
                        if(!this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY).setWeapon(new Bomb(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case BASDROITE:
                    if(positionJoueurX != this.concretePlateau.getTaille() && positionJoueurY != this.concretePlateau.getTaille()){
                        if(!this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX+1,positionJoueurY+1).setWeapon(new Bomb(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case BAS:
                    if(positionJoueurY != this.concretePlateau.getTaille()){
                        if(!this.concretePlateau.getCase(positionJoueurX,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX,positionJoueurY+1).setWeapon(new Bomb(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case BASGAUCHE:
                    if(positionJoueurX != 0 && positionJoueurY != this.concretePlateau.getTaille()){
                        if(!this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY+1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY+1).setWeapon(new Bomb(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case GAUCHE:
                    if(positionJoueurX != 0){
                        if(!this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY).setWeapon(new Bomb(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
                case HAUTGAUCHE:
                    if(positionJoueurX != 0 && positionJoueurY != 0){
                        if(!this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY-1).getWall()){
                            joueur.addEnergie(-1);
                            if(visible){
                                joueur.addEnergie(-1);
                            }
                            this.concretePlateau.getCase(positionJoueurX-1,positionJoueurY-1).setWeapon(new Bomb(joueur, this.concretePlateau,visible));
                        }
                    }
                    break;
            }
        }
    }
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
