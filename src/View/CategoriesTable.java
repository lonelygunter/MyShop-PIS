package View;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import DAO.CategoryDAO;
import Model.Category;

public class CategoriesTable extends AbstractTableModel {

    private final int NUMBER_OF_COLUMNS = 4;

    private List<Category> list;
    private String sql;

    public CategoriesTable(String sql) {
        this.sql = sql;
        this.list = CategoryDAO.getInstance().findAllOrderBy(sql);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return(columnIndex == 1);
    }

    @Override
    public void setValueAt( Object value, int rowIndex, int columnIndex) {
       Category p = list.get(rowIndex);
        if (columnIndex == 1) {
            p.setName(value.toString());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Id categoria";
            case 1: return "Categoria";
            case 2: return "Id sottocategoria";
            case 3: return "Sottocategoria";
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
        Category w = list.get(rowIndex);

        if(w.getFatherId() != 0) {
            switch (columnIndex) {
                case 0: return w.getId();
                case 1: return w.getName();
                case 2: return w.getFatherId();
                case 3: return CategoryDAO.getInstance().findById(w.getFatherId()).getName();
            }
        }
        
        return null;
    }

    public List<Category> getlist() {
        return list;
    }
}