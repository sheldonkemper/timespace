package com.timespace.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.anyLong;
import java.util.HashSet;
import java.util.Set;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.timespace.models.Employee;
import com.timespace.services.EmployeeService;

class EmployeeControllerTest {

	MockMvc mockMvc;
	@Mock 
	EmployeeService employeeService;
	
	@InjectMocks
	EmployeeController controller;
	
	Set<Employee>employee;
	
	@BeforeEach
	void setUp() throws Exception {
		employee = new HashSet<>();
		employee.add(Employee.builder().id(1l).build());
		employee.add(Employee.builder().id(2l).build());
		
		mockMvc =  MockMvcBuilders
				.standaloneSetup(controller)
				.build();
		
	}

	@Test
	void displayEmployeeTest()  throws Exception{
		when(employeeService.findById(anyLong())).thenReturn(Employee.builder().id(1l).build());
		
		mockMvc.perform(get("/employee/details/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("employee/details"))
		.andExpect(model().attribute("employee",hasProperty("id",is(1l))));
	}

}
