package vue;

import model.Case;
import model.ProxyPlateau;
import observer.ModelListener;

import javax.swing.table.AbstractTableModel;

public class AdapterFromProxyPlateauToTableModel extends AbstractTableModel implements ModelListener {

    public ProxyPlateau proxyPlateau;

    public AdapterFromProxyPlateauToTableModel(ProxyPlateau proxyPlateau) {
        this.proxyPlateau = proxyPlateau;
    }

    @Override
    public int getRowCount() {
        return this.proxyPlateau.getTaille();
    }

    @Override
    public int getColumnCount() {
        return this.proxyPlateau.getTaille();
    }

    @Override
    public Case getValueAt(int rowIndex, int columnIndex) {
        return this.proxyPlateau.getCase(rowIndex, columnIndex);
    }

    @Override
    public void somethingHasChanged(Object source) {
        fireTableDataChanged();
    }
}
