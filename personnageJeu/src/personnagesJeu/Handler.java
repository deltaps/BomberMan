package personnagesJeu;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class Handler extends DefaultHandler {
    protected ArrayList<Character> munition;
    protected ArrayList<Character> energie;
    protected String update;
    public Handler(){
        this.munition = new ArrayList<>();
        this.energie = new ArrayList<>();
    }
    /*
    public void startDocument(){
        System.out.println("Début du document");
    }

    public void endDocument(){
        System.out.println("Fin du document");
    }
     */

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts){
        this.update = qName;
        // les attributes, il faut faire une boucle sur la length puis affiché le getQname etc avec des getters
    }

    public void endElement(String namespaceURI, String localName, String qName){
        this.update = "null";
    }

    public void characters(char[] ch, int indiceDebut, int longueur){
        ArrayList<Character> liste = new ArrayList<>();
        for(int i=0;i<longueur;i++){
            liste.add(ch[indiceDebut+i]);
        }
        switch (this.update){
            case "munitions":
                this.munition = liste;
                break;
            case "energie":
                this.energie = liste;
                break;

        }
    }

    public ArrayList<Character> getMunition() {
        return munition;
    }

    public ArrayList<Character> getEnergie() {
        return energie;
    }
}