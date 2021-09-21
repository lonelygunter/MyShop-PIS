package View;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import DAO.UserDAO;
import Model.User;

public class UsersTable extends AbstractTableModel {

    private final int NUMBER_OF_COLUMNS = 10;

    private List<User> list;
    private String sql;

    public UsersTable(String sql) {
        this.sql = sql;
        this.list = UserDAO.getInstance().findAllOrderBy(sql);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return(columnIndex == 1);
    }

    @Override
    public void setValueAt( Object value, int rowIndex, int columnIndex) {
        User p = list.get(rowIndex);
        if (columnIndex == 1) {
            p.setName(value.toString());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Id";
            case 1: return "Username";
            case 2: return "Nome";
            case 3: return "Cognome";
            case 4: return "Data di nascita";
            case 5: return "Email";
            case 6: return "Telefono";
            case 7: return "Indirizzo";
            case 8: return "CAP";
            case 9: return "Disable";
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
        User u = list.get(rowIndex);

        switch (columnIndex) {
            case 0: return u.getId();
            case 1: return u.getUsername();
            case 2: return u.getName();
            case 3: return u.getSurname();
            case 4: return u.getAge();
            case 5: return u.getEmail();
            case 6: return u.getTelephone();
            case 7: return u.getStreet();
            case 8: return u.getCap();
            case 9: return u.isDisable();
        }
        return null;
    }

    public List<User> getlist() {
        return list;
    }
}
