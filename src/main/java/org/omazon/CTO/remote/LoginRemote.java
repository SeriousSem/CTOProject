package org.omazon.CTO.remote;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Employee;
import org.omazon.CTO.remote.interfaces.LoginRemoteInter;


@Stateless
public class LoginRemote implements LoginRemoteInter {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String password;
    private String userLogin;

    @ManagedProperty("#{employee}")
    public Employee employee;

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    public String doUserLogin() {
        employee = employeeDAO.getEmployeeByLogin(userLogin, password);
        if (employee != null) {
            return "Login success";
        }
        else
            return "Login fail";
    }

    @Override
    public String getUserLogin() {
        return userLogin;
    }

    @Override
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<String> getEmployee() {
    	List<String> emStr = new ArrayList<String>();
		Employee em = employee;
		emStr.add(String.valueOf(em.getEmployeeId()));
		emStr.add(em.getName());
		emStr.add(em.getSurname());
		emStr.add(em.getPassword());
		emStr.add(em.getUserLogin());
        return emStr;
    }

    @Override
    public void setEmployee(List<String> employee) {
    	this.employee.setEmployeeId(Long.parseLong(employee.get(0)));
    	this.employee.setName(employee.get(1));
    	this.employee.setSurname(employee.get(2));
    	this.employee.setPassword(employee.get(3));
    	this.employee.setUserLogin(employee.get(4));
    }
}
