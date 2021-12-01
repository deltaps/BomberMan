package vue;

import javax.swing.*;

import model.*;
import personnagesJeu.Personnage;
import java.awt.*;
import java.util.List;

public class VuePlateau extends JPanel {

    public Plateau plateau;
    public Personnage joueurCourant;

    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final int TAILLE_IMAGE = 40;

    public VuePlateau (Plateau plateau, Personnage joueurCourant){

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.plateau = plateau;
        this.joueurCourant = joueurCourant;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tailleLigne = this.plateau.getTaille();
        int tailleColonne = this.plateau.getTaille();

        for(int x = 0; x <= tailleLigne; x++) {
            for(int y = 0; y <= tailleColonne; y++) {
                Case square = this.plateau.getCase(y, x);

                afficheCase(g,x, y);
                afficheMur(g, square, x, y);
                if(this.plateau.getArme(y, x, this.joueurCourant) != null) {
                    afficheWeapon(g, square, x, y);
                }
                affichePastille(g, square, x, y);
            }
        }
        afficheJoueur(g);
        g.dispose();
    }

    public void afficheCase(Graphics g, int x, int y) {
        g.drawImage(Image.imageCase, x * TAILLE_IMAGE, y * TAILLE_IMAGE, null);
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
        List<Personnage> listeJoueurs = this.plateau.getJoueurs();
        for (Personnage X : listeJoueurs) {
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
    //public void afficheTir

}
