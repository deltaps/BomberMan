package vue;

import javax.swing.*;
import model.ConcretePlateau;
import model.Case;
import model.Bomb;
import model.LandMine;
import vue.Image;
import java.awt.*;
import java.awt.Image;


public class VuePlateau extends JPanel {
    public AdapterFromConcretePlateauToTableModel adaptedPlateau;
    public Case casse;

    public VuePlateau (AdapterFromConcretePlateauToTableModel adaptedPlateau, Case casse){
        this.adaptedPlateau = adaptedPlateau;
        this.casse = casse;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tailleLigne = this.adaptedPlateau.getRowCount();
        int tailleColonne = this.adaptedPlateau.getColumnCount();

        for(int x = 0; x < tailleLigne; x++) {
            for(int y = 0; y < tailleColonne; y++) {
                //afficheCase(g, x, y);
                //afficheBombesEtMines(g, x, y);
                //affichePastille(g, x, y);
                //affiche
            }
        }
        //afficheJoueurs()
    }

    public void afficheCase(Graphics g, int x, int y) {
        g.drawImage(Image.imageCase, x, y, null);
        //if(casse.getPastille() == true) {
            //affichePastille(g, x, y);
        //}
        //if(casse.getWeapon instanceof Bomb) {

        //}
        Rectangle rectangle = new Rectangle(x, y, 10, 10);
    }


}
