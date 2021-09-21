package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import DAO.OrderDAO;
import Model.Order;

public class OrderTable extends AbstractTableModel {

    private final int NUMBER_OF_COLUMNS = 5;

    private ArrayList<Order> list;
    private String sql;

    public OrderTable(String sql) {
        this.sql = sql;
        
        this.list = OrderDAO.getInstance().findAllOrderBy(sql);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return(columnIndex == 1);
    }

    @Override
    public void setValueAt( Object value, int rowIndex, int columnIndex) {
        Order o = list.get(rowIndex);
        if (columnIndex == 1) {
            o.setId(Integer.parseInt(value.toString()));
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Id";
            case 1: return "Data di creazione";
            case 2: return "Prezzo";
            case 3: return "Articolo";
            case 4: return "Id dell'articolo";
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
        Order o = list.get(rowIndex);

        switch (columnIndex) {
            case 0: return o.getId();
            case 1: return o.getDate();
            case 2: return o.getPrice();
            case 3: return o.getItem().getName();
            case 4: return o.getItem().getId();
        }
        return null;
    }

    public List<Order> getlist() {
        return list;
    }
}
