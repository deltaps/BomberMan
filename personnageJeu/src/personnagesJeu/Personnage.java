/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnagesJeu;

/**
 *
 * @author pronost
 */
public class Personnage {
    protected String name;
    protected int energie;
    protected int munition;
    protected int[] position;
    protected boolean bouclier;

    public Personnage(String name){
        this.name = name;
        this.energie = 1;
        this.munition = 5;
        this.position = new int[]{0, 0};
        this.bouclier = false;
    }

    @Override
    public String toString() {
        return "Nom du personnage : " + this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergie() {
        return energie;
    }

    public void setEnergie(int energie) {
        this.energie = energie;
    }

    public int getMunition() {
        return munition;
    }

    public void setMunition(int munition) {
        this.munition = munition;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public boolean getBouclier() {
        return bouclier;
    }

    public void setBouclier(boolean bouclier) {
        this.bouclier = bouclier;
    }

    public int[] getPosition() {
        return this.position;
    }

    public void addEnergie(int add) {
        this.energie += add;
    }
}
