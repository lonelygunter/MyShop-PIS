package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import DAO.CategoryDAO;

public class StoreCatTable extends AbstractTableModel {

    private final int NUMBER_OF_COLUMNS = 4;

    private ArrayList<String> list;

    public StoreCatTable() {
        this.list = CategoryDAO.getInstance().findAllCurrentStore(1);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return(columnIndex == 1);
    }

    @Override
    public void setValueAt( Object value, int rowIndex, int columnIndex) {
       String p = list.get(rowIndex);
        if (columnIndex == 1) {
            //p.setName(value.toString());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Id categoria";
            case 1: return "Categoria";
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
        String w = list.get(rowIndex);

        switch (columnIndex) {
            case 0: return w;
            case 1: return CategoryDAO.getInstance().findById(Integer.parseInt(w)).getName();
        }
        
        return null;
    }

    public List<String> getlist() {
        return list;
    }
}