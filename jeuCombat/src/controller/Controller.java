package controller;

import model.Model;
import personnagesJeu.Personnage;
import vue.Vue;
import vue.VueOptions;

import java.util.ArrayList;
import java.util.List;

/*
Le controller lie le modèle est la vue et permet au joueur d'intéragir avec.
 */

public class Controller {

    private Model model;

    // 2 Variables servants uniquement pour les actions.
    protected String action;
    protected boolean visible;

    public Controller() {

        // Créer le model grâce aux options transmises dans cette classe.
        new VueOptions(this);

        // Pour tous les personnages, créer une fenêtre différente et leur attribuer les écouteurs.
        for(Personnage joueur : model.getListeJoueurs()) {
            Vue view = new Vue(joueur, model.getProxyPlateau(), this); // Affiche la fenêtre du jeu.
            model.addModelListener(view);
            EcouteurSouris souris = new EcouteurSouris(this, joueur);
            view.addMouseListener(souris);
        }

        this.action = null;
        this.visible = false;
    }

    // Renvoie le joueur courant.
    public Personnage getJoueurCourant() {
        return this.model.getCurrentPlayer();
    }

    // Renvoie l'action sélectionnée par le joueur.
    public String getAction() {
        return this.action;
    }

    // Une action est modifiée quand le joueur en sélectionne une.
    public void setAction(String action, Personnage joueur) {
        if(joueur == this.model.getCurrentPlayer()) {
            this.action = action;
        }
    }

    // Même chose que les actions.
    public boolean getVisible() {
        return this.visible;
    }

    // Même chose que les actions.
    public void setVisible(boolean b) {
        this.visible = b;
    }

    // Créer le model à partir des informations que les joueurs ont transmis.
    public void createModel(int taille, List<String> nomJoueurs) {
        List<Personnage> joueurs = new ArrayList<>();
        for(String nom : nomJoueurs) {
            joueurs.add(new Personnage(nom));
        }
        this.model = new Model(taille, joueurs);
    }

    public boolean isOver() {
        return this.model.isOver();
    }

    // Exécute l'action sélectionnée avec une position déterminée par la souris.
    public void action(int[] position) {
        if(this.action == "deplacement") {
            this.model.action(8, position, false);
        }

        else if(this.action == "mine") {
            this.model.action(9, position, this.visible);
        }

        else if(this.action == "bombe") {
            this.model.action(10, position, this.visible);
        }

        else if(this.action == "tir") {
            this.action = null;
            this.model.action(11, position, false);
        }
    }

    // Exécute une action qui n'a pas besoin de position.
    public void action() {
        if(this.action == "bouclier") {
            this.action = null;
            this.model.action(12, new int[]{0,0}, false);
        }

        else if(this.action == "rienfaire") {
            this.action = null;
            this.model.action(13, new int[]{0,0}, false);
        }
    }
}
