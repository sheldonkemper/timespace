package com.timespace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping("/employee")
@Controller
public class EmployeeController 
{
	
	
	@RequestMapping(value = {"/requestholiday"}, method = RequestMethod.GET)
	public ModelAndView requestHoliday() 
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("employee/requestHoliday");
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