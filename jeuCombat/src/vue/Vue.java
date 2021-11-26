package vue;

import javax.swing.*;
import java.awt.*;

import model.ProxyPlateau;
import personnagesJeu.Personnage;

public class Vue extends JFrame {
    private ProxyPlateau plateau;
    private Personnage joueur;

    private VuePlateau vuePlateau;

    public Vue(Personnage joueur, ProxyPlateau plateau) {
        this.joueur = joueur;
        this.plateau = plateau;

        this.vuePlateau = new VuePlateau(plateau, this.joueur);

        this.GUI();
    }

    public void GUI() {
        this.setTitle("Bomberman");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 800));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);


        contentPane.add(this.vuePlateau);

        //ListeJoueurs listeJoueurs = new ListeJoueurs(this.joueurs)
        //contentPane.add(listeJoueurs);

        //InfoJoueur infoJoueur = new InfoJoueur(this.joueur);
        //contentPane.add(infoJoueur);

        //ActionJoueur actionJoueur = new ActionJoueur();
        //contentPane.add(actionJoueur);

        setContentPane(contentPane);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void update() {
        repaint();
    }
}
