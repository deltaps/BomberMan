package vue;

import personnagesJeu.Personnage;

import javax.swing.*;
import java.awt.*;
import java.util.List;



public class ListeJoueurs extends JTable {
    public ListeJoueurs(List<Personnage> joueurs) {


        setSize(new Dimension(200,600));
    }
}
