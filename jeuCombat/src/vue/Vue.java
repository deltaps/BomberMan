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
    private InfoJoueur infoJoueur;

    public Vue(Personnage joueur, ProxyPlateau plateau) {
        this.joueur = joueur;
        this.plateau = plateau;

        this.vuePlateau = new VuePlateau(plateau, this.joueur);
        this.infoJoueur = new InfoJoueur(this.joueur);

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
        contentPane.add(playerList, BorderLayout.EAST);

        contentPane.add(this.infoJoueur, BorderLayout.WEST);

        ActionJoueur actionJoueur = new ActionJoueur(this.joueur);
        contentPane.add(actionJoueur, BorderLayout.CENTER);

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
        getContentPane().add(this.infoJoueur);

        setVisible(true);

        repaint();
    }
}
