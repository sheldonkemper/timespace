package com.timespace.controllers;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.timespace.models.Employee;
import com.timespace.models.Holiday;
import com.timespace.services.EmployeeService;
import com.timespace.services.HolidayService;

@SessionAttributes("user")
@RequestMapping("/employee")
@Controller
public class HolidayController 
{
	HolidayService holidayService;
	EmployeeService employeeService;

	public HolidayController(HolidayService holidayService, EmployeeService employeeService) 
	{
		
		this.employeeService = employeeService;
		this.holidayService = holidayService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("holiday")
	public Holiday loadEmployeeWithHoliday(
			@PathVariable("employeeId") Long employeeId, 
			Map<String, Object> model) 
	{
		Employee employee = employeeService.findById(employeeId);
		model.put("employee", employee);
		Holiday holiday = new Holiday();
		employee.getHolidays().add(holiday);
		holiday.setEmplId(employee);
		return holiday;
	}


}
	


			 

