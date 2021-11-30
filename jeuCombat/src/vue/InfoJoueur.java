package vue;

import personnagesJeu.Personnage;

import javax.swing.*;
import java.awt.*;

public class InfoJoueur extends JPanel {

    private Personnage joueur;

    public InfoJoueur(Personnage joueur) {

        this.joueur = joueur;
        setPreferredSize(new Dimension(100,200));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new GridLayout(5,1));
        add(new JLabel("Joueur : " + joueur.getName()));
        add(new JLabel("Énergie : " + joueur.getEnergie()));
        add(healthBar(joueur.getEnergie()));
        add(new JLabel("Munitions : " + joueur.getMunition()));
        if(joueur.getBouclier()) {
            add(new JLabel("Bouclier activé"));
        }
        else {
            add(new JLabel("Bouclier inactif"));
        }

    }

    public JPanel healthBar(int energy) {
        return new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                if(energy > 10 && energy <= 20) {
                    g.setColor(new Color((20-energy)*25, 255, 0));
                    g.fillRect(0,0,energy*3,20);
                }
                else if(energy <= 10 && energy > -1) {
                    g.setColor(new Color(255, energy*25, 0));
                    g.fillRect(0,0,energy*3,20);
                }
                else if(energy <= -1) {
                    g.setColor(Color.black);
                    g.fillRect(0,0,60,20);
                }
                else {
                    g.setColor(new Color(0, 255, 0));
                    g.fillRect(0,0,60,20);
                }
            }
        };
    }
}
