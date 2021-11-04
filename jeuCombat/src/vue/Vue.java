package vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.ConcretePlateau;
import personnagesJeu.Personnage;

public class Vue extends JFrame {
    private Personnage joueur;
    private AdapterFromConcretePlateauToTableModel adaptedPlateau;
    private List<Personnage> listejoueurs;

    public Vue(Personnage joueur, ConcretePlateau concretePlateau) {
        this.joueur = joueur;
        this.adaptedPlateau = new AdapterFromConcretePlateauToTableModel(concretePlateau);
        this.listejoueurs = concretePlateau.getJoueurs();

        this.GUI();
    }

    public void GUI() {
        this.setTitle("Bomberman");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 800));
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);

        //VuePlateau plateau = new VuePlateau(this.adaptedPlateau)
        //contentPane.add(plateau);

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
