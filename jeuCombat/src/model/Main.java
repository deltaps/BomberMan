package model;

import personnagesJeu.Personnage;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Personnage joueur1 = new Personnage("Aurlien");
        Personnage joueur2 = new Personnage("Justice");
        joueur2.setPosition(new int[]{1,1});
        joueur1.setPosition(new int[]{3,3});
        List<Personnage> listePerso = new ArrayList<>();
        listePerso.add(joueur2);
        listePerso.add(joueur1);
        Model jeu = new Model(8,listePerso);
        jeu.action(10, jeu.BAS,false);
        System.out.println(joueur2.getEnergie());
        System.out.println(jeu);
        jeu.changePlayer();
        System.out.println(jeu);
        /*
        Personnage joueur = new Personnage("Jean");
        Personnage joueur2 = new Personnage("Pierre");
        List<Personnage> liste = new ArrayList<>();
        joueur.setPosition(new int[]{1,1});
        joueur2.setPosition(new int[]{2,1});
        liste.add(joueur);
        liste.add(joueur2);
        ConcretePlateau plateau = new ConcretePlateau(liste,7);
        Plateau plateauj1 = new ProxyPlateau(plateau);
        Action action = new Action(plateau);
        action.poseBombe(joueur, action.DROITE);
        for(Personnage joueurActuelle : liste){
            System.out.println("Affichage du plateau du joueur" + joueurActuelle.getName());
            for(int x = 0; x < 7; x++){
                System.out.println("");
                for(int y = 0; y < 7; y++){
                    if(joueurActuelle.getPosition()[0] == x && joueurActuelle.getPosition()[1] == y){
                        System.out.print("J ");
                    }
                    else if(plateauj1.getPlateau()[x][y].getWall()){
                        System.out.print("+ ");
                    }
                    else if(plateauj1.getArme(y,x,joueurActuelle) != null){
                        System.out.print("A ");
                    }
                    else{
                        System.out.print("0 ");
                    }
                }
            }
        }

         */
    }
}
