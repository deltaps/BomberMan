package controller;

import model.Model;
import personnagesJeu.Personnage;
import vue.Vue;

public class Controller {

    private Model model;
    private Vue vue;

    public Controller(Model model) {
        this.model = model;

        //for(Personnage joueur : model.getListeJoueurs()) {
            this.vue = new Vue(model.getCurrentPlayer(), model.getProxyPlateau()); // Affiche la fenêtre du jeu.
        //}

        EcouteurSouris souris = new EcouteurSouris(this);
        vue.addMouseListener(souris);
    }

    public void deplace() {
        this.model.action(8, new int[]{-1,0}, false);
    }

}
