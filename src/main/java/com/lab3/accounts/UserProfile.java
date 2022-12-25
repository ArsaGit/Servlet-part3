package com.lab3.accounts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users2")
public class UserProfile implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "login", unique = true, updatable = false)
    private String login;
    @Column(name = "email", unique = true, updatable = false)
    private String email;
    @Column(name = "password", unique = true, updatable = false)
    private String pass;

    public UserProfile() {

    }

    public UserProfile(String login, String email, String pass) {
        this.setId(-1);
        this.setLogin(login);
        this.setEmail(email);
        this.setPass(pass);
    }

    public UserProfile(long id, String login, String email, String pass) {
        this.setId(id);
        this.setLogin(login);
        this.setEmail(email);
        this.setPass(pass);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() { return login; }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
