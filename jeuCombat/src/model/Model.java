package model;

public class Model {
    public int[Personnage] listeJoueur;
    public Plateau plateau;
    public Action action;
    public Personnage currentPlayer;

    public model(int[Personnage] listeJoueur, Plateau plateau, Action action, Personnage currentPlayer){
        this.listeJoueur = listeJoueur;
        this.plateau = plateau;
        this.action = action;
        this.currentPlayer = currentPlayer;
    }

    public boolean isOver(){
        if(currentPlayer.energie!=0){
            return false;
        }
        System.out.println("Partie terminé");
        return true;
    }


}
