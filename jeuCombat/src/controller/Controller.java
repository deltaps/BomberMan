package controller;

import model.Model;
import personnagesJeu.Personnage;
import vue.Vue;
import vue.VueOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    private Model model;
    private Vue vue;

    protected String action;
    protected boolean visible;

    public Controller() {

        new VueOptions(this);

        for(Personnage joueur : model.getListeJoueurs()) {
            Vue view = new Vue(joueur, model.getProxyPlateau(), this); // Affiche la fenêtre du jeu.
            model.addModelListener(view);
            EcouteurSouris souris = new EcouteurSouris(this, joueur);
            view.addMouseListener(souris);
        }

        this.action = null;
        this.visible = false;
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

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean b) {
        this.visible = b;
    }

    public void createModel(int taille, List<String> nomJoueurs) {
        List<Personnage> joueurs = new ArrayList<>();
        for(String nom : nomJoueurs) {
            joueurs.add(new Personnage(nom));
        }
        this.model = new Model(taille, joueurs);
    }

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
