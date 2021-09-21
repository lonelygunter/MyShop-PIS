package DAO;

import java.util.ArrayList;

import Model.Position;

public interface IPositionDAO {

    Position findById(int id);
    ArrayList<Position> findAll();

    int add(Position p);

    int removeById(int id);

    int update(Position p);
}
