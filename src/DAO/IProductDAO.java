package DAO;

import java.util.ArrayList;

import Model.Item;
import Model.Position;
import Model.Product;

public interface IProductDAO {

    Product findById(int id);
    ArrayList<Product> findAll();

    int add(Item item, int quantity, Position pposition);

    int removeById(int id);

    int update(Item item, int quantity, Position position);

    int reservation(Product product, Item item);

    int addToList(int idList, int idProduct);
}