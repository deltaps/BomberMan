package model;

import personnagesJeu.Personnage;

import java.util.*;

public class Plateau {
    protected Case[][] plateau;
    protected List<Personnage> joueurs;
    protected int taille;

    public Plateau(List<Personnage> joueurs, int taille){
        this.joueurs = new ArrayList<>();
        this.joueurs.addAll(joueurs);
        this.plateau = new Case[taille][taille];
        this.taille = taille - 1;
        generePlateau(taille);
    }

    public Plateau(List<Personnage> joueurs, boolean test){
        this.plateau = new Case[3][3];
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                this.plateau[x][y] = new Case(false,false);
            }
        }
        this.plateau[0][2].setWall(true);
        this.plateau[2][0].setWall(true);
    }

    public Case getCase(int x, int y) {
        return this.plateau[x][y];
    }

    public void generePlateau(int taille){
        for(int x = 0; x < taille; x++){
            for(int y = 0; y < taille; y++){
                if(x == 0 | y == 0 | x == taille-1 | y == taille-1){
                    this.plateau[x][y] = new Case(true,false);
                }
                else{
                    this.plateau[x][y] = new Case(false,false);
                }
            }
        }
        int nbMur = (taille - 6) / 3;
        nbMur *= nbMur;
        nbMur += 1;
        Random random = new Random();
        while(nbMur != 0){
            int i = random.nextInt(taille-6);
            i += 3;
            int j = random.nextInt(taille-6);
            j += 3;
            int r = random.nextInt(2);
            if(r == 0){
                if(!this.plateau[i-2][j].getWall() & !this.plateau[i-2][j-1].getWall() & !this.plateau[i-2][j+1].getWall() & !this.plateau[i-1][j-1].getWall() &
                        !this.plateau[i-1][j+1].getWall() & !this.plateau[i][j].getWall() & !this.plateau[i][j-1].getWall() & !this.plateau[i][j+1].getWall()
                & !this.plateau[i+1][j].getWall() & !this.plateau[i+1][j-1].getWall() & !this.plateau[i+1][j+1].getWall() & !this.plateau[i+2][j].getWall()
                & !this.plateau[i+2][j-1].getWall() & !this.plateau[i+2][j+1].getWall()){
                    this.plateau[i][j].setWall(true);
                    this.plateau[i-1][j].setWall(true);
                    this.plateau[i+1][j].setWall(true);
                    nbMur--;
                }
            }
            else{
                if(!this.plateau[i-1][j-2].getWall() & !this.plateau[i][j-2].getWall() & !this.plateau[i+1][j-1].getWall() & !this.plateau[i-1][j-1].getWall() &
                        !this.plateau[i][j-1].getWall() & !this.plateau[i+1][j-1].getWall() & !this.plateau[i-1][j].getWall() & !this.plateau[i][j].getWall() & !this.plateau[i+1][j].getWall()
                & !this.plateau[i-1][j+1].getWall() & !this.plateau[i][j+1].getWall() & !this.plateau[i+1][j+1].getWall() & !this.plateau[i-1][j+2].getWall() & !this.plateau[i][j+2].getWall()
                & !this.plateau[i+1][j+2].getWall()){
                    this.plateau[i][j].setWall(true);
                    this.plateau[i][j-1].setWall(true);
                    this.plateau[i][j+1].setWall(true);
                    nbMur--;
                }
            }
        }
    }

    public void ajoutMur(int x, int y){
        this.plateau[x][y].setWall(true);
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public int getTaille(){
        return this.taille;
    }


}
