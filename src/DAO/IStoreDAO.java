package DAO;

import java.util.ArrayList;

import Model.Store;

public interface IStoreDAO {

    Store findById(int id);
    ArrayList<Store> findAll();

    int add(Store s);

    int removeById(int id);

    int update(Store s);

    ArrayList<Store> findAllOrderBy(String sql);

    boolean storeExists(Store store);

    Store findByNation(String store);
}