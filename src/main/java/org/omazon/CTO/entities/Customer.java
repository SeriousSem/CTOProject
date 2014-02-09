package org.omazon.CTO.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: All Date: 29.09.13 Time: 00:42
 */
@Entity
@Table(name = "customer")
@SessionScoped
@ManagedBean(name = "customer", eager = true)
public class Customer implements Serializable {

    private static final long serialVersionUID = -3009396611049889475L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    private long customerId;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

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

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        try {
	        int result = 1;
	        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
	        result = prime * result + (int) (getCustomerId() ^ (getCustomerId() >>> 32));
	        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
	        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
	        result = prime * result + ((getSurname() == null) ? 0 : getSurname().hashCode());
	        return result;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (getAddress() == null) {
            if (other.getAddress() != null)
                return false;
        } else if (!getAddress().equals(other.getAddress()))
            return false;
        if (getCustomerId() != other.getCustomerId())
            return false;
        if (getEmail() == null) {
            if (other.getEmail() != null)
                return false;
        } else if (!getEmail().equals(other.getEmail()))
            return false;
        if (getName() == null) {
            if (other.getName() != null)
                return false;
        } else if (!getName().equals(other.getName()))
            return false;
        if (getSurname() == null) {
            if (other.getSurname() != null)
                return false;
        } else if (!getSurname().equals(other.getSurname()))
            return false;
        return true;
    }
    

}
