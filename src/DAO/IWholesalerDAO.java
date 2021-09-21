package DAO;

import java.util.ArrayList;

import Model.Wholesaler;

public interface IWholesalerDAO {

    Wholesaler findById(int id);
    ArrayList<Wholesaler> findAll();

    int add(Wholesaler w);

    int removeById(int id);

    int update(Wholesaler w);

    ArrayList<Wholesaler> findAllOrderBy(String sql);

    Wholesaler findByName(String name);

    boolean wholesalerExists(Wholesaler wholesler);

    boolean wholesalerNameExists(Wholesaler wholesler);
}
