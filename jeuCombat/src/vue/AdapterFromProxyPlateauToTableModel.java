package vue;

import model.Case;
import model.ProxyPlateau;
import observer.*;

public class AdapterFromProxyPlateauToTableModel extends AbstractListenableModel implements ModelListener {

    public ProxyPlateau proxyPlateau;

    public AdapterFromProxyPlateauToTableModel(ProxyPlateau proxyPlateau) {
        this.proxyPlateau = proxyPlateau;
    }

    public int getRowCount() {
        return this.proxyPlateau.getTaille();
    }

    public int getColumnCount() {
        return this.proxyPlateau.getTaille();
    }

    public Case getValueAt(int rowIndex, int columnIndex) {
        return this.proxyPlateau.getCase(rowIndex, columnIndex);
    }

    @Override
    public void somethingHasChanged(Object source) {
        fireChange();
    }
}
