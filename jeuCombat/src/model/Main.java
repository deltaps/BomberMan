package model;

import personnagesJeu.Personnage;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Personnage joueur = new Personnage("Jean");
        List<Personnage> liste = new ArrayList<>();
        joueur.setPosition(new int[]{1,1});
        liste.add(joueur);
        Plateau plateau = new Plateau(liste,true);
        Case[][] listeplateau = plateau.getPlateau();
        for(int x = 0; x < 3; x++){
            System.out.println("");
            for(int y = 0; y < 3; y++){
                if(joueur.getPosition()[0] == x && joueur.getPosition()[1] == y){
                    System.out.print("J ");
                }
                else if(listeplateau[x][y].getWall()){
                    System.out.print("+ ");
                }
                else{
                    System.out.print("0 ");
                }
            }
        }
        System.out.println("");
        System.out.println("___________________");
        Action action = new Action(plateau);
        action.deplacement(joueur,action.DROITE);
        action.deplacement(joueur,action.HAUT);

        for(int x = 0; x < 3; x++){
            System.out.println("");
            for(int y = 0; y < 3; y++){
                if(joueur.getPosition()[0] == x && joueur.getPosition()[1] == y){
                    System.out.print("J ");
                }
                else if(listeplateau[x][y].getWall()){
                    System.out.print("+ ");
                }
                else{
                    System.out.print("0 ");
                }
            }
        }
    }
}
