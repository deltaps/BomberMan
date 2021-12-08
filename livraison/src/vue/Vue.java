package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

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

    /*
    Méthode servant pour l'affichage de la fenêtre principale.
     */
    public void GUI() {
        this.setTitle("Bomberman"); // Titre de la fenêtre
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Si la fenêtre est fermée met fin au programme.
        this.setPreferredSize(new Dimension(Image.TAILLE_IMAGE * plateau.getTaille() +10, Image.TAILLE_IMAGE * plateau.getTaille() +200)); // On calcule les dimensions de la fenêtre en fonction de la taille du plateau.

        // contentPane va contenir tous les éléments de la fenêtre.
        // Nous utilisons un layout "BorderLayout()" pour positionner nos éléments simplement.
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
        //setLocationRelativeTo(null);
    }

    // Affiche un message et stoppe le programme tant que le joueur n'appuie pas sur "OK".
    public void afficheMessage(String message, String titre) {
        JOptionPane.showMessageDialog(null, message, titre, JOptionPane.INFORMATION_MESSAGE);
    }

    /*
    Méthode faisant parti de l'Observer Pattern, qui détecte si le model a changé (à chaque appel de la fonction firechange()
    et redessine certains éléments si c'est le cas.
     */
    @Override
    public void somethingHasChanged(Object source) {
        getContentPane().remove(this.infoJoueur);
        this.infoJoueur = new InfoJoueur(this.joueur);
        getContentPane().add(this.infoJoueur, BorderLayout.WEST);

        setVisible(true);

        this.actionJoueur.afficheActionsDisponibles();

        revalidate();
        repaint();

        // Si le jeu est fini, affiche une fenêtre pour féliciter le joueur courant et ferme le jeu.
        if(this.controller.isOver()) {
            afficheMessage(this.joueur.getName() + " a remporté la partie, bravo à lui !", "Félicitation");
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); // Ferme cette fenêtre.
        }
    }
}
