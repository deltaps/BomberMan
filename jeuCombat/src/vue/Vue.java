package vue;

import javax.swing.*;
import java.awt.*;

import model.ProxyPlateau;
import observer.ModelListener;
import personnagesJeu.Personnage;

public class Vue extends JFrame implements ModelListener {
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
        contentPane.setLayout(new BorderLayout(1,1));


        contentPane.add(this.vuePlateau, BorderLayout.WEST);

        contentPane.add(new JTable(new AdapterFromListeJoueursToTableModel(this.plateau.getJoueurs())), BorderLayout.EAST);

        //ListeJoueurs listeJoueurs = new ListeJoueurs(this.joueurs)
        //contentPane.add(listeJoueurs);

        //InfoJoueur infoJoueur = new InfoJoueur(this.joueur);
        //contentPane.add(infoJoueur);

        //ActionJoueur actionJoueur = new ActionJoueur();
        //contentPane.add(actionJoueur);

        setContentPane(contentPane);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void somethingHasChanged(Object source) {
        repaint();
    }
}
