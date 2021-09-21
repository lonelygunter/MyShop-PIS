package DAO;

import java.util.ArrayList;

import Model.Manager;
import Model.User;

public interface IManagerDAO {
    
    User findById(int id);
    ArrayList<Manager> findAll();

    int add(User manager);

    int removeById(int id);

    int update(User manager);
}
