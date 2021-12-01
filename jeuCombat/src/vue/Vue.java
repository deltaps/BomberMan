package vue;

import javax.swing.*;
import java.awt.*;

import controller.Controller;
import model.Plateau;
import observer.ModelListener;
import personnagesJeu.Personnage;

public class Vue extends JFrame implements ModelListener {
    private Plateau plateau;
    private Personnage joueur;
    private Controller controller;

    private VuePlateau vuePlateau;
    private InfoJoueur infoJoueur;
    private ActionJoueur actionJoueur;

    public Vue(Personnage joueur, Plateau plateau, Controller controller) {
        this.joueur = joueur;
        this.plateau = plateau;
        this.controller = controller;

        this.vuePlateau = new VuePlateau(plateau, this.joueur);
        this.infoJoueur = new InfoJoueur(this.joueur);
        this.actionJoueur = new ActionJoueur(this.joueur, this.controller, Image.TAILLE_IMAGE * plateau.getTaille() -250);

        this.GUI();
    }

    public void GUI() {
        this.setTitle("Bomberman");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(Image.TAILLE_IMAGE * plateau.getTaille() +10, Image.TAILLE_IMAGE * plateau.getTaille() +200));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());


        contentPane.add(this.vuePlateau, BorderLayout.NORTH);

        JTable playerList = new JTable(new AdapterFromListeJoueursToTableModel(this.plateau.getJoueurs()));
        playerList.setPreferredSize(new Dimension(150,200));
        playerList.setBorder(BorderFactory.createLineBorder(Color.black));
        contentPane.add(playerList, BorderLayout.EAST);

        contentPane.add(this.infoJoueur, BorderLayout.WEST);

        contentPane.add(this.actionJoueur, BorderLayout.CENTER);

        setContentPane(contentPane);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void somethingHasChanged(Object source) {
        getContentPane().remove(this.infoJoueur);
        this.infoJoueur = new InfoJoueur(this.joueur);
        getContentPane().add(this.infoJoueur, BorderLayout.WEST);

        setVisible(true);

        this.actionJoueur.afficheActionsDisponibles();

        revalidate();
        repaint();
    }
}
