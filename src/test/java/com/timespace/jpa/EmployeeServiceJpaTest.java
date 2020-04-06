package com.timespace.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.timespace.controllers.EmployeeController;
import com.timespace.models.Employee;
import com.timespace.repositories.EmployeeRepository;
import com.timespace.services.EmployeeService;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceJpaTest
{
    @Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	EmployeeService employeeService;
	 
	@InjectMocks
	EmployeeController controller;
	 
	Set<Employee> employees;
	 
	MockMvc mockMvc;

	  @BeforeEach
	    void setUp() {
	        employees = new HashSet<>();
	        employees.add(Employee.builder().id(1l).build());
	        employees.add(Employee.builder().id(2l).build());
	 
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(controller)
	                .build();
	    }
	
	    @Test
	    void displayEmployee() throws Exception {
	        when(employeeService.findById(anyLong())).thenReturn(Employee.builder().id(1l).build());
	 
	        mockMvc.perform(get("/employee/details/1"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("employee/details"));
	                //.andExpect(model().attribute("employee", hasProperty("id", is(1l))));
	    }
	    
	  
	
	
}
