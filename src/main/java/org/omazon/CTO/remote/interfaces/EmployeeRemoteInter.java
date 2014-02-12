package org.omazon.CTO.remote.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;


@Remote
public interface EmployeeRemoteInter extends Serializable {

    public void update();

    public List<String> getEmployee();

    public void setEmployee(List<String> employee);


}
