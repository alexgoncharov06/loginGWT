package com.github.alexgoncharov06.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.*;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 16.04.16.
 */

@Entity
@Table(name = "users", schema = "PUBLIC")
public class User implements IsSerializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    int ID;
    @Column(name = "login")
    private String login;
    @Column(name = "name")
    private String name;
    @Column(name = "password", length = 64)
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = CryptPassword.encryptPassword(password);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getLogin().equals(user.getLogin())) return false;
        return getPassword().equals(user.getPassword());

    }

    @Override
    public int hashCode() {
        int result = getLogin().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.setPassword(password);
    }

    public User(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.setPassword(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
