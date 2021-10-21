package model;

import personnagesJeu.Personnage;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Personnage joueur = new Personnage("Jean");
        List<Personnage> liste = new ArrayList<>();
        liste.add(joueur);
        Plateau plateau = new Plateau(liste,10);
        Case[][] listeplateau = plateau.getPlateau();
        for(int x = 0; x < 10; x++){
            System.out.println("");
            for(int y = 0; y < 10; y++){
                if(listeplateau[x][y].getWall()){
                    System.out.print("+ ");
                }
                else{
                    System.out.print("0 ");
                }
            }
        }
    }
}
