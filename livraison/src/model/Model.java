package model;

import observer.AbstractListenableModel;
import personnagesJeu.Personnage;

import java.util.List;
import java.util.Random;
//Class représentant notre jeu, donc appelant toutes les autres class de notre model.
public class Model extends AbstractListenableModel {
    //TODO UML
    //TODO RAPPORT
    //Ensemble des variables finales permettant de faire des actions, et de diriger l'action. ----
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
    //---------------------------------------------------------------------------------------------
    //Le model possède donc un plateau, les actions possibles, le personnage courant, la liste des personnages, ainsi qu'un proxyPlateau.
    protected ConcretePlateau concretePlateau;
    protected Action action;
    protected Personnage currentPlayer;
    protected List<Personnage> listeJoueurs;
    protected ProxyPlateau proxyPlateau;

    public Model(int taillePlateau, List<Personnage> listeJoueurs){//Le constructeur construit donc le plateau et positionne les joueurs.
        this.concretePlateau = new ConcretePlateau(listeJoueurs, taillePlateau);
        Random random = new Random();
        for(Personnage joueur : listeJoueurs){
            while(true){
                int x = random.nextInt(taillePlateau-3);
                x++;
                int y = random.nextInt(taillePlateau-3);
                y++;
                boolean pasSurJoueur = true;
                for(Personnage joueurAdverse : listeJoueurs){
                    if(joueurAdverse != joueur){
                        if(joueurAdverse.getPosition()[0] == x && joueurAdverse.getPosition()[1] == y){
                            pasSurJoueur = false;
                        }
                    }
                }
                if(!this.concretePlateau.getPlateau()[x][y].getWall() && !this.concretePlateau.getPastille(x,y) && pasSurJoueur){
                    joueur.setPosition(new int[]{x,y});
                    break;
                }
            }
        }
        this.listeJoueurs = listeJoueurs;
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

    public boolean isOver() { //Méthode permettant de savoir si le jeu est fini ou non.
        int nbJoueurs = this.listeJoueurs.size();
        int nbJoueursDown = 0;
            for (Personnage joueur : this.listeJoueurs) {
            if (joueur.getEnergie() <= 0) {
                nbJoueursDown++;
            }
        }
        if (nbJoueursDown >= nbJoueurs - 1) {//On vérifie si tous les joueurs sont mort (-1) si c'est le cas c'est fini.
            return true;
        }
        return false;
    }

    public void changePlayer() { //Méthode permettant de passer aux joueurs suivants, d'incrémenter les compteurs de bombes et de supprimer un joueur de la liste s'il est mort.
        for (int n = 0; n < this.listeJoueurs.size(); n++) {
            if(this.listeJoueurs.get(n).getEnergie() <= 0){//Vérification si le joueur est mort.
                this.concretePlateau.getJoueurs().remove(this.listeJoueurs.get(n));
                this.listeJoueurs.remove(n);
            }
            if (this.listeJoueurs.get(n) == this.currentPlayer) {
                if (n == this.listeJoueurs.size() - 1) {//Si nous somme au bout de la liste, nous revenons au premier joueur.
                    this.currentPlayer = this.listeJoueurs.get(0);
                    this.compteurBombe(this.currentPlayer); //On incrémente toutes les bombes de ce joueur.
                    this.currentPlayer.setBouclier(false); //On désactive son bouclier.
                    break;
                }
                else {
                    this.currentPlayer = this.listeJoueurs.get(n + 1); //On prend le prochain joueur.
                    this.compteurBombe(this.currentPlayer); //On incrémente toutes les bombes de ce joueur.
                    this.currentPlayer.setBouclier(false); //On désactive son bouclier.
                    break;
                }
            }
        }
        fireChange(); // on prévient les écouteurs des changements
    }

    public void compteurBombe(Personnage player){
        for(Case[] casex : this.concretePlateau.getPlateau()){ // pour toutes les lignes
            for(Case casey : casex){ // pour toutes les cases de ces lignes
                if(casey.getWeapon() != null){ // Si il y a une arme
                    if (casey.getWeapon() instanceof Bomb && casey.getWeapon().getOwner() == player){ // Que cette arme est une bombe qui appartient au joueur
                        ((Bomb) casey.getWeapon()).tictac(); // on décrémente son compte à rebours
                        if(((Bomb) casey.getWeapon()).getCompteARebourt() == 0){ // Si son compte à rebours est à 0
                            casey.getWeapon().detonation(); // on la fait exploser
                            casey.setWeapon(null); // on la supprime de la case
                        }
                    }
                }
            }
        }
    }

    public void action(int action, int[] direction, boolean visible){
        Case caseCourante = this.concretePlateau.getCase(this.currentPlayer.getPosition()[0]+direction[0],this.currentPlayer.getPosition()[1]+direction[1]); // on get la case concerner par le déplacement
        switch (action) {
            case DEPLACEMENT: // Si on veut faire un déplacement
                this.action.deplacement(this.currentPlayer, direction); // on déplace le joueur
                if (this.concretePlateau.getArme(this.currentPlayer.getPosition()[0], this.currentPlayer.getPosition()[1], this.currentPlayer) != null) { // Si le joueur se trouve sur une arme
                    this.concretePlateau.getArme(this.currentPlayer.getPosition()[0], this.currentPlayer.getPosition()[1], this.currentPlayer).detonation(); // l'arme explose
                    this.concretePlateau.getCase(this.currentPlayer.getPosition()[0], this.currentPlayer.getPosition()[1]).setWeapon(null); // on supprime l'arme
                }
                if(this.concretePlateau.getPlateau()[this.currentPlayer.getPosition()[0]][this.currentPlayer.getPosition()[1]].getPastille()){ // Si le joueur est sur une pastille d'énérgie
                    this.currentPlayer.addEnergie(5); // Le joueur gagne 5 énergies
                    this.concretePlateau.getPlateau()[this.currentPlayer.getPosition()[0]][this.currentPlayer.getPosition()[1]].setPastille(false); // on supprime la pastille
                }
                break;
            case MINE: // Si on veut placer une mine
                Boolean pasSurJoueur = true;
                for(Personnage joueurAdv : this.listeJoueurs){
                    if(this.concretePlateau.getCase(joueurAdv.getPosition()[0],joueurAdv.getPosition()[1]) == caseCourante){
                        pasSurJoueur = false; // on vérifie qu'aucun joueur n'est sur la case ou on veut poser la mine
                    }
                }
                if(caseCourante.getWeapon() == null && !caseCourante.getPastille() && pasSurJoueur){ // S'il n'y a pas déjà une arme et qu'aucun joueur n'occupe la case
                    this.action.poseMine(this.currentPlayer, direction, visible); // on pose la mine
                }
                break;
            case BOMBE: // Si on veut placer une bombe
                pasSurJoueur = true;
                for(Personnage joueurAdv : this.listeJoueurs){
                    if(this.concretePlateau.getCase(joueurAdv.getPosition()[0],joueurAdv.getPosition()[1]) == caseCourante){
                        pasSurJoueur = false; // on vérifie qu'aucun joueur n'est sur la case ou on veut poser la bombe
                    }
                }
                if(caseCourante.getWeapon() == null && !caseCourante.getPastille() && pasSurJoueur){ // S'il n'y a pas déjà une arme et qu'aucun joueur n'occupe la case
                    this.action.poseBombe(this.currentPlayer, direction, visible); // on pose la bombe
                }
                break;
            case TIR: // Si on veut tirer
                this.action.fire(this.currentPlayer, direction); // on tire dans la direction voulue
                this.changePlayer(); // on change de joueur
                break;
            case BOUCLIER: // Si on veut se protéger
                this.action.bouclier(this.currentPlayer); // on lance l'action pour se protéger
                this.changePlayer(); // on change de joueur
                break;
            case RIENFAIRE: // Si on veut ne rien faire
                this.action.neRienFaire(this.currentPlayer); // on lance l'action neRienFaire
                this.changePlayer(); // on change de joueur
                break;
        }
        fireChange(); // on prévient les écouteurs des changements
    }

    @Override
    public String toString() {
        System.out.println("Affichage du plateau du joueur" + this.currentPlayer);
        boolean danslejoueur = false;
        for (int x = 0; x < this.concretePlateau.getTaille(); x++) {
            System.out.println("");
            for (int y = 0; y < this.concretePlateau.getTaille(); y++) {
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
                }
                else if(this.concretePlateau.getPastille(x,y)){
                    System.out.print("P ");
                }
                else {
                    System.out.print("0 ");
                }
            }
        }
        return "";
    }
}

