package com.timespace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.timespace.models.Employee;
import com.timespace.models.Holiday;
import com.timespace.services.EmployeeService;
import com.timespace.services.HolidayService;

@RequestMapping("/employee")
@Controller
public class EmployeeController {
	private final EmployeeService employeeService;
	public EmployeeController(HolidayService holidayService, EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/details/{employeeId}")
	public ModelAndView showDetails(@PathVariable("employeeId") Long employeeId,@ModelAttribute("holiday") Holiday holiday) {
		ModelAndView model = new ModelAndView("employee/details");
		Employee employee = this.employeeService.findById(employeeId);
		model.addObject("employee", employee).addObject("holiday",holiday);
		return model;
	}

}