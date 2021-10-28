package vue;

import javax.swing.*;

import model.ConcretePlateau;
import personnagesJeu.Personnage;

public class Vue extends JFrame {
    private Personnage joueur;
    private ConcretePlateau concretePlateau;

    public Vue(Personnage joueur, ConcretePlateau concretePlateau) {
        this.joueur = joueur;
        this.concretePlateau = concretePlateau;
    }

    //public


}
