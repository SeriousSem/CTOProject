package org.omazon.CTO.remote.interfaces;

import java.io.Serializable;

import javax.ejb.Remote;

import org.omazon.CTO.entities.Employee;

@Remote
public interface LoginRemoteInter extends Serializable {
	
	public String doUserLogin();

    public String getUserLogin();

    public void setUserLogin(String userLogin);

    public String getPassword();

    public void setPassword(String password);

    public Employee getEmployee();

    public void setEmployee(Employee employee);

}
