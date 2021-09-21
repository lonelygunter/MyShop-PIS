package DAO;

import java.util.ArrayList;

public interface IEmailNotificationDAO {
    
    String findByUserId(int userId);
    ArrayList<String> findAll();

    boolean emailExists(String email);
}
