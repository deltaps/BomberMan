package model;

import personnagesJeu.Personnage;

import java.util.*;

public class ConcretePlateau implements Plateau {
    protected Case[][] plateau;
    protected List<Personnage> joueurs;
    protected int taille;

    public ConcretePlateau(List<Personnage> joueurs, int taille){ // Constructeur pour un plateau aléatoire
        if(taille < 7){
            throw new IllegalArgumentException("Votre taille doit être supérieur ou égale à 7");
        }
        this.joueurs = new ArrayList<>();
        this.joueurs.addAll(joueurs);
        this.plateau = new Case[taille][taille];
        this.taille = taille - 1;
        generePlateau(taille);
    }

    public ConcretePlateau(List<Personnage> joueurs, int taille, boolean test){ // Constructeur pour un plateau de test vide non aléatoire
        this.plateau = new Case[taille][taille];
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
        this.joueurs = new ArrayList<>();
        this.joueurs.addAll(joueurs);
    }

    public ConcretePlateau(List<Personnage> joueurs, boolean test){ // Constructeur pour un plateau de test non aléatoire de 3x3 avec deux murs dans les coins supérieur droit et inférieur gauche
        this.plateau = new Case[3][3];
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                this.plateau[x][y] = new Case(false,false);
            }
        }
        this.plateau[0][2].setWall(true);
        this.plateau[2][0].setWall(true);
        this.joueurs = new ArrayList<>();
        this.joueurs.addAll(joueurs);
    }
    public Case getCase(int x, int y) {
        return this.plateau[x][y];
    }

    public void generePlateau(int taille){
        // Placement des murs représentants les bordures du plateau
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
        //Placement des murs aléatoires.
        int nbMur = taille*3; // pour une taille égale à 7 on peut poser un maximum de taille*3 murs sans que la partie ne soit bloquée dans le pire des cas
        ArrayList<Integer> listeBlock = new ArrayList<>(); // une liste des cases sur lesquels un mur ne peux pas se poser au risque de bloquer certaine zone de la carte ou même bloquer la partie en séparant deux joueurs
        while(nbMur != 0) {
            int x = new Random().nextInt(taille-6);
            x += 3; // on tire un x aléatoire avec une distance aux bordures de 3 comme les murs sont des lignes de 3 murs consécutifs il faut laisser au moins une case libre pour contourner le mur et ne pas bloquer le jeu
            int y = new Random().nextInt(taille-6);
            y += 3; // pareil pour y
            int oui = new Random().nextInt(2); // on tire un aléatoire qui va dire si le mur est en position verticale ou horizontale
            if(oui == 1){ // position horizontale
                if(!(listeBlock.contains((taille*x)+y) || listeBlock.contains((taille*x)+y+1) || listeBlock.contains((taille*x)+y-1))){ // Si le mur ne se pose pas sur une des cases interdites
                    // on place le mur
                    this.plateau[x][y].setWall(true);
                    this.plateau[x][y + 1].setWall(true);
                    this.plateau[x][y - 1].setWall(true);
                    // on bloque trois cases dans la position opposée aux extrémités du mur
                    listeBlock.add(((x - 1) * taille) + (y - 2));
                    listeBlock.add(((x - 1) * taille) + (y + 2));
                    listeBlock.add((x * taille) + (y - 2));
                    listeBlock.add((x * taille) + (y + 2));
                    listeBlock.add(((x + 1) * taille) + (y - 2));
                    listeBlock.add(((x + 1) * taille) + (y + 2));
                    listeBlock.add((x * taille) + y);
                    listeBlock.add((x * taille) + (y + 1));
                    listeBlock.add((x * taille) + (y - 1));


                }
            }
            else{ // Sinon le mur est en position verticale
                if(!(listeBlock.contains((taille*x)+y) || listeBlock.contains((taille*(x+1))+y) || listeBlock.contains((taille*(x-1))+y))) { // on vérifie s'il ne se pose pas sur les cases interdites
                    // on pose le mur
                    this.plateau[x][y].setWall(true);
                    this.plateau[x - 1][y].setWall(true);
                    this.plateau[x + 1][y].setWall(true);
                    // on bloque trois cases dans la position opposée aux extrémités du mur
                    listeBlock.add(((x - 2) * taille) + (y - 1));
                    listeBlock.add(((x - 2) * taille) + y);
                    listeBlock.add(((x - 2) * taille) + (y + 1));
                    listeBlock.add(((x - 1) * taille) + (y - 1));
                    listeBlock.add(((x - 1) * taille) + (y + 1));
                    listeBlock.add((x * taille) + (y + 1));
                    listeBlock.add((x * taille) + (y - 1));
                    listeBlock.add(((x + 1) * taille) + (y - 1));
                    listeBlock.add(((x + 1) * taille) + (y + 1));
                    listeBlock.add(((x + 2) * taille) + (y - 1));
                    listeBlock.add(((x + 2) * taille) + y);
                    listeBlock.add(((x + 2) * taille) + (y + 1));
                    listeBlock.add((x * taille) + y);
                    listeBlock.add(((x - 1) * taille) + y);
                    listeBlock.add(((x + 1) * taille) + y);
                }
            }
            nbMur--;
        }
        // Placement des pastilles d'énergie
        for(int i = 0; i < taille / 2; i++){
            // on tire un x et un y sur le plateau qui n'est pas sur les bordures
            int x = new Random().nextInt(taille-2);
            int y = new Random().nextInt(taille-2);
            x++;
            y++;
            // tant qu'il y a un mur sur la position tirée on en retire une nouvelle
            while(this.plateau[x][y].getWall()){
                x = new Random().nextInt(taille-2);
                y = new Random().nextInt(taille-2);
                x++;
                y++;
            }
            // Si tout est bon on peut poser la pastille
            this.plateau[x][y].setPastille(true);
        }
    }

    public void ajoutMur(int x, int y){
        this.plateau[x][y].setWall(true);
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public int getTaille(){
        return this.taille+1;
    }

    public List<Personnage> getJoueurs() {
        return this.joueurs;
    }

    public Weapon getArme(int x,int y,Personnage joueur){
        return this.plateau[x][y].getWeapon();
    }

    public boolean getPastille(int x, int y){
        return this.plateau[x][y].getPastille();
    }
}
