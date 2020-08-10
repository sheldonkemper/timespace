package com.timespace.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.timespace.models.Employee;
import com.timespace.services.EmployeeService;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
	  @Mock
	  private  EmployeeService employeeService;
	   @InjectMocks
	   private  EmployeeController controller;
	  Set<Employee> employees;
	  MockMvc mockMvc;
	@BeforeEach
	void setUp() throws Exception 
	{
		  HashSet<Employee> employees = new HashSet<>();
	        employees.add(Employee.builder().id(1l).build());
	        employees.add(Employee.builder().id(2l).build());
	        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/*
	 * Return a specific employee
	 */
    @Test
    void displayEmployeeDetails() throws Exception {
        when(employeeService.findById(anyLong()))
        			.thenReturn(Employee.builder()
        			.id(1l)
        			.build());
        mockMvc.perform(get("/employee/details/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/details"));
    }
}
