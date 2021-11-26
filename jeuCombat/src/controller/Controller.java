package controller;

import model.Model;
import observer.AbstractListenableModel;
import observer.ModelListener;
import vue.Vue;

import java.util.Arrays;

public class Controller extends AbstractListenableModel implements ModelListener {

    private Model model;
    private Vue vue;

    public Controller(Model model) {
        this.model = model;
        model.addModelListener(this);

        //for(Personnage joueur : model.getListeJoueurs()) {
            this.vue = new Vue(model.getCurrentPlayer(), model.getProxyPlateau()); // Affiche la fenêtre du jeu.
        //}

        EcouteurSouris souris = new EcouteurSouris(this);
        vue.addMouseListener(souris);
    }

    public void deplace() {
        System.out.println(Arrays.toString(model.getCurrentPlayer().getPosition()));
        this.model.action(8, new int[]{-1,0}, false);
        System.out.println(Arrays.toString(model.getCurrentPlayer().getPosition()));

    }

    @Override
    public void somethingHasChanged(Object source) {
        this.vue.update();
    }
}
