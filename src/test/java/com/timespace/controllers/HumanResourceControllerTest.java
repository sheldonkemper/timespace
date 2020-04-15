package com.timespace.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.timespace.models.Employee;
import com.timespace.services.EmployeeService;
import com.timespace.services.ManagerService;


@ExtendWith(MockitoExtension.class)
public class HumanResourceControllerTest {
	
	@Mock 
	private EmployeeService employeeService;
	
	@Mock 
	ManagerService managerService;
	
	@InjectMocks
	private HumanResourceController controller;
	
	private Set<Employee> employee;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp()
	{
		employee = new HashSet<>();
		employee.add(Employee.builder().id(1l).build());
		employee.add(Employee.builder().id(2l).build());
		
		 mockMvc = MockMvcBuilders
				.standaloneSetup(controller)
				.build();
	}
	
	
	/**
	 * Test the user is allowed to navigate to the requested pages
	 * 
	 * @throws Exception
	 */
	@Test
	@WithMockUser(username = "Mallet", password = "12345", roles = "HR")
	public void HumanResourceNavigateToIndexPageTest() throws Exception {
	    this.mockMvc.perform(get("/humanresource/addemployee")).andDo(print()).andExpect(status().isOk());
	}
	
	/**
	 * Test that the addEmployee form is returned.
	 * Verify the user has not submitted the form
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
    void addEmployeeForm() throws Exception {
        mockMvc.perform(get("/humanresource/addemployee"))
                .andExpect(status().isOk())
                .andExpect(view().name("humanresource/addEmployee"))
                .andExpect(model().attributeExists("employee"));

        verifyZeroInteractions(employeeService);
    }
	
	


}
