package model;

import org.junit.jupiter.api.Test;
import personnagesJeu.Personnage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    @Test
    void constructeur(){
        Personnage j1 = new Personnage("Mathieu");
        Personnage j2 = new Personnage("Aurélien");
        List<Personnage> listePersonnage = new ArrayList<>();
        listePersonnage.add(j1);
        listePersonnage.add(j2);
        for(int i = 0; i<99999; i++){
            Model model = new Model(10,listePersonnage);
            ConcretePlateau plateau = model.getPlateau();
            assertFalse(plateau.getPastille(j1.getPosition()[0],j1.getPosition()[1]));
            assertFalse(plateau.getPastille(j2.getPosition()[0],j2.getPosition()[1]));
            assertFalse(plateau.getPlateau()[j1.getPosition()[0]][j1.getPosition()[1]].getWall());
            assertFalse(plateau.getPlateau()[j2.getPosition()[0]][j2.getPosition()[1]].getWall());
        }
    }
    @Test
    void isOver() {
        Personnage j1 = new Personnage("Mathieu");
        Personnage j2 = new Personnage("Aurélien");
        Personnage j3 = new Personnage("Jacob");
        Personnage j4 = new Personnage("Gurvan");
        List<Personnage> listePersonnage = new ArrayList<>();
        listePersonnage.add(j1);
        listePersonnage.add(j2);
        listePersonnage.add(j3);
        listePersonnage.add(j4);
        Model model = new Model(10,listePersonnage);
        j1.setEnergie(0);
        assertFalse(model.isOver());
        j2.setEnergie(0);
        assertFalse(model.isOver());
        j3.setEnergie(0);
        assertTrue(model.isOver());
    }

    @Test
    void changePlayer() {
        Personnage j1 = new Personnage("Mathieu");
        Personnage j2 = new Personnage("Aurélien");
        Personnage j3 = new Personnage("Jacob");
        Personnage j4 = new Personnage("Gurvan");
        List<Personnage> listePersonnage = new ArrayList<>();
        listePersonnage.add(j1);
        listePersonnage.add(j2);
        listePersonnage.add(j3);
        listePersonnage.add(j4);
        Model model = new Model(10,listePersonnage);
        Personnage currentPlayer = model.getCurrentPlayer();
        model.changePlayer();
        assertFalse(currentPlayer == model.getCurrentPlayer());
        Personnage currentPlayer2 = model.getCurrentPlayer();
        model.changePlayer();
        assertFalse(currentPlayer2 == model.getCurrentPlayer());
        currentPlayer = model.getCurrentPlayer();
        model.changePlayer();
        assertFalse(currentPlayer == model.getCurrentPlayer());
        currentPlayer = model.getCurrentPlayer();
        model.changePlayer();
        assertFalse(currentPlayer == model.getCurrentPlayer());
        currentPlayer = model.getCurrentPlayer();
        model.changePlayer();
        assertFalse(currentPlayer == model.getCurrentPlayer());
    }

    @Test
    void testAction() {
        Personnage j1 = new Personnage("Aurelien");
        int energiej1 = j1.getEnergie();
        Personnage j2 = new Personnage("Mathieu");
        int energiej2 = j2.getEnergie();
        ArrayList<Personnage> listePersonnage = new ArrayList<>();
        listePersonnage.add(j1);
        listePersonnage.add(j2);
        Model model = new Model(8,listePersonnage);
        j1.setPosition(new int[]{2,3});
        j2.setPosition(new int[]{3,2});
        model.action(model.MINE, model.HAUTGAUCHE, true);
        energiej1--;// Le j1 ce déplace de 1
        model.changePlayer();
        model.action(model.DEPLACEMENT, model.HAUT, true);
        energiej2--;// Le j2 ce déplace de 1
        model.action(model.DEPLACEMENT, model.HAUT, true);
        energiej2--;// le j2 ce déplace de 1
        energiej2--;// le j2 ce prend la bombe
        energiej1--;// le j1 ce la prend aussi
        assertTrue(model.getPlateau().getCase(1,2).getWeapon() == null);
        assertTrue(j2.getEnergie() == energiej2);
        assertTrue(j1.getEnergie() == energiej1);

        model.changePlayer();
        j1.setEnergie(20);
        j2.setEnergie(20);
        energiej1 = 20;
        energiej2 = 20;
        j1.setPosition(new int[]{2,3});
        j2.setPosition(new int[]{3,2});
        model.action(model.BOMBE, model.HAUTGAUCHE, true);
        energiej1--;// Le j1 ce déplace de 1
        model.changePlayer();
        model.action(model.DEPLACEMENT, model.HAUT, true);
        energiej2--;// Le j2 ce déplace de 1
        model.action(model.DEPLACEMENT, model.HAUT, true);
        energiej2--;// le j2 ce déplace de 1
        energiej2--;// le j2 ce prend la bombe
        energiej1--;// le j1 ce la prend aussi
        assertTrue(model.getPlateau().getCase(1,2).getWeapon() == null);
        assertTrue(j2.getEnergie() == energiej2);
        assertTrue(j1.getEnergie() == energiej1);

        model.changePlayer();
        model.action(model.BOMBE, model.HAUTGAUCHE, true);
        assertTrue(model.getPlateau().getCase(1,2).getWeapon() != null);
        model.changePlayer();
        model.changePlayer();
        assertFalse(model.getPlateau().getCase(1,2).getWeapon() == null);
        model.changePlayer();
        model.changePlayer();
        assertFalse(model.getPlateau().getCase(1,2).getWeapon() == null);
        model.changePlayer();
        model.changePlayer();
        assertTrue(model.getPlateau().getCase(1,2).getWeapon() == null);

        Personnage Mathieu = new Personnage("Mathieu");
        int energieMathieu = Mathieu.getEnergie();
        List<Personnage> listePersonnage2 = new ArrayList<>();
        listePersonnage2.add(Mathieu);
        Model model2 = new Model(10,listePersonnage2);
        Plateau plateauTeste = new ConcretePlateau(listePersonnage2,10,true); //bah alors ça fail les tests
        plateauTeste.getPlateau()[1][1].setPastille(true);
        Mathieu.setPosition(new int[]{1,2});
        model2.action(model.DEPLACEMENT, model.GAUCHE,true);
        assertTrue(plateauTeste.getPlateau()[1][1].getPastille());
        assertEquals(Mathieu.getEnergie(), energieMathieu + 4);
        assertFalse(plateauTeste.getPlateau()[1][1].getPastille());
    }
}