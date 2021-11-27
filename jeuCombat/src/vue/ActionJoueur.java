package vue;

import personnagesJeu.Personnage;

import javax.swing.*;

public class ActionJoueur extends JPanel {
    private Personnage joueur;

    public ActionJoueur(Personnage joueur) {
        this.joueur = joueur;

        setSize(600,200);
    }
}
