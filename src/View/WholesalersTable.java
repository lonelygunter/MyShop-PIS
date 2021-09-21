package View;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import DAO.WholesalerDAO;
import Model.Wholesaler;

public class WholesalersTable extends AbstractTableModel {

    private final int NUMBER_OF_COLUMNS = 7;

    private List<Wholesaler> list;
    private String sql;

    public WholesalersTable(String sql) {
        this.sql = sql;
        this.list = WholesalerDAO.getInstance().findAllOrderBy(sql);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return(columnIndex == 1);
    }

    @Override
    public void setValueAt( Object value, int rowIndex, int columnIndex) {
       Wholesaler p = list.get(rowIndex);
        if (columnIndex == 1) {
            p.setName(value.toString());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Id";
            case 1: return "Nome";
            case 2: return "Email";
            case 3: return "Telefono";
            case 4: return "Website";
            case 5: return "Citta'";
            case 6: return "Nazione";
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
        Wholesaler w = list.get(rowIndex);

        switch (columnIndex) {
            case 0: return w.getId();
            case 1: return w.getName();
            case 2: return w.getEmail();
            case 3: return w.getTelephone();
            case 4: return w.getWebsite();
            case 5: return w.getCity();
            case 6: return w.getNation();
        }
        return null;
    }

    public List<Wholesaler> getlist() {
        return list;
    }
}