package DAO;

import java.util.ArrayList;

import Model.Stockist;
import Model.Wholesaler;

public interface IStockistDAO {

    Stockist findById(int id);
    ArrayList<Stockist> findAll();

    int add(Wholesaler wholesaler);

    int removeById(int id);

    int update(Wholesaler wholesaler, int oldId);
}
