package com.timespace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timespace.services.EmployeeService;


@RequestMapping("/employee")
@Controller
public class EmployeeController 
{
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}
	
	@RequestMapping(value = {"/requestholiday"}, method = RequestMethod.GET)
	public ModelAndView requestHoliday() 
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("employee/requestHoliday");
		return model;
	}
	
	@GetMapping("/details/{employeeId}")
	public ModelAndView showDetails(@PathVariable("employeeId") Long employeeId) 
	{
		ModelAndView model = new ModelAndView("employee/details");
		model.addObject("employee",this.employeeService.findById(employeeId));
		return model;
	}
	
	@RequestMapping(value = {"/details"}, method = RequestMethod.GET)
	public ModelAndView details() 
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("employee/details");
		return model;
	}
	
	
	
}