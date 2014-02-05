package org.moazon.CTO.remote;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.controllers.LoginController;
import org.omazon.CTO.entities.Employee;
import org.omazon.CTO.remote.interfaces.EmployeeRemoteInter;


@Stateless
public class EmployeeRemote implements EmployeeRemoteInter {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
    @ManagedProperty("#{login}")
    public LoginController login;

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    public void update() {
    	employeeDAO.update(login.getEmployee());
    }

    @Override
    public Employee getEmployee() {
        return login.getEmployee();
    }

    @Override
    public void setEmployee(Employee employee) {
        login.setEmployee(employee);
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }

}
