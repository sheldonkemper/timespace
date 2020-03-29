package com.timespace.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
@WebMvcTest(LoginController.class)
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private LoginController controller;
	
	@Test
	public void contexLoads() throws Exception {
	    assertThat(controller).isNotNull();
	}

	@Test
	public void shouldReturnDefaultLogon() throws Exception {
		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());

	}
	
	@Test
	public void shouldReturnDefaultLogout() throws Exception {
		this.mockMvc.perform(get("/logout")).andDo(print()).andExpect(status().isOk());

	}
	
	@Test
	public void shouldRedirect() throws Exception {

		this.mockMvc.perform(get("/humanresource/index")).andDo(print()).andExpect(redirectedUrl("http://localhost/login"));

	}
}