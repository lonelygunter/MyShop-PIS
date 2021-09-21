package DAO;

import java.util.ArrayList;

import Model.Item;
import Model.Service;

public interface IServiceDAO {

    Service findById(int id);
    ArrayList<Service> findAll();

    int add(Item i);

    int removeById(int id);

    int update(Item i, int oldId);

    int addToList(int idList, int idService);
}
