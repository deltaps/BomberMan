package model;

import personnagesJeu.Personnage;

import java.util.List;

public interface Plateau {
    public void generePlateau(int taille);
    public void ajoutMur(int x, int y);
    public Case[][] getPlateau();
    public int getTaille();
    public List<Personnage> getJoueurs();
    public Weapon getArme(int x,int y,Personnage joueur);
}
