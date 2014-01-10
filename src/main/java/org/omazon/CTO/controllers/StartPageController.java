/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
*/
package org.omazon.CTO.controllers;


import org.omazon.CTO.DAO.interfaces.CustomerDAO;
import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Customer;
import org.omazon.CTO.entities.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import java.util.List;

@ManagedBean(name = "startPageController")
@RequestScoped
public class StartPageController {

    @Inject
    private CustomerDAO customerDAO;
    
    @Inject
    private EmployeeDAO employeeDAO;

    public StartPageController() {
    }

    public List<Customer> getCustomers() {
        return customerDAO.getAll();
    }
    
    public List<Employee> getEmployees() {
        return employeeDAO.getAll();
    }


}
