package org.example.entity;

import org.example.annotation.IDField;

/**
 * the User entity, it has two type of user ADMIN and USER;
 */
public class User {

    /**
     * username, it is unique
     */
    @IDField
    private String account;

    /**
     * user's password
     */
    private String password;

    /**
     * a flag to indicate if the user is admin
     */
    private Boolean isAdmin;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
