package vue;

import personnagesJeu.Personnage;

import javax.swing.*;
import java.awt.*;

public class ActionJoueur extends JPanel {
    private Personnage joueur;

    public ActionJoueur(Personnage joueur) {
        this.joueur = joueur;

        setPreferredSize(new Dimension(150,200));
    }
}
