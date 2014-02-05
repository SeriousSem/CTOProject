package org.moazon.CTO.remote;

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
    public Employee getEmployee() {
        return employee;
    }

    @Override
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
