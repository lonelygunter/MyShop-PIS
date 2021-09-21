package DAO;

import java.util.ArrayList;

import Model.Payment;

public interface IPaymentDAO {

    Payment findById(int id);
    ArrayList<Payment> findAll();
    
    int add(Payment p);
}
