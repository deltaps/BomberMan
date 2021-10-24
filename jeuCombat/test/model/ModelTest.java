package model;

import org.junit.jupiter.api.Test;
import personnagesJeu.Personnage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

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
    void action() {

    }
}