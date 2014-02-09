package org.moazon.CTO.remote;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Employee;
import org.omazon.CTO.remote.interfaces.EmployeeRemoteInter;


@Stateless
public class EmployeeRemote implements EmployeeRemoteInter {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
	private Employee employee;

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    public void update() {
    	employeeDAO.merge(employee);
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
