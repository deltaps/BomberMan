package controller;

import personnagesJeu.Personnage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
Classe servant d'écouteur pour la souris.
 */

public class EcouteurSouris extends MouseAdapter {

    private Controller controller;
    private Personnage joueur;

    public EcouteurSouris(Controller controller, Personnage joueur) {
        this.controller = controller;
        this.joueur = joueur;
    }


    // Redéfinition de moussePressed qui détecte quand le clic gauche de la souris est actionnée.
    @Override
    public void mousePressed(MouseEvent e) {
        int xSouris = e.getX() -8;
        int ySouris = e.getY() -30; // getX() et getY() permettent d'avoir la position de la souris. Ces positions sont ajustées pour bien correspondre à la position réelle de chaque élément, d'où le "-8" et le "-30".

        int numeroCaseColonne = getNumeroCaseColonne(xSouris); // Variable correspondant à l'abscisse de la case cliquée du tableau de gauche, soit celui du joueur courant.
        int numeroCaseLigne = getNumeroCaseLigne(ySouris); // Variable correspondant à l'ordonnée de la case cliquée, qui est la même sur les deux tableaux car ils sont placés à coté l'un de l'autre.
        //Ces deux variables sont très utiles pour travailler avec les valeurs réelles du model, la position de la souris exacte ne sera donc plus utile.

        if(numeroCaseColonne > -1 && numeroCaseLigne > -1 && joueur == controller.getJoueurCourant()) { // Est vraie que si le clique n'est pas en dehors de la fenêtre ou que le joueur n'est pas le joueur courant.
            int[] direction = getDirection(new int[]{numeroCaseLigne, numeroCaseColonne});

            if(direction != null) {
                if (this.controller.getAction() == "deplacement" || this.controller.getAction() == "tir") {
                    if ((direction[0] == 0 && direction[1] != 0) || (direction[0] != 0 && direction[1] == 0)) {
                        this.controller.action(direction);
                    }
                }

                else if (this.controller.getAction() == "mine" || this.controller.getAction() == "bombe") {
                    this.controller.action(direction);
                }
            }
        }
    }

    // Méthode permettant d'obtenir la coordonnées de l'abscisse de la case cliquée.
    // La coordonnée de la souris est divisée par 40 car la taille des images est de 40x40.
    public int getNumeroCaseColonne(int xSouris) {
        int numeroCaseColonne = xSouris / 40;

        if(numeroCaseColonne < -1 || numeroCaseColonne > 20) {
            return -1; // Retourne -1 si le clique n'est pas dans la fenêtre
        }
        return numeroCaseColonne;
    }

    // Méthode permettant d'obtenir la coordonnée de l'ordonnée de la case cliquée.
    // La coordonnée de la souris est divisée par 40 car la taille des images est de 40x40.
    public int getNumeroCaseLigne(int ySouris) {
        int numeroCaseLigne = ySouris / 40;
        if(numeroCaseLigne < -1 || numeroCaseLigne > 20) {
            return -1; // Retourne -1 si le clique n'est pas dans la fenêtre
        }
        return numeroCaseLigne;
    }

    // Fonction qui renvoie la direction du clique (de la case à coté) détecté par rapport au joueur.
    public int[] getDirection(int[] positionSouris) {
        int[] positionJoueur = this.controller.getJoueurCourant().getPosition();
        int x;
        int y;

        if(positionJoueur[0] == positionSouris[0]+1) {
            x = -1;
        }
        else if(positionJoueur[0] == positionSouris[0]-1) {
            x = 1;
        }
        else if(positionJoueur[0] == positionSouris[0]) {
            x = 0;
        }
        else {
            x = 2;
        }

        if(positionJoueur[1] == positionSouris[1]+1) {
            y = -1;
        }
        else if(positionJoueur[1] == positionSouris[1]-1) {
            y = 1;
        }
        else if(positionJoueur[1] == positionSouris[1]) {
            y = 0;
        }
        else {
            y = 2;
        }

        if(x == 2 || y == 2 || (x == 0 && y == 0)) {
            return null;
        }
        return new int[]{x,y};
    }
}
