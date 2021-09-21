package DAO;

import java.util.ArrayList;

import Model.Administrator;
import Model.User;

public interface IAdministratorDAO {
    
    Administrator findById(int id);
    ArrayList<Administrator> findAll();

    int add(User administrator);

    int removeById(int id);

    int update(User administrator, int oldId);
}
