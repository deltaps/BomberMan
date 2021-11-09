package vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.ConcretePlateau;
import model.ProxyPlateau;
import personnagesJeu.Personnage;

public class Vue extends JFrame {
    private Personnage joueur;
    private AdapterFromProxyPlateauToTableModel adaptedPlateau;
    private List<Personnage> listejoueurs;

    public Vue(Personnage joueur, ProxyPlateau proxyPlateau) {
        this.joueur = joueur;
        this.adaptedPlateau = new AdapterFromProxyPlateauToTableModel(proxyPlateau);
        this.listejoueurs = proxyPlateau.getJoueurs();

        this.GUI();
    }

    public void GUI() {
        this.setTitle("Bomberman");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 800));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);

        VuePlateau plateau = new VuePlateau(this.adaptedPlateau, this.joueur, this.listejoueurs);
        contentPane.add(plateau);

        //ListeJoueurs listeJoueurs = new ListeJoueurs(this.joueurs)
        //contentPane.add(listeJoueurs);

        //InfoJoueur infoJoueur = new InfoJoueur(this.joueur);
        //contentPane.add(infoJoueur);

        //ActionJoueur actionJoueur = new ActionJoueur();
        //contentPane.add(actionJoueur);

        this.setContentPane(contentPane);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}
