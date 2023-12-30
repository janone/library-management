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
    private boolean isAdmin;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
