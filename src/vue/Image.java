package vue;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    public static BufferedImage imageCase;
    public static BufferedImage imageMur;
    public static BufferedImage imageJoueur;
    public static BufferedImage imageEnnemie;
    public static BufferedImage imageBombe;
    public static BufferedImage imagePastille;
    public static BufferedImage imageMine;
    public static BufferedImage imageTir;
    public final static int TAILLE_IMAGE = 40;

    public static String DELIMITEUR_LINUX = "/";
    public static String DELIMITEUR_WINDOWS = "\\";
    public static String delimiteur;

    static {
        if (System.getProperty("os.name").equals("Windows")) {
            delimiteur = DELIMITEUR_WINDOWS;
        } else {
            delimiteur = DELIMITEUR_LINUX;
        }
        try {
            // Lis l'image (dans l'exemple de la ligne ci-dessous l'image de la case) dans le ficher images.
            // La variable delimiteur est faite pour pouvoir charger les images dans le fichier, pour les utilisateurs de Windows ou de Linux.
            imageCase = ImageIO.read(new File("./images" + delimiteur + "case.png"));
            imageMur = ImageIO.read(new File("./images" + delimiteur + "mur.png"));
            imageJoueur = ImageIO.read(new File("./images" + delimiteur + "joueur.png"));
            imageEnnemie = ImageIO.read(new File("./images" + delimiteur + "joueurEnnemi.png"));
            imageBombe = ImageIO.read(new File("./images" + delimiteur + "bomb.png"));
            imagePastille = ImageIO.read(new File("./images" + delimiteur + "pastille.png"));
            imageMine = ImageIO.read(new File("./images" + delimiteur + "mine.png"));
            imageTir = ImageIO.read(new File("./images" + delimiteur + "tir.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void resize(int width, int height) {
        /*imageCase = (BufferedImage) imageCase.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        imageMur = new BufferedImage(width, height, imageMur.getType());
        imageJoueur = new BufferedImage(width, height, imageJoueur.getType());
        imageEnnemie = new BufferedImage(width, height, imageEnnemie.getType());
        imageBombe = new BufferedImage(width, height, imageBombe.getType());
        imagePastille = new BufferedImage(width, height, imagePastille.getType());
        imageMine = new BufferedImage(width, height, imageMine.getType());
        imageTir = new BufferedImage(width, height, imageTir.getType());*/
    }
}
