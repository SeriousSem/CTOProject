package org.omazon.CTO.remote.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;


@Remote
public interface LoginRemoteInter extends Serializable {
	
	public String doUserLogin();

    public String getUserLogin();

    public void setUserLogin(String userLogin);

    public String getPassword();

    public void setPassword(String password);

    public List<String> getEmployee();

    public void setEmployee(List<String> employee);

}
