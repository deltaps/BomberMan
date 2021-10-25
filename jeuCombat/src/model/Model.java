package model;

import personnagesJeu.Personnage;

import java.util.List;

public class Model {
    protected final int HAUT = 0;
    protected final int BAS = 1;
    protected final int GAUCHE = 2;
    protected final int DROITE = 3;
    protected final int HAUTDROITE = 4;
    protected final int HAUTGAUCHE = 5;
    protected final int BASDROITE = 6;
    protected final int BASGAUCHE = 7;
    protected final int DEPLACEMENT = 8;
    protected final int MINE = 9;
    protected final int BOMBE = 10;
    protected final int TIR = 11;
    protected final int BOUCLIER = 12;
    protected final int RIENFAIRE = 13;
    protected ConcretePlateau concretePlateau;
    protected Action action;
    protected Personnage currentPlayer;
    protected List<Personnage> listeJoueurs;

    public Model(int taillePlateau,List<Personnage> listeJoueurs){
        this.listeJoueurs = listeJoueurs;
        this.concretePlateau = new ConcretePlateau(this.listeJoueurs,taillePlateau);
        this.action = new Action(this.concretePlateau);
        this.currentPlayer = this.listeJoueurs.get(0);
    }

    public ConcretePlateau getPlateau() {
        return concretePlateau;
    }

    public void setPlateau(ConcretePlateau concretePlateau) {
        this.concretePlateau = concretePlateau;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Personnage getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Personnage currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<Personnage> getListeJoueurs() {
        return listeJoueurs;
    }

    public void setListeJoueurs(List<Personnage> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    public boolean isOver(){
        return true;
    }

    public void changePlayer(){
        for(int n = 0; n < this.listeJoueurs.size() ; n++){
            if(this.listeJoueurs.get(n) == this.currentPlayer){
                System.out.println(this.listeJoueurs.get(n));
                System.out.println(this.getCurrentPlayer());
                if(n == this.listeJoueurs.size()-1){
                    this.currentPlayer = this.listeJoueurs.get(0);
                    break;
                }
                else{
                    this.currentPlayer = this.listeJoueurs.get(n+1);
                    break;
                }
            }
        }
        System.out.println("-----------------------");
    }

    public void action(int action, int direction){
        //TODO, est-ce que c'est la méthode action du model qui vérifie si l'énergie est suffisante?
        //si oui, qu'est-ce que l'on retourne?
        switch (action){
            case DEPLACEMENT:
                this.action.deplacement(this.currentPlayer,direction);
                break;
            case MINE:
                this.action.poseMine(this.currentPlayer,direction);
                break;
            case BOMBE:
                this.action.poseBombe(this.currentPlayer,direction);
                break;
            case TIR:
                this.action.fire(this.currentPlayer,direction);
                break;
            case BOUCLIER:
                this.action.bouclier(this.currentPlayer);
                break;
            case RIENFAIRE:
                this.action.neRienFaire(this.currentPlayer);
                break;
        }
    }

}
