package DAO;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import Model.User;
import View.AppFrame;

public interface IUserDAO {

    User findById(int id);
    ArrayList<User> findAll();

    int add(User u);

    int removeById(int id);

    int update(User u);

    boolean userExists(String username);

    boolean emailExists(String email);

    boolean credentialsOk(String username, String password);

    String getHashedPassword(String username);

    User getByUsername(String username);

    boolean managerExists(User u);

    boolean administratorExists(User u);

    int disable(AppFrame appFrame, User u);

    int sendEmail(String recipient, String subject, String body, String path) throws AddressException, MessagingException, IOException;

    ArrayList<User> findAllOrderBy(String sql);
}