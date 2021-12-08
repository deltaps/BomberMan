package vue;

import controller.Controller;
import personnagesJeu.Personnage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Classe permettant d'afficher les options disponibles pour le joueur en fonction de ses attributs actuels.
Les boutons ont des listener qui appelent des fonctions du controller, si l'utilisateur veut que celles-ci soient visibles ou non.
 */

public class ActionJoueur extends JPanel implements ActionListener {

    private Personnage joueur;
    private Controller controller;

    // Tous les boutons correspondant à toutes les actions possibles.
    // Les JCheckBox servent uniquement pour poser des bombes.
    private final JButton deplacement, mine, bombe, tir, bouclier, rienfaire, annuler;
    private final JCheckBox checkBoxVisible;

    public ActionJoueur(Personnage joueur, Controller controller, int width) {
        this.joueur = joueur;
        this.controller = controller;

        setPreferredSize(new Dimension(width,200));
        setBorder(BorderFactory.createLineBorder(Color.black));

        this.deplacement = new JButton("Se déplacer");
        this.mine = new JButton("Mine");
        this.bombe = new JButton("Bombe");
        this.tir = new JButton("Tirer");
        this.bouclier = new JButton("Se protéger");
        this.rienfaire = new JButton("Ne rien faire");
        this.annuler = new JButton("Annuler");
        this.checkBoxVisible = new JCheckBox("Visible par les ennemis");
        this.checkBoxVisible.setFont(new Font("Serif", Font.PLAIN, 10));

        this.deplacement.addActionListener(this);
        this.mine.addActionListener(this);
        this.bombe.addActionListener(this);
        this.tir.addActionListener(this);
        this.bouclier.addActionListener(this);
        this.rienfaire.addActionListener(this);
        this.annuler.addActionListener(this);
        this.checkBoxVisible.addActionListener(this);


        afficheActionsDisponibles();
    }

    // Cette méthode affiche toutes les actions en fonction de l'énergie et des munitions du joueur.
    // Par exemple s'il n'a plus assez d'énergie, il ne peut plus poser de bombe donc le bouton pour poser des bombes ne s'affiche plus.
    public void afficheActionsDisponibles() {
        removeAll();

        if(this.joueur == this.controller.getJoueurCourant()) { // Si le joueur actuel est le joueur courant.

            if(this.controller.getAction() == null) { // Si le joueur n'a pas sélectionné d'action.

                if (this.joueur.getEnergie() > 2 && this.joueur.getMunition() > 0) {
                    setLayout(new GridLayout(6, 1));

                    add(this.deplacement);
                    add(this.mine);
                    add(this.bombe);
                    add(this.tir);
                    add(this.bouclier);
                    add(this.rienfaire);
                }

                else if (this.joueur.getEnergie() > 1 && this.joueur.getMunition() > 0) {
                    setLayout(new GridLayout(4, 1));

                    add(this.deplacement);
                    add(this.tir);
                    add(this.bouclier);
                    add(this.rienfaire);
                }

                else if (this.joueur.getEnergie() <= 1 && this.joueur.getMunition() > 0) {
                    setLayout(new GridLayout(2, 1));

                    add(this.tir);
                    add(this.rienfaire);
                }
                else if (this.joueur.getEnergie() > 1 && this.joueur.getMunition() <= 0) {
                    setLayout(new GridLayout(2, 1));

                    add(this.bouclier);
                    add(this.rienfaire);
                }

                else if (this.joueur.getEnergie() <= 1 && this.joueur.getMunition() <= 0) {
                    setLayout(new GridLayout(1, 1));

                    add(this.rienfaire);
                }

                else if (this.joueur.getEnergie() > 2 && this.joueur.getMunition() <= 0) {
                    setLayout(new GridLayout(5, 1));

                    add(this.deplacement);
                    add(this.mine);
                    add(this.bombe);
                    add(this.bouclier);
                    add(this.rienfaire);
                }

                else if (this.joueur.getEnergie() > 1 && this.joueur.getMunition() <= 0) {
                    setLayout(new GridLayout(3, 1));

                    add(this.deplacement);
                    add(this.bouclier);
                    add(this.rienfaire);
                }
            }

            // Si le joueur a sélectionné les actions telles que "deplacement", "mine", "bombe".
            else if(this.controller.getAction() == "deplacement" || this.controller.getAction() == "mine" || this.controller.getAction() == "bombe") {

                if(this.joueur.getEnergie() > 1) {
                    if(this.controller.getAction() == "deplacement") {
                        afficheActionDeplacement();
                    }
                    else if(this.controller.getAction() == "mine") {
                        afficheActionMine();
                    }
                    else {
                        afficheActionBombe();
                    }
                }
                else {
                    this.controller.setAction(null, this.joueur);
                    this.afficheActionsDisponibles();
                }
            }
        }

        else {
            setLayout(new GridLayout(1,1));
            JLabel label = new JLabel("Tour de " + this.controller.getJoueurCourant().getName());
            add(label);
        }

        revalidate();
        repaint();
    }

    // Si le joueur a sélectionné l'action "deplacement", il affiche du texte et un bouton pour annuler son action.
    public void afficheActionDeplacement() {
        removeAll();
        setLayout(new GridLayout(3,1));

        JTextArea textArea1 = createTextArea("Vous pouvez vous déplacer d'une case adjacente à la votre si vous possédez plus de 1 énergie.");
        JTextArea textArea2 = createTextArea("Pour cela, cliquer sur une des cases à coté de la votre. Cette action enlève 1 énergie par déplacement.");

        add(textArea1);
        add(textArea2);
        add(this.annuler);
    }

    // Pareil mais avec l'action pour poser une mine.
    public void afficheActionMine() {
        removeAll();
        setLayout(new GridLayout(3,1));

        JTextArea textArea1 = createTextArea("Vous pouvez déposer une mine sur une case adjacente ou diagonale à la votre si vous possédez plus de 1 énergie.");

        this.checkBoxVisible.setSelected(this.controller.getVisible());

        add(textArea1);
        add(this.checkBoxVisible);
        add(this.annuler);
    }

    // Pareil mais avec l'action pour poser une bombe.
    public void afficheActionBombe() {
        removeAll();
        setLayout(new GridLayout(3,1));

        JTextArea textArea1 = createTextArea("Vous pouvez déposer une bombe sur une case adjacente ou diagonale à la votre si vous possédez plus de 1 énergie.");

        this.checkBoxVisible.setSelected(this.controller.getVisible());

        add(textArea1);
        add(this.checkBoxVisible);
        add(this.annuler);
    }

    // Pareil mais avec l'action pour tirer.
    public void afficheActionTir() {
        removeAll();
        setLayout(new GridLayout(3,1));

        JTextArea textArea1 = createTextArea("Vous pouvez tirer dans une direction (horizontale ou verticale) si vous possédez au moins 1 munition.");
        JTextArea textArea2 = createTextArea("Si le tir touche un autre joueur, celui-ci perd de l'énergie.");

        add(textArea1);
        add(textArea2);
        add(this.annuler);
    }

    // Cette méthode va créer et renvoyer une zone de text (JTextArea).
    // JTextArea a la particularité d'avoir les méthodes setLineWrap(true) qui permet le saut de lignes
    // Ainsi que setFont() qui permet de réduire la taille de la police dans notre cas, mais aussi de la changer (la police).
    public JTextArea createTextArea(String message) {
        JTextArea textArea = new JTextArea(message);

        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Serif", Font.PLAIN, 10));

        return textArea;
    }

    // Méthode redéfinie de ActionListener exécutée chaque fois qu'un bouton est appuyé.
    // Analyse la source et si c'est un bouton, fait l'action du controller correspondant.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == annuler) {
            this.controller.setAction(null, this.joueur);
            afficheActionsDisponibles();
        }

        else if(source == deplacement) {
            this.controller.setAction("deplacement", this.joueur);
            afficheActionDeplacement();
        }

        else if (source == mine) {
            this.controller.setAction("mine", this.joueur);
            afficheActionMine();
        }

        else if(source == bombe) {
            this.controller.setAction("bombe", this.joueur);
            afficheActionBombe();
        }

        else if(source == tir) {
            this.controller.setAction("tir", this.joueur);
            afficheActionTir();
        }

        else if(source == bouclier) {
            this.controller.setAction("bouclier", this.joueur);
            this.controller.action();

        }

        else if(source == rienfaire) {
            this.controller.setAction("rienfaire", this.joueur);
            this.controller.action();
        }
        else if(source == checkBoxVisible) {
            this.controller.setVisible(checkBoxVisible.isSelected());
        }

        revalidate();
        repaint();
    }
}
