package com.timespace.controllers;

import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.timespace.models.Employee;
import com.timespace.services.EmployeeService;


@ExtendWith(MockitoExtension.class)
public class HumanResourceControllerTest {
	
	@Mock EmployeeService employeeService;
	
	@InjectMocks
	HumanResourceController controller;
	
	Set<Employee> employee;
	
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
	
	@Test
	void listEmployees() throws Exception
	{
		
		when(employeeService.findAll()).thenReturn(employee);
		mockMvc.perform(get("/humanresource/listemployee"))
		.andExpect(status().isOk())
		.andExpect(view().name("humanresource/listEmployees"));
		
	}
	
	/**
	 * Test the user is allowed to navigate to the requested pages
	 * 
	 * @throws Exception
	 */
	@Test
	@WithMockUser(username = "hr", password = "123", roles = "HR")
	public void HumanResourceNavigateToIndexPageTest() throws Exception {
	    this.mockMvc.perform(get("/humanresource/listemployee")).andDo(print()).andExpect(status().isOk());
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
