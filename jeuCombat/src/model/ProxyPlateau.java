package model;

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
        if(this.plateau.getArme(x,y,joueur) == null){ // Si il n'y a pas d'arme sur la case on return null
            return null;
        }
        if(this.plateau.getArme(x,y,joueur).isVisible()){ // Si l'arme est visible elle s'affiche
            return this.plateau.getArme(x,y,joueur);
        }
        if(this.plateau.getArme(x,y,joueur).getOwner() == joueur){ // Sinon l'arme n'est pas visible et on vérifie si elle appartient au joueur, si c'est le cas il voie son arme
            return this.plateau.getArme(x,y,joueur);
        }
        else{ // Sinon l'arme n'est pas visible et n'appartient pas au joueur donc il ne la voie pas
            return null;
        }
    }
    @Override
    public Case getCase(int x, int y){
        return this.plateau.getCase(x,y);
    }
}
