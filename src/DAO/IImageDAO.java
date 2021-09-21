package DAO;

import java.util.ArrayList;

import Model.Image;

public interface IImageDAO {

    Image findById(int id);
    ArrayList<Image> findAll();

    Image findByItemId(int id);
}
