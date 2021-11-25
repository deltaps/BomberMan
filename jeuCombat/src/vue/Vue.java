package vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.ConcretePlateau;
import model.Plateau;
import model.ProxyPlateau;
import observer.ModelListener;
import personnagesJeu.Personnage;

public class Vue extends JFrame implements ModelListener {
    private ProxyPlateau plateau;
    private Personnage joueur;
    //private List<Personnage> listejoueurs;

    private VuePlateau vuePlateau;

    public Vue(Personnage joueur, ProxyPlateau plateau) {
        this.joueur = joueur;
        this.plateau = plateau;

        plateau.addModelListener(this);
        //this.listejoueurs = plateau.getJoueurs();

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

        this.setContentPane(contentPane);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


    @Override
    public void somethingHasChanged(Object source) {
        setVisible(true);
        System.out.println("ui");
    }
}
