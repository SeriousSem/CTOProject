package org.omazon.CTO.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 04.11.13
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "employee")
@SessionScoped
@ManagedBean
public class Employee extends UserAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employeeId")
    private long customerId;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (customerId != employee.customerId) return false;
        if (!name.equals(employee.name)) return false;
        if (!surname.equals(employee.surname)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + surname.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}