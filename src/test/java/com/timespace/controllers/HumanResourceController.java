package com.timespace.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


@WebMvcTest(HumanResourceControllerTest.class)
class HumanResourceControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    private HumanResourceController controller;
	
    
	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	/**
	 * test the user is allowed to navigate to the requested page
	 * 
	 * @throws Exception
	 */
	@Test
	@WithMockUser(username = "hr", password = "123", roles = "HR")
	public void HumanResourceNavigateToIndexPageTest() throws Exception {
	    this.mockMvc.perform(get("/humanresource/index")).andDo(print()).andExpect(status().isOk());
	}

	/**
	 * test the user is not allowed to navigate to the requested page
	 * 
	 * @throws Exception
	 */
	@Test
	@WithMockUser(username = "user345", password = "123", roles = "EMPLOYEE")
	public void EmployeeFailsToNavigateToRequestPageTest() throws Exception {
	    this.mockMvc.perform(get("/humanresource/index")).andDo(MockMvcResultHandlers.print()).andExpect(status().isForbidden());
	}
}
