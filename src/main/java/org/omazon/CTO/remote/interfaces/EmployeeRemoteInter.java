package org.omazon.CTO.remote.interfaces;

import java.io.Serializable;

import javax.ejb.Remote;

import org.omazon.CTO.entities.Employee;

@Remote
public interface EmployeeRemoteInter extends Serializable {

    public void update();

    public Employee getEmployee();

    public void setEmployee(Employee employee);


}
