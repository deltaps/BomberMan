package vue;

import controller.Controller;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.*;

public class VueOptions extends JFrame {

    private final Object[] taillesPlateauxPossibles = {8, 9, 10, 11, 12};
    private final Object[] nombresJoueursPossibles = {2, 3, 4, 5, 6, 7, 8};

    public VueOptions(Controller controller) {
        int taillePlateau = (int) afficheInputDialogQuestionAvecPossibles("Veuillez choisir la taille du plateau :", "Sélectionnez une option", taillesPlateauxPossibles);

        System.out.println(taillePlateau);

        int nombreJoueurs = (int) afficheInputDialogQuestionAvecPossibles("Veuillez choisir le nombre de joueur :", "Sélectionnez une option", nombresJoueursPossibles);
        System.out.println(nombreJoueurs);
        List<String> joueurs = new ArrayList<>();
        for(int i = 1; i < nombreJoueurs+1; i++) {
            joueurs.add((String) afficheInputDialog("Entrez le nom du joueur " + i + " :", "Joueur " + i));
        }

        controller.createModel(taillePlateau, joueurs);
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public Object afficheInputDialogQuestionAvecPossibles(String message, String titre, Object[] possibles) {
        return JOptionPane.showInputDialog(null, message, titre, JOptionPane.QUESTION_MESSAGE, null, possibles, possibles[0]);
    }

    public Object afficheInputDialog(String message, String titre) {
        return JOptionPane.showInputDialog(null, message, titre, JOptionPane.QUESTION_MESSAGE);
    }
}
