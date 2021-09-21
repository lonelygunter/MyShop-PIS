package View;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import DAO.CategoryDAO;
import DAO.ItemDAO;
import DAO.WholesalerDAO;
import Model.Item;

public class ItemsTable extends AbstractTableModel {

    private final int NUMBER_OF_COLUMNS = 8;

    private List<Item> list;
    private String sql;

    public ItemsTable(String sql) {
        this.sql = sql;
        this.list = ItemDAO.getInstance().findAllOrderBy(sql);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return(columnIndex == 1);
    }

    @Override
    public void setValueAt( Object value, int rowIndex, int columnIndex) {
        Item p = list.get(rowIndex);
        if (columnIndex == 1) {
            p.setName(value.toString());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Id";
            case 1: return "Nome";
            case 2: return "Descrizione";
            case 3: return "Prezzo";
            case 4: return "Tipo";
            case 5: return "Disponibilità";
            case 6: return "Grossista";
            case 7: return "Categoria";
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
        Item i = list.get(rowIndex);

        switch (columnIndex) {
            case 0: return i.getId();
            case 1: return i.getName();
            case 2: return i.getDescription();
            case 3: return i.getPrice();
            case 4: return i.getType();
            case 5: return i.isAvailable();
            case 6: return WholesalerDAO.getInstance().findById(i.getWholesalerId()).getName();
            case 7: return CategoryDAO.getInstance().findById(i.getCategoryId()).getName();
        }
        return null;
    }

    public List<Item> getlist() {
        return list;
    }
}
