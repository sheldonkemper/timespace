package com.timespace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timespace.services.EmployeeService;

@RequestMapping("/humanresource")
@Controller
public class HumanResourceController 
{

	private final EmployeeService employeeService;
	
	public HumanResourceController(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}
	
	@RequestMapping({"/listemployee"})
	public String listEmployee(Model model)
	{
		model.addAttribute("employees",employeeService.findAll());
		return "humanresource/listEmployees";
	}

	
}