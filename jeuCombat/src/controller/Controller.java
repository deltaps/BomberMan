package controller;

import model.Model;
import personnagesJeu.Personnage;
import vue.Vue;

import java.util.Arrays;

public class Controller {

    private Model model;
    private Vue vue;

    protected String action;

    public Controller(Model model) {
        this.model = model;

        for(Personnage joueur : model.getListeJoueurs()) {
            Vue view = new Vue(joueur, model.getProxyPlateau(), this); // Affiche la fenêtre du jeu.
            model.addModelListener(view);
            EcouteurSouris souris = new EcouteurSouris(this, joueur);
            view.addMouseListener(souris);
        }

        this.action = null;
    }

    public Personnage getJoueurCourant() {
        return this.model.getCurrentPlayer();
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action, Personnage joueur) {
        if(joueur == this.model.getCurrentPlayer()) {
            this.action = action;
        }
    }

    public void action(int[] position) {
        if(this.action == "deplacement") {
            this.model.action(8, position, false);
        }

        else if(this.action == "mine") {
            this.model.action(9, position, false);
        }

        else if(this.action == "bombe") {
            this.model.action(10, position, false);
        }

        else if(this.action == "tir") {
            this.action = null;
            this.model.action(11, position, false);
        }
    }

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
