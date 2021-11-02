/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnagesJeu;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.ParserFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

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
        xmlRead();
        this.position = new int[]{0, 0};
        this.bouclier = false;
    }

    public void xmlRead(){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            Handler handler = new Handler();

            saxParser.parse("..\\personnageJeu\\src\\personnagesJeu\\personnage-option.xml", handler);
            String munition = "";
            for(char character : handler.getMunition()){
                munition+= character;
            }
            String energie = "";
            for(char character : handler.getEnergie()){
                energie+= character;
            }
            this.munition = Integer.parseInt(munition);
            this.energie = Integer.parseInt(energie);
        }
        catch (Exception e) {
            System.out.println(e);
        }
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
