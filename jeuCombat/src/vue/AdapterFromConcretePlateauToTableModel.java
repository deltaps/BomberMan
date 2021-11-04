package vue;

import model.Case;
import model.ConcretePlateau;
import observer.ModelListener;

import javax.swing.table.AbstractTableModel;

public class AdapterFromConcretePlateauToTableModel extends AbstractTableModel implements ModelListener {

    public ConcretePlateau concretePlateau;

    public AdapterFromConcretePlateauToTableModel(ConcretePlateau concretePlateau) {
        this.concretePlateau = concretePlateau;
    }

    @Override
    public int getRowCount() {
        return this.concretePlateau.getTaille();
    }

    @Override
    public int getColumnCount() {
        return this.concretePlateau.getTaille();
    }

    @Override
    public Case getValueAt(int rowIndex, int columnIndex) {
        return this.concretePlateau.getCase(rowIndex, columnIndex);
    }

    @Override
    public void somethingHasChanged(Object source) {
        fireTableDataChanged();
    }
}
