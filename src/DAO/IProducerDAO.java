package DAO;

import java.util.ArrayList;

import Model.Producer;

public interface IProducerDAO {

    Producer findById(int id);
    ArrayList<Producer> findAll();

    int add(Producer p);

    int removeById(int id);

    int update(Producer p, int oldId);
}
