package model;

import observer.AbstractListenableModel;
import observer.ModelListener;
import personnagesJeu.Personnage;

import java.util.List;

public interface Plateau extends ModelListener {
    public void generePlateau(int taille);
    public void ajoutMur(int x, int y);
    public Case[][] getPlateau();
    public int getTaille();
    public List<Personnage> getJoueurs();
    public Weapon getArme(int x,int y,Personnage joueur);
    public Case getCase(int x,int y);
}
