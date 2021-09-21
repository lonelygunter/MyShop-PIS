package DAO;

import java.util.ArrayList;

import Model.Item;
import Model.List;
import Model.User;

public interface IItemDAO {

    Item findById(int id);
    ArrayList<Item> findAll();

    ArrayList<Item> findAllOrderBy(String sql);

    int add(Item i);

    int removeById(int id);

    int update(Item i);

    int addToList(int idList, int idItem);

    int addOrder(Item i, List list, User user);

    Item findByName(String name);

    boolean itemExists(Item item);

    boolean itemNameExists(Item item);
}
