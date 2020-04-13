package com.timespace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.timespace.models.Manager;
import com.timespace.services.ManagerService;


@RequestMapping("/manager")
@Controller
public class ManagerController {
	
	private final ManagerService managerService;
	
	public ManagerController(ManagerService managerService)
	{
		this.managerService = managerService;
	}

	
	@GetMapping("/addnew/{employeeId}")
	public ModelAndView showDetails(@PathVariable("employeeId") Long employeeId) 
	{
		return null;
		//Manager employee = this.managerService.findById(employeeId);
		
	
	}
}
