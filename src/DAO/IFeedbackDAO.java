package DAO;

import java.util.ArrayList;

import Model.Feedback;

public interface IFeedbackDAO {

    Feedback findById(int id);
    ArrayList<Feedback> findAll();

    int write(Feedback f);
    int answer(Feedback f, boolean managerOrAdmin);

    int getItemIdById(int id);

    int getPurchaseHasItemById(int id);
}
