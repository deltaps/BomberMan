package vue;

import javax.swing.*;

import model.*;
import personnagesJeu.Personnage;
import java.awt.*;
import java.util.List;

/*
Cette classe va afficher le plateau en entier et tous ses éléments.
 */

public class VuePlateau extends JPanel {

    public Plateau plateau;
    public Personnage joueurCourant;

    private final int TAILLE_IMAGE = 40;

    public VuePlateau (Plateau plateau, Personnage joueurCourant){

        setPreferredSize(new Dimension(Image.TAILLE_IMAGE * plateau.getTaille(), Image.TAILLE_IMAGE * plateau.getTaille()));

        this.plateau = plateau;
        this.joueurCourant = joueurCourant;
    }

    // Méthode redéfinie de JPanel, permet de dessiner grâce à une variable de type Graphics.
    // Elle est appelée avec diverses fonction dans la vue comme "setVisible(true)" ou encore "repaint()".
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tailleLigne = this.plateau.getTaille();
        int tailleColonne = this.plateau.getTaille();

        for(int x = 0; x < tailleLigne; x++) {
            for(int y = 0; y < tailleColonne; y++) {
                // Pour chaque case, nous allons afficher tous les éléments qui sont dessus.
                Case square = this.plateau.getCase(y, x);

                afficheCase(g,x, y);
                afficheMur(g, square, x, y);
                afficheWeapon(g, square, x, y);
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

    // Affiche toutes les bombes qui sont visibles par tout le monde et toutes les bombes du joueur personnel non visibles.
    public void afficheWeapon(Graphics g, Case square, int x, int y){
        if(this.plateau.getArme(y, x, this.joueurCourant) != null) {
            if (square.getWeapon() instanceof Bomb) {
                g.drawImage(Image.imageBombe, x * TAILLE_IMAGE, y * TAILLE_IMAGE, null);
            } else if (square.getWeapon() instanceof LandMine) {
                g.drawImage(Image.imageMine, x * TAILLE_IMAGE, y * TAILLE_IMAGE, null);
            }
        }
    }

    // Affiche les joueurs ennemis en rouge, et le joueur personnel en bleu. N'affiche pas les joueurs qui n'ont plus d'énergie.
    public void afficheJoueur(Graphics g) {
        List<Personnage> listeJoueurs = this.plateau.getJoueurs();
        for (Personnage joueur : listeJoueurs) {
            if(joueur.getEnergie() > 0) {
                int x = joueur.getPosition()[0];
                int y = joueur.getPosition()[1];

                if (joueur == this.joueurCourant) {
                    g.drawImage(Image.imageJoueur, y * TAILLE_IMAGE, x * TAILLE_IMAGE, null);
                } else {
                    g.drawImage(Image.imageEnnemie, y * TAILLE_IMAGE, x * TAILLE_IMAGE, null);
                }
            }
        }
    }

    public void affichePastille(Graphics g, Case square, int x, int y){
        if(square.getPastille()){
            g.drawImage(Image.imagePastille, x * TAILLE_IMAGE, y * TAILLE_IMAGE, null);
        }
    }
}
