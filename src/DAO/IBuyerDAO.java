package DAO;

import java.util.ArrayList;

import Model.Buyer;

public interface IBuyerDAO {
    
    Buyer findById(int id);
    ArrayList<Buyer> findAll();

    int add(Buyer b);

    int removeById(int id);

    int update(Buyer b, int oldId);
}
