package DAO;

import java.util.ArrayList;

import Model.Order;

public interface IOrderDAO {

    Order findById(int id);
    ArrayList<Order> findAll();

    int add(Order o);

    int removeById(int id);

    int update(Order o);

    ArrayList<Order> findAllOrderBy(String sql);

    Order findByName(String name);

    Order findByNameAndUserId(String name, int userId);

    int removeAll(int currentUserId);

    int removeToOrder(int id);
}
