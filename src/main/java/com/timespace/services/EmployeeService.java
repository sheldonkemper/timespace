package com.timespace.services;

import org.springframework.stereotype.Service;

import com.timespace.models.Employee;

@Service
public interface EmployeeService extends CrudService<Employee, Long>
{
    Employee findByLastName(String lastName);
}