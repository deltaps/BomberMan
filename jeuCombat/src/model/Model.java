package model;

import personnagesJeu.Personnage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    protected final int[] HAUT = new int[]{-1,0};
    protected final int[] BAS = new int[]{1,0};
    protected final int[] GAUCHE = new int[]{0,-1};
    protected final int[] DROITE = new int[]{0,1};
    protected final int[] HAUTDROITE = new int[]{-1,1};
    protected final int[] HAUTGAUCHE = new int[]{-1,-1};
    protected final int[] BASDROITE = new int[]{1,1};
    protected final int[] BASGAUCHE = new int[]{1,-1};
    protected final int DEPLACEMENT = 8;
    protected final int MINE = 9;
    protected final int BOMBE = 10;
    protected final int TIR = 11;
    protected final int BOUCLIER = 12;
    protected final int RIENFAIRE = 13;
    protected ConcretePlateau concretePlateau;
    protected Action action;
    protected Personnage currentPlayer;
    protected List<Personnage> listeJoueurs;
    protected ProxyPlateau proxyPlateau;

    public Model(int taillePlateau, List<Personnage> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
        this.concretePlateau = new ConcretePlateau(this.listeJoueurs, taillePlateau);
        this.action = new Action(this.concretePlateau);
        this.currentPlayer = this.listeJoueurs.get(0);
        this.proxyPlateau = new ProxyPlateau(this.concretePlateau);
    }

    public ConcretePlateau getPlateau() {
        return concretePlateau;
    }

    public void setPlateau(ConcretePlateau concretePlateau) {
        this.concretePlateau = concretePlateau;
    }

    public ProxyPlateau getProxyPlateau() {
        return this.proxyPlateau;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Personnage getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Personnage currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<Personnage> getListeJoueurs() {
        return listeJoueurs;
    }

    public void setListeJoueurs(List<Personnage> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    public boolean isOver() {
        int nbJoueurs = this.listeJoueurs.size();
        int nbJoueursDown = 0;
        for (Personnage joueur : this.listeJoueurs) {
            if (joueur.getEnergie() <= 0) {
                nbJoueursDown++;
            }
        }
        if (nbJoueursDown >= nbJoueurs - 1) {
            return true;
        }
        return false;
    }

    public void changePlayer() {
        for (int n = 0; n < this.listeJoueurs.size(); n++) {
            if (this.listeJoueurs.get(n) == this.currentPlayer) {
                if (n == this.listeJoueurs.size() - 1) {
                    this.currentPlayer = this.listeJoueurs.get(0);
                    break;
                } else {
                    this.currentPlayer = this.listeJoueurs.get(n + 1);
                    break;
                }
            }
        }
    }

    public void action(int action, int[] direction, boolean visible) {
        //TODO, est-ce que c'est la méthode action du model qui vérifie si l'énergie est suffisante?
        //si oui, qu'est-ce que l'on retourne?
        switch (action) {
            case DEPLACEMENT:
                this.action.deplacement(this.currentPlayer, direction);
                if (this.concretePlateau.getArme(this.currentPlayer.getPosition()[0], this.currentPlayer.getPosition()[1], this.currentPlayer) != null) {
                    this.concretePlateau.getArme(this.currentPlayer.getPosition()[0], this.currentPlayer.getPosition()[1], this.currentPlayer).detonation();
                    this.concretePlateau.getCase(this.currentPlayer.getPosition()[0], this.currentPlayer.getPosition()[1]).setWeapon(null);
                }
                break;
            case MINE:
                this.action.poseMine(this.currentPlayer, direction, visible);
                break;
            case BOMBE:
                this.action.poseBombe(this.currentPlayer, direction, visible);
                break;
            case TIR:
                this.action.fire(this.currentPlayer, direction);
                break;
            case BOUCLIER:
                this.action.bouclier(this.currentPlayer);
                break;
            case RIENFAIRE:
                this.action.neRienFaire(this.currentPlayer);
                break;
        }
    }

    @Override
    public String toString() {
        System.out.println("Affichage du plateau du joueur" + this.currentPlayer);
        boolean danslejoueur = false;
        for (int x = 0; x < this.concretePlateau.getTaille()+1; x++) {
            System.out.println("");
            for (int y = 0; y < this.concretePlateau.getTaille()+1; y++) {
                danslejoueur = false;
                for(Personnage joueur : this.listeJoueurs){
                    if(joueur.getPosition()[0] == x && joueur.getPosition()[1] == y){
                        System.out.print("J ");
                        danslejoueur = true;
                    }
                }
                if(danslejoueur){
                    continue;
                }
                else if(this.proxyPlateau.getPlateau()[x][y].getWall()) {
                    System.out.print("+ ");
                } else if (this.proxyPlateau.getArme(x, y, this.currentPlayer) != null) {
                    System.out.print("A ");
                } else {
                    System.out.print("0 ");
                }
            }
        }
        return "";
    }
}

