import model.*;
import personnagesJeu.Personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {
    protected final int HAUT = 0;
    protected final int BAS = 1;
    protected final int GAUCHE = 2;
    protected final int DROITE = 3;
    protected final int HAUTDROITE = 4;
    protected final int HAUTGAUCHE = 5;
    protected final int BASDROITE = 6;
    protected final int BASGAUCHE = 7;

    @org.junit.jupiter.api.Test
    void deplacement() {
        Personnage perso = new Personnage("Jean");
        perso.setPosition(new int[]{1,1});
        int energiePerso = perso.getEnergie();
        List<Personnage> liste = new ArrayList<>();
        liste.add(perso);
        Plateau plateau = new Plateau(liste,true);
        Action action = new Action(plateau);
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
        Plateau plateau = new Plateau(liste,true);
        Action action = new Action(plateau);
        action.poseMine(joueur,HAUT);
        assertTrue(plateau.getCase(1,0).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-1);
        action.poseMine(joueur,HAUTDROITE);
        assertFalse(plateau.getCase(2,0).getWeapon() instanceof LandMine);
        assertTrue(joueur.getEnergie() == energieJoueur-1 );
        action.poseMine(joueur,DROITE);
        assertTrue(plateau.getCase(2,1).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-2);
        action.poseMine(joueur,BASDROITE);
        assertTrue(plateau.getCase(2,2).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-3);
        action.poseMine(joueur,BAS);
        assertTrue(plateau.getCase(1,2).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-4);
        action.poseMine(joueur,BASGAUCHE);
        assertFalse(plateau.getCase(0,2).getWeapon() instanceof LandMine);
        assertTrue(joueur.getEnergie() == energieJoueur-4);
        action.poseMine(joueur,GAUCHE);
        assertTrue(plateau.getCase(0,1).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-5);
        action.poseMine(joueur,HAUTGAUCHE);
        assertTrue(plateau.getCase(0,0).getWeapon() instanceof LandMine && joueur.getEnergie() == energieJoueur-6);
        joueur.setEnergie(0);
        Plateau plateau2 = new Plateau(liste,true);
        Action action2 = new Action(plateau);
        joueur.setPosition(new int[]{1,1});
        action2.poseMine(joueur,HAUT);
        assertFalse(plateau2.getCase(1,0).getWeapon() instanceof LandMine);
    }
    //TODO crée le restes des tests
    @org.junit.jupiter.api.Test
    void poseBombe() {
    }

    @org.junit.jupiter.api.Test
    void fire() {
    }

    @org.junit.jupiter.api.Test
    void bouclier() {
    }

    @org.junit.jupiter.api.Test
    void neRienFaire() {
    }
}