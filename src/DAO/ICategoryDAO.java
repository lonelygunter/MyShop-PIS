package DAO;

import java.util.ArrayList;

import Model.Category;

public interface ICategoryDAO {

    Category findById(int id);
    ArrayList<Category> findAll();

    int add(Category c);

    int removeById(int id);

    int update(Category c);
    
    String[] getAllNames();

    ArrayList<Category> getAllByFatherId(int fatherId);

    ArrayList<Category> findAllOrderBy(String sql);

    Category findByName(String name);

    boolean categoryExists(Category wholesler);

    boolean categoryNameExists(Category wholesler);

    int addToStoreCategories(Category c, String store);

    int deleteToStoreCategories(Category c, String store);

    ArrayList<String> findAllCurrentStore(int storeId);
}
