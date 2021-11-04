package lanceur;

import model.Model;
import personnagesJeu.Personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Terminal {
    protected final int[] HAUT = new int[]{-1,0};
    protected final int[] BAS = new int[]{1,0};
    protected final int[] GAUCHE = new int[]{0,-1};
    protected final int[] DROITE = new int[]{0,1};
    protected final int[] HAUTDROITE = new int[]{-1,1};
    protected final int[] HAUTGAUCHE = new int[]{-1,-1};
    protected final int[] BASDROITE = new int[]{1,1};
    protected final int[] BASGAUCHE = new int[]{1,-1};

    public static void main(String[] args) {
        Personnage joueur1 = new Personnage("J1");
        Personnage joueur2 = new Personnage("J2");
        ArrayList<Personnage> listePersonnage = new ArrayList<>();
        listePersonnage.add(joueur1);
        listePersonnage.add(joueur2);
        Model model = new Model(10,listePersonnage);
        Scanner scanner = new Scanner(System.in);
        while(!model.isOver()){
            System.out.println(model);
            System.out.println("C'est au tour du joueur : " + model.getCurrentPlayer());
            System.out.println("Quelle action voulez vous faire?");
            int action = scanner.nextInt();
            System.out.println("Si c'est un déplacement, dans qu'elle direction voulez-vous allez?");
            int deplacement = scanner.nextInt();
            int[] trueDeplacement;
            switch (deplacement){
                case 0:
                    trueDeplacement = new int[]{-1,0};
                    break;
                case 1:
                    trueDeplacement = new int[]{-1,1};
                    break;
                case 2:
                    trueDeplacement = new int[]{0,1};
                    break;
                case 3:
                    trueDeplacement = new int[]{1,1};
                    break;
                case 4:
                    trueDeplacement = new int[]{1,0};
                    break;
                case 5:
                    trueDeplacement = new int[]{1,-1};
                    break;
                case 6:
                    trueDeplacement = new int[]{0,-1};
                    break;
                case 7:
                    trueDeplacement = new int[]{-1,-1};
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + deplacement);
            }
            System.out.println("Visible ou non?");
            int visible = scanner.nextInt();
            boolean trueVisible = false;
            if(visible == 0){
                trueVisible = false;
            }
            else{
                trueVisible = true;
            }
            model.action(action,trueDeplacement,trueVisible);
        }
    }
}
