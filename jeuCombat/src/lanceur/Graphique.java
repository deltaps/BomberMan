package lanceur;

import model.Model;
import personnagesJeu.Personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import vue.Vue;

public class Graphique {
    protected final int HAUT = 0;
    protected final int BAS = 1;
    protected final int GAUCHE = 2;
    protected final int DROITE = 3;
    protected final int HAUTDROITE = 4;
    protected final int HAUTGAUCHE = 5;
    protected final int BASDROITE = 6;
    protected final int BASGAUCHE = 7;
    public static void main(String[] args){

        Personnage joueur1 = new Personnage("Aurlien");
        Personnage joueur2 = new Personnage("Justice");
        List<Personnage> listePerso = new ArrayList<>();
        listePerso.add(joueur2);
        listePerso.add(joueur1);
        Model jeu = new Model(10,listePerso);

        Vue vue = new Vue(joueur1, jeu.getPlateau());

        jeu.toString();
        }
}
