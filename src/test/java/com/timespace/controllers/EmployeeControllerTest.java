package com.timespace.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.timespace.models.Employee;
import com.timespace.services.EmployeeService;

import junit.framework.Assert;

class EmployeeControllerTest {

	MockMvc mockMvc;
	
	@Mock 
	EmployeeService employeeService;
	
	@InjectMocks
	EmployeeController controller;
	
	@Mock 
	Employee employee;
	
	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	void displayEmployeeTest() throws Exception {
		assertNotNull(employee);
		
		
		
	}

}
