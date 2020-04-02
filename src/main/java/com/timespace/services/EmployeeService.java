package com.timespace.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.timespace.models.Employee;

@Service
public interface EmployeeService extends CrudService<Employee, Long>
{
    Employee findByLastName(String lastName);
    Optional<Employee> findByEmplId(Integer emplId);
}