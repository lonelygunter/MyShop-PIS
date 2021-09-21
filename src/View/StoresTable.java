package View;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.Store;

public class StoresTable extends AbstractTableModel {

    private final int NUMBER_OF_COLUMNS = 7;

    private List<Store> list;
    private String sql;

    public StoresTable(String sql) {
        this.sql = sql;
        this.list = StoreDAO.getInstance().findAllOrderBy(sql);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return(columnIndex == 1);
    }

    @Override
    public void setValueAt( Object value, int rowIndex, int columnIndex) {
        Store s = list.get(rowIndex);
        if (columnIndex == 1) {
            s.setId(Integer.parseInt(value.toString()));
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Id";
            case 1: return "Telefono";
            case 2: return "Indirizzo";
            case 3: return "Cap";
            case 4: return "Nazione";
            case 5: return "Id Manager";
            case 6: return "Manager";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return NUMBER_OF_COLUMNS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Store s = list.get(rowIndex);

        switch (columnIndex) {
            case 0: return s.getId();
            case 1: return s.getTelephone();
            case 2: return s.getStreet();
            case 3: return s.getCap();
            case 4: return s.getNation();
            case 5: return s.getUserId();
            case 6: return UserDAO.getInstance().findById(s.getUserId()).getUsername();
        }
        return null;
    }

    public List<Store> getlist() {
        return list;
    }
}