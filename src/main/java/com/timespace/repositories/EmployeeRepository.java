package com.timespace.repositories;

import org.springframework.data.repository.CrudRepository;

import com.timespace.models.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Long>
{
	Employee findByLastName(String lastName);
}