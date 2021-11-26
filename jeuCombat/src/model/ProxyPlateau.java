package model;

import observer.AbstractListenableModel;
import personnagesJeu.Personnage;

import java.util.List;

public class ProxyPlateau implements Plateau {
    protected ConcretePlateau plateau;
    public ProxyPlateau(ConcretePlateau plateau){
        this.plateau = plateau;
    }

    @Override
    public void generePlateau(int taille) {
        this.plateau.generePlateau(taille);
    }

    @Override
    public void ajoutMur(int x, int y) {
        this.plateau.ajoutMur(x,y);
    }

    @Override
    public Case[][] getPlateau() {
        return this.plateau.getPlateau();
    }

    @Override
    public int getTaille() {
        return this.plateau.getTaille();
    }

    @Override
    public List<Personnage> getJoueurs() {
        return this.plateau.getJoueurs();
    }

    @Override
    public Weapon getArme(int x, int y,Personnage joueur){
        if(this.plateau.getArme(x,y,joueur) == null){
            return null;
        }
        if(this.plateau.getArme(x,y,joueur).isVisible()){
            return this.plateau.getArme(x,y,joueur);
        }
        if(this.plateau.getArme(x,y,joueur).getOwner() == joueur){
            return this.plateau.getArme(x,y,joueur);
        }
        else{
            return null;
        }
    }
    @Override
    public Case getCase(int x, int y){
        return this.plateau.getCase(x,y);
    }
}
