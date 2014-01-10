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
@ManagedBean(name="employee", eager=true)
public class Employee extends UserAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employeeId")
    private long employeeId;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "psw")
    private String psw;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (employeeId != employee.employeeId) return false;
        if (!name.equals(employee.name)) return false;
        if (!surname.equals(employee.surname)) return false;

        return true;
    }

    @Override
    public int hashCode() {
    	try {
	        int result = (int) (employeeId ^ (employeeId >>> 32));
	        result = 31 * result + surname.hashCode();
	        result = 31 * result + name.hashCode();
	        return result;
    	}
    	catch (NullPointerException e) {
    		return 0;
    	}
    }
}