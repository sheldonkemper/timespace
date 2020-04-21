package com.timespace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.timespace.models.Employee;
import com.timespace.models.Holiday;
import com.timespace.models.Manager;
import com.timespace.services.EmployeeService;
import com.timespace.services.HolidayService;
import com.timespace.services.ManagerService;

@RequestMapping("/manager")
@Controller
public class ManagerController {
	
	private final EmployeeService employeeService;
	private final ManagerService managerService;
	
	public ManagerController(ManagerService managerService,HolidayService holidayService, EmployeeService employeeService) {
		this.employeeService = employeeService;
		this.managerService = managerService;
	}
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	@GetMapping("/listsubordinates/{employeeId}")
	public String listStaff(@PathVariable("employeeId") Long employeeId,ModelMap modelMap) {
		
		Employee employee = this.employeeService.findById(employeeId);
		Manager manager = managerService.findByEmplId(employee.getEmplId()).get();
		modelMap.addAttribute("employee",employee).addAttribute("manager",manager);
		
		return "manager/listSubordinates";
	}

}
