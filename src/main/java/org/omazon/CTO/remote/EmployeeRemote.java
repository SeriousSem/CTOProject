package org.omazon.CTO.remote;

import java.util.ArrayList;
import java.util.List;

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
    	Employee ee = new Employee();
    	if (employee.size() == 5) {
    	ee.setEmployeeId(Long.parseLong(employee.get(0)));
    	ee.setName(employee.get(1));
    	ee.setSurname(employee.get(2));
    	ee.setPassword(employee.get(3));
    	ee.setUserLogin(employee.get(4));
    	}
    	else {
    		ee.setName(employee.get(0));
        	ee.setSurname(employee.get(1));
        	ee.setPassword(employee.get(2));
        	ee.setUserLogin(employee.get(3));
    	}
    	this.employee = ee;
    }

}
