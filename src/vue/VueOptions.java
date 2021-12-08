package vue;

import controller.Controller;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.*;

/*
Classe permettant de générer les options grâce à une interface graphique
et de créer un model via le controller à partir des informations entrées par l'utilisateur.
 */

public class VueOptions extends JFrame {

    // Ces deux tableaux sont les valeurs possibles pour les 2 premières fenêtres.
    // La taille du plateau est restreinte de 9 à 12 de base sinon cela cause des problèmes d'affichage, mais nous pouvons très bien changer ces valeurs.
    // Le nombre des joueurs est restreint de 2 à 8 pour empêcher d'ouvrir trop de fenêtres différentes.
    private final Object[] taillesPlateauxPossibles = {9, 10, 11, 12};
    private final Object[] nombresJoueursPossibles = {2, 3, 4, 5, 6, 7, 8};

    public VueOptions(Controller controller) {
        // Taille du plateau
        int taillePlateau = (int) afficheInputDialogAvecPossibles("Veuillez choisir la taille du plateau :", "Sélectionnez une option", taillesPlateauxPossibles);

        // Nombre de joueurs et liste de joueurs.
        int nombreJoueurs = (int) afficheInputDialogAvecPossibles("Veuillez choisir le nombre de joueur :", "Sélectionnez une option", nombresJoueursPossibles);
        List<String> joueurs = new ArrayList<>();
        for(int i = 1; i < nombreJoueurs+1; i++) {
            joueurs.add((String) afficheInputDialog("Entrez le nom du joueur " + i + " :", "Joueur " + i));
        }

        controller.createModel(taillePlateau, joueurs);
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); // Ferme cette fenêtre.
    }

    // Affiche un "pop up" avec JOptionPane qui permet à l'utilisateur d'entrer une valeur et de la récupérer.
    public Object afficheInputDialogAvecPossibles(String message, String titre, Object[] possibles) {
        return JOptionPane.showInputDialog(null, message, titre, JOptionPane.QUESTION_MESSAGE, null, possibles, possibles[0]);
    }

    public Object afficheInputDialog(String message, String titre) {
        return JOptionPane.showInputDialog(null, message, titre, JOptionPane.QUESTION_MESSAGE);
    }
}
