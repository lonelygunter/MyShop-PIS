package Model;

import java.util.Date;

public class User {

    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private Date age;
    private String email;
    private String telephone;
    private String street;
    private String cap;
    private String role;
    private int disable;


    public User() {
        this.name = "";
        this.surname = "";
        this.age = new Date();
        this.email = "";
        this.telephone = "";
        this.cap = "";
        this.street = "";
        this.role = "";
        this.disable = 0;
    }

    public User(String username, String password, String name, String surname, Date age, String email, String telephone, String cap, String street, String role, int disable) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.telephone = telephone;
        this.cap = cap;
        this.street = street;
        this.role = role;
        this.disable = disable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public int isDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public String toString() {
        return name + " " + surname + " " + email + " " + age + " " + role + " " + telephone + " " + cap ;
    }
}