package com.timespace.jpa;


import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.timespace.models.Employee;
import com.timespace.repositories.EmployeeRepository;
import com.timespace.services.jpa.EmployeeServiceJpa;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceJpaTest
{
	private static final String LAST_NAME = "Kemper";

	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	Employee employee;
	
	@InjectMocks
	EmployeeServiceJpa service;

	@Test
	void findByLastName()
	{
		//Using Project Lombok to build an employee with an id of 1 and a surname of Kemper
		employee = new Employee();
		employee.setLastName(LAST_NAME);
		
		when(employeeRepository.findByLastName(any())).thenReturn(employee);
		Employee kemper = service.findByLastName(LAST_NAME);
		assertEquals(LAST_NAME,kemper.getLastName());
		// selective, explicit, highly readable verification
		verify(employeeRepository).findByLastName(any());
		}
	
	@Test
	void displayEmployees() throws Exception
	{
		//when(EmployeeServiceJpa.findById(anyLong())).thenReturn(Employee.builder().id(1l)).build();
		
	}
	
}
