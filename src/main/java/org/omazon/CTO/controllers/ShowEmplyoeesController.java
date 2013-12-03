package org.omazon.CTO.controllers;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Employee;

@ManagedBean(name = "showEmployees")
@RequestScoped
public class ShowEmplyoeesController {

    @Inject
    private EmployeeDAO employeeDAO;

    public ShowEmplyoeesController() {
    }

    public List<Employee> getEmployees() {
        return employeeDAO.getAll();
    }


}
