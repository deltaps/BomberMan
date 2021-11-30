package vue;

import javax.swing.*;
import java.awt.*;

import controller.Controller;
import model.ProxyPlateau;
import observer.ModelListener;
import personnagesJeu.Personnage;

public class Vue extends JFrame implements ModelListener {
    private ProxyPlateau plateau;
    private Personnage joueur;
    private Controller controller;

    private VuePlateau vuePlateau;
    private InfoJoueur infoJoueur;
    private ActionJoueur actionJoueur;

    public Vue(Personnage joueur, ProxyPlateau plateau, Controller controller) {
        this.joueur = joueur;
        this.plateau = plateau;
        this.controller = controller;

        this.vuePlateau = new VuePlateau(plateau, this.joueur);
        this.infoJoueur = new InfoJoueur(this.joueur);
        this.actionJoueur = new ActionJoueur(this.joueur, this.controller);

        this.GUI();
    }

    public void GUI() {
        this.setTitle("Bomberman");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 600));

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
