package personnagesJeu;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class Handler extends DefaultHandler {
    protected ArrayList<Character> munition;
    protected ArrayList<Character> energie;
    protected ArrayList<Character> pastille;
    protected String update;
    public Handler(){
        this.munition = new ArrayList<>();
        this.energie = new ArrayList<>();
        this.pastille = new ArrayList<>();
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts){
        this.update = qName; //Permets de savoir si nous sommes dans la balise de munitions ou d'énergie lorsque nous allons lire les caractères de la balise.
    }

    public void endElement(String namespaceURI, String localName, String qName){
        this.update = "null";
    }

    public void characters(char[] ch, int indiceDebut, int longueur){
        ArrayList<Character> liste = new ArrayList<>();
        for(int i=0;i<longueur;i++){
            liste.add(ch[indiceDebut+i]); //On lit tous les caractères, et on les place dans une liste provisoire.
        }
        switch (this.update){ //On regarde si nous sommes dans la balise de munitions ou d'énergie
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