package com.timespace.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.timespace.models.Employee;
import com.timespace.models.Holiday;
import com.timespace.services.EmployeeService;
import com.timespace.services.HolidayService;

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

	/*
	 * @GetMapping("/requestholiday/{employeeId}") public String
	 * initNewHolidayRequestForm(@PathVariable("employeeId") Long employeeId,
	 * Map<String, Object> model) { return "employee/requestHoliday"; }
	 */

	@PostMapping ("/requestholiday/{employeeId}")
	public String processHolidayRequestForm(
			@PathVariable("employeeId") Long employeeId,
			@Valid Holiday holiday,
			BindingResult result)
	{
		if(result.hasErrors())
		{
			return "employee/requestHoliday";
		}
		else
		{
			Employee employee = employeeService.findById(employeeId);
			if (holiday.validDateRange() && employee.getEntitlement() >= holiday.daysBetween()) 
			{
				this.holidayService.save(holiday);
				return "redirect:/employee/details/{employeeId}"; 
			}
			else
			{
				return "employee/requestHoliday";
			}
		}
	}
}
	


			 

