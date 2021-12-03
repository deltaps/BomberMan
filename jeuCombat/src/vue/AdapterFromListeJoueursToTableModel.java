package vue;

import personnagesJeu.Personnage;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

/*
Classe utilisant le pattern Adapter pour convertir une liste de joueur en TableModel afin d'en faire un tableau
(le tableau en bas à droite de l'écran).
Elle redéfinie toutes les méthodes le cette interface.
 */

public class AdapterFromListeJoueursToTableModel implements TableModel {

    private List<Personnage> joueurs;
    private final int ID = 0;
    private final int JOUEUR = 1;
    private final int ENERGIE = 2;

    public AdapterFromListeJoueursToTableModel(List<Personnage> joueurs) {
        this.joueurs = joueurs;
    }
    @Override
    public int getRowCount() {
        return this.joueurs.size()+1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case ID:
                return "ID";
            case JOUEUR:
                return "Nom";
            case ENERGIE:
                return "Énergie";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0,columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex == 0) {
            switch (columnIndex){
                case ID:
                    return "ID";
                case JOUEUR:
                    return "Nom";
                case ENERGIE:
                    return "Énergie";
                default:
                    return null;
            }
        }
        else {
            rowIndex -= 1;
            Personnage p = this.joueurs.get(rowIndex);
            switch (columnIndex){
                case ID:
                    return rowIndex+1;
                case JOUEUR:
                    return p.getName();
                case ENERGIE:
                    return p.getEnergie();
                default:
                    return null;
            }
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        this.joueurs.set(rowIndex, ((Personnage) aValue));
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
