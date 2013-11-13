package org.omazon.CTO.entities;

import javax.persistence.Column;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 04.11.13
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
public abstract class UserAbstract {

    @Column(name = "userLogin")
    String userLogin;

    @Column(name = "password")
    String password;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
