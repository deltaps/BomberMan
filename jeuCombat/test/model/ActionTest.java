package model;

import personnagesJeu.Personnage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {
    protected final int[] HAUT = new int[]{-1,0};
    protected final int[] BAS = new int[]{1,0};
    protected final int[] GAUCHE = new int[]{0,-1};
    protected final int[] DROITE = new int[]{0,1};
    protected final int[] HAUTDROITE = new int[]{-1,1};
    protected final int[] HAUTGAUCHE = new int[]{-1,-1};
    protected final int[] BASDROITE = new int[]{1,1};
    protected final int[] BASGAUCHE = new int[]{1,-1};
    @org.junit.jupiter.api.Test
    void deplacement() {
        Personnage perso = new Personnage("Jean");
        perso.setPosition(new int[]{1,1});
        int energiePerso = perso.getEnergie();
        List<Personnage> liste = new ArrayList<>();
        liste.add(perso);
        ConcretePlateau concretePlateau = new ConcretePlateau(liste,true);
        Action action = new Action(concretePlateau);
        action.deplacement(perso,HAUT);
        assertTrue(perso.getPosition()[0] == 0 && perso.getPosition()[1] == 1 && perso.getEnergie() == energiePerso-1);
        perso.setPosition(new int[]{1,1});
        action.deplacement(perso,DROITE);
        assertTrue(perso.getPosition()[0] == 1 && perso.getPosition()[1] == 2 && perso.getEnergie() == energiePerso-2);
        perso.setPosition(new int[]{1,1});
        action.deplacement(perso,GAUCHE);
        assertTrue(perso.getPosition()[0] == 1 && perso.getPosition()[1] == 0 && perso.getEnergie() == energiePerso-3);
        perso.setPosition(new int[]{1,1});
        action.deplacement(perso,BAS);
        assertTrue(perso.getPosition()[0] == 2 && perso.getPosition()[1] == 1 && perso.getEnergie() == energiePerso-4);
        perso.setPosition(new int[]{0,1});
        action.deplacement(perso,DROITE);
        assertTrue(perso.getPosition()[0] == 0 && perso.getPosition()[1] == 1 && perso.getEnergie() == energiePerso-4);
        perso.setPosition(new int[]{1,2});
        action.deplacement(perso,HAUT);
        assertTrue(perso.getPosition()[0] == 1 && perso.getPosition()[1] == 2 && perso.getEnergie() == energiePerso-4);
        perso.setPosition(new int[]{1,0});
        action.deplacement(perso,BAS);
        assertTrue(perso.getPosition()[0] == 1 && perso.getPosition()[1] == 0 && perso.getEnergie() == energiePerso-4);
        perso.setPosition(new int[]{2,1});
        action.deplacement(perso,GAUCHE);
        assertTrue(perso.getPosition()[0] == 2 && perso.getPosition()[1] == 1 && perso.getEnergie() == energiePerso-4);
        perso.setEnergie(0);
        perso.setPosition(new int[]{1,1});
        action.deplacement(perso,HAUT);
        assertTrue(perso.getPosition()[0] == 1 && perso.getPosition()[1] == 1);
    }

    @org.junit.jupiter.api.Test
    void poseMine() {
        Personnage joueur = new Personnage("Jean");
        int energieJoueur = joueur.getEnergie();
        List<Personnage> liste = new ArrayList<>();
        joueur.setPosition(new int[]{1,1});
        liste.add(joueur);
        ConcretePlateau concretePlateau = new ConcretePlateau(liste,true);
        Action action = new Action(concretePlateau);
        action.poseMine(joueur,HAUT,true);
        assertTrue(concretePlateau.getCase(0,1).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-1);
        action.poseMine(joueur,HAUTDROITE,true);
        assertFalse(concretePlateau.getCase(0,2).getWeapon() instanceof LandMine);
        assertTrue(joueur.getEnergie() == energieJoueur-1 );
        action.poseMine(joueur,DROITE,true);
        assertTrue(concretePlateau.getCase(1,2).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-2);
        action.poseMine(joueur,BASDROITE,true);
        assertTrue(concretePlateau.getCase(2,2).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-3);
        action.poseMine(joueur,BAS,true);
        assertTrue(concretePlateau.getCase(2,1).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-4);
        action.poseMine(joueur,BASGAUCHE,true);
        assertFalse(concretePlateau.getCase(2,0).getWeapon() instanceof LandMine);
        assertTrue(joueur.getEnergie() == energieJoueur-4);
        action.poseMine(joueur,GAUCHE,true);
        assertTrue(concretePlateau.getCase(1,0).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-5);
        action.poseMine(joueur,HAUTGAUCHE,false);
        assertTrue(concretePlateau.getCase(0,0).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-7);
        joueur.setEnergie(0);
        ConcretePlateau concretePlateau2 = new ConcretePlateau(liste,true);
        Action action2 = new Action(concretePlateau);
        joueur.setPosition(new int[]{1,1});
        action2.poseMine(joueur,HAUT,false);
        assertFalse(concretePlateau2.getCase(0,1).getWeapon() instanceof LandMine);
    }
    @org.junit.jupiter.api.Test
    void poseBombe() {
        Personnage joueur = new Personnage("Jean");
        int energieJoueur = joueur.getEnergie();
        List<Personnage> liste = new ArrayList<>();
        joueur.setPosition(new int[]{1,1});
        liste.add(joueur);
        ConcretePlateau concretePlateau = new ConcretePlateau(liste,true);
        Action action = new Action(concretePlateau);
        action.poseBombe(joueur,HAUT,true);
        assertTrue(concretePlateau.getCase(0,1).getWeapon() instanceof Bomb && joueur.getEnergie() == energieJoueur-1 && ((Bomb) concretePlateau.getCase(0,1).getWeapon()).getOwner() == joueur);
        action.poseBombe(joueur,HAUTDROITE,true);
        assertFalse(concretePlateau.getCase(0,2).getWeapon() instanceof LandMine);
        assertTrue(joueur.getEnergie() == energieJoueur-1 );
        action.poseBombe(joueur,DROITE,true);
        assertTrue(concretePlateau.getCase(1,2).getWeapon() instanceof Bomb && joueur.getEnergie() == energieJoueur-2);
        action.poseBombe(joueur,BASDROITE,true);
        assertTrue(concretePlateau.getCase(2,2).getWeapon() instanceof Bomb && joueur.getEnergie() == energieJoueur-3);
        action.poseBombe(joueur,BAS,true);
        assertTrue(concretePlateau.getCase(2,1).getWeapon() instanceof Bomb && joueur.getEnergie() == energieJoueur-4);
        action.poseBombe(joueur,BASGAUCHE,true);
        assertFalse(concretePlateau.getCase(2,0).getWeapon() instanceof Bomb);
        assertTrue(joueur.getEnergie() == energieJoueur-4);
        action.poseBombe(joueur,GAUCHE,true);
        assertTrue(concretePlateau.getCase(1,0).getWeapon() instanceof Bomb && joueur.getEnergie() == energieJoueur-5);
        action.poseBombe(joueur,HAUTGAUCHE,false);
        assertTrue(concretePlateau.getCase(0,0).getWeapon() instanceof Bomb && joueur.getEnergie() == energieJoueur-7);
        joueur.setEnergie(0);
        ConcretePlateau concretePlateau2 = new ConcretePlateau(liste,true);
        Action action2 = new Action(concretePlateau);
        joueur.setPosition(new int[]{1,1});
        action2.poseBombe(joueur,HAUT,true);
        assertFalse(concretePlateau2.getCase(0,1).getWeapon() instanceof Bomb);
    }

    @org.junit.jupiter.api.Test
    void fire() { //Dans ce test je pars du principe que le tir fait perdre 2 d'énergie a un joueur, et que tirer ne fait pas perdre d'énergie au tireur, il faut donc que l'on se mette d'accord sur ça.
        Personnage joueur = new Personnage("Jean");
        int energieJ1 = joueur.getEnergie();
        int munitionJ1 = joueur.getMunition();
        Personnage joueur2 = new Personnage("Pierre");
        int energieJ2 = joueur2.getEnergie();
        int munitionJ2 = joueur2.getMunition();
        List<Personnage> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(joueur);
        listeJoueurs.add(joueur2);
        ConcretePlateau concretePlateau = new ConcretePlateau(listeJoueurs,5,true);
        Action action = new Action(concretePlateau);
        joueur.setPosition(new int[]{1,1});
        joueur2.setPosition(new int[]{3,1});
        action.fire(joueur,BAS);
        assertTrue(joueur.getMunition() == munitionJ1-1 && joueur2.getEnergie() == energieJ2-2);
        joueur.setPosition(new int[]{1,2});
        joueur2.setPosition(new int[]{3,2});
        action.fire(joueur2,GAUCHE);
        assertNotEquals(joueur.getEnergie(), energieJ1 - 2);
        assertEquals(joueur2.getMunition(), munitionJ2 - 1);
        action.fire(joueur2,HAUT);
        assertTrue(joueur.getEnergie() == energieJ1-2);
    }

    @org.junit.jupiter.api.Test
    void bouclier(){
        Personnage joueur = new Personnage("Jean");
        int energieJ1 = joueur.getEnergie();
        Personnage joueur2 = new Personnage("Pierre");
        List<Personnage> listeJoueur = new ArrayList<>();
        listeJoueur.add(joueur);
        listeJoueur.add(joueur2);
        ConcretePlateau concretePlateau = new ConcretePlateau(listeJoueur,5,true);
        concretePlateau.getCase(2,2).setWall(true);
        Action action = new Action(concretePlateau);
        assertFalse(joueur.getBouclier());
        action.bouclier(joueur);
        assertTrue(joueur.getBouclier());
        assertFalse(joueur2.getBouclier());
        assertEquals(joueur.getEnergie(), energieJ1 - 1);
        joueur.setPosition(new int[]{1,1});
        joueur2.setPosition(new int[]{3,1});
        action.fire(joueur,DROITE);
        assertEquals(joueur.getEnergie(), energieJ1 -1);
    }

    @org.junit.jupiter.api.Test
    void neRienFaire() {
        Personnage joueur = new Personnage("Jean");
        int energieJoueur = joueur.getEnergie();
        int posistionX = joueur.getPosition()[0];
        int positionY = joueur.getPosition()[1];
        List<Personnage> listePersonnage = new ArrayList<>();
        listePersonnage.add(joueur);
        ConcretePlateau concretePlateau = new ConcretePlateau(listePersonnage,5,true);
        Action action = new Action(concretePlateau);
        action.neRienFaire(joueur);
        assertEquals(joueur.getEnergie(), energieJoueur + 1);
        assertTrue(joueur.getPosition()[0] == posistionX && joueur.getPosition()[1] == positionY);
    }
}