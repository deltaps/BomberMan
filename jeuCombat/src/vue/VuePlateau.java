package vue;

import javax.swing.*;
import model.ConcretePlateau;
import model.Case;
import model.Bomb;
import model.LandMine;
import personnagesJeu.Personnage;
import java.awt.*;
import java.util.List;


public class VuePlateau extends JPanel {

    public AdapterFromConcretePlateauToTableModel adaptedPlateau;
    public List<Personnage> listeJoueurs;
    public Personnage joueurCourant;

    private final int TAILLE_IMAGE = 40;

    public VuePlateau (AdapterFromConcretePlateauToTableModel adaptedPlateau, Personnage joueurCourant, List<Personnage> listeJoueurs) {

        this.setSize(new Dimension(800, 800));

        this.adaptedPlateau = adaptedPlateau;
        this.joueurCourant = joueurCourant;
        this.listeJoueurs = listeJoueurs;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tailleLigne = this.adaptedPlateau.getRowCount();
        int tailleColonne = this.adaptedPlateau.getColumnCount();

        for(int x = 0; x <= tailleLigne; x++) {
            for(int y = 0; y <= tailleColonne; y++) {
                Case square = this.adaptedPlateau.getValueAt(y, x);

                afficheCase(g,x, y);
                afficheMur(g, square, x, y);
                afficheWeapon(g, square, x, y);
                affichePastille(g, square, x, y);
            }
        }
        afficheJoueur(g);
    }

    public void afficheCase(Graphics g, int x, int y) {
        g.drawImage(Image.imageCase, x * TAILLE_IMAGE, y * TAILLE_IMAGE, null);
        //if(casse.getPastille() == true) {
            //affichePastille(g, x, y);
        //}
        //if(square.getWeapon instanceof Bomb) {

        //}

    }

    public void afficheMur(Graphics g, Case square, int x, int y) {
        if(square.getWall()) {
            g.drawImage(Image.imageMur, x * TAILLE_IMAGE, y * TAILLE_IMAGE, null);
        }
    }

    public void afficheWeapon(Graphics g, Case square, int x, int y){
        if(square.getWeapon() instanceof Bomb) {
            g.drawImage(Image.imageBombe, x * TAILLE_IMAGE, y * TAILLE_IMAGE, null);
        }

        else if (square.getWeapon() instanceof LandMine) {
            g.drawImage(Image.imageMine, x * TAILLE_IMAGE, y * TAILLE_IMAGE, null);
        }
    }

    public void afficheJoueur(Graphics g) {
        for (Personnage X : this.listeJoueurs) {
            int x = X.getPosition()[0];
            int y = X.getPosition()[1];

            if(X == this.joueurCourant) {
                g.drawImage(Image.imageJoueur, y * TAILLE_IMAGE, x * TAILLE_IMAGE, null);
            }
            else {
                g.drawImage(Image.imageEnnemie, y * TAILLE_IMAGE, x * TAILLE_IMAGE, null);
            }
        }
    }

    public void affichePastille(Graphics g, Case square, int x, int y){
        if(square.getPastille()){
            g.drawImage(Image.imagePastille, x * TAILLE_IMAGE, y * TAILLE_IMAGE, null);
        }
    }
    //publiv void afficheTir

}
