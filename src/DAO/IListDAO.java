package DAO;

import java.util.ArrayList;

import Model.Item;
import Model.List;

public interface IListDAO {

    List findById(int id);
    ArrayList<List> findAll();

    int add(List l);

    int removeById(int id);

    int update(List l);

    boolean listExists(String name);

    ArrayList<Item> getProductListById(int listId);

    int insertItem(Item item, List list);

    List getByName(String name);

    ArrayList<Item> getListItemsByName(String name);

    ArrayList<Item> getListItemsById(int id);

    boolean itemExists(int itemId, int listId);

    int removeListHasItemById(int listId, int itemId);

    int removeAllListHasItemById(int listId);

    int updatePrice(int listId, float totalPrice);
}
