package DAO;

import java.util.ArrayList;

import Model.CompositeProduct;
import Model.Product;

public interface ICompositeProductDAO {
    
    CompositeProduct findById(int id);
    ArrayList<CompositeProduct> findAll();

    int add(CompositeProduct cp, Product p);

    int removeById(int id);

    int update(CompositeProduct cp, int newId);
}