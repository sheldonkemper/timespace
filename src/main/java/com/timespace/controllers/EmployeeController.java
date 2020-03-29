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
	public ModelAndView userPage() 
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("employee/requestHoliday");
		return model;
	}
	
	
	
}