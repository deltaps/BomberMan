package vue;

import personnagesJeu.Personnage;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class AdapterFromListeJoueursToTableModel implements TableModel {

    private List<Personnage> joueurs;
    private final int NUMERO = 0;
    private final int JOUEUR = 1;
    private final int ENERGIE = 2;

    public AdapterFromListeJoueursToTableModel(List<Personnage> joueurs) {
        this.joueurs = joueurs;
    }
    @Override
    public int getRowCount() {
        return this.joueurs.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case NUMERO -> "ID";
            case JOUEUR -> "Joueur";
            case ENERGIE -> "Vie";
            default -> null;
        };
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
        Personnage p = this.joueurs.get(rowIndex);
        return switch (columnIndex) {
            case NUMERO -> rowIndex;
            case JOUEUR -> p.getName();
            case ENERGIE -> p.getEnergie();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        this.joueurs.set(rowIndex, (Personnage) aValue);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
