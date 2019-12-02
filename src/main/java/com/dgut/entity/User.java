package com.dgut.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Integer user_id;
    private String user_name;
    private String user_password;

    private List<Account> accounts;

    private List<Account> roles;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", accounts=" + accounts +
                ", roles=" + roles +
                '}';
    }

    public List<Account> getRoles() {
        return roles;
    }

    public void setRoles(List<Account> roles) {
        this.roles = roles;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }


}
