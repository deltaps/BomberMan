package model;

import personnagesJeu.Personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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
        System.out.println(joueur1.getMunition());
        List<Personnage> listePerso = new ArrayList<>();
        listePerso.add(joueur2);
        listePerso.add(joueur1);
        Model jeu = new Model(10,listePerso);
        jeu.toString();
        //Scanner scanner = new Scanner(System.in);
        /*
        while(!jeu.isOver()){
            System.out.println("Joueur : " + jeu.getCurrentPlayer());
            System.out.println(jeu);
            System.out.println("Quelle action faire?");
            int action = scanner.nextInt();
            System.out.println("Quelle direction?");
            /*
            int direction = scanner.nextInt();
            System.out.println("Visible ou non?");
            int visible = scanner.nextInt();
            if(visible == 0){
                jeu.action(action,direction,true);
                jeu.changePlayer();
            }
            else{
                jeu.action(action,direction,false);
                jeu.changePlayer();
            }
            */
        }
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
