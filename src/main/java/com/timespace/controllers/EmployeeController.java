package com.timespace.controllers;

import java.time.LocalDate;

import javax.validation.Valid;

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
public class EmployeeController {
	private final HolidayService holidayService;
	private final EmployeeService employeeService;

	public EmployeeController(HolidayService holidayService, EmployeeService employeeService) {
		this.employeeService = employeeService;
		this.holidayService = holidayService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/details/{employeeId}")
	public ModelAndView showDetails(@PathVariable("employeeId") Long employeeId) {
		ModelAndView model = new ModelAndView("employee/details");
		Employee employee = this.employeeService.findById(employeeId);
		model.addObject("employee", employee);
		return model;
	}

	@GetMapping("/requestholiday/{employeeId}")
	public ModelAndView requestHoliday(@PathVariable("employeeId") Long employeeId,
			@ModelAttribute("employee") Employee employee, @ModelAttribute("holiday") Holiday holiday,
			BindingResult result) {
		ModelAndView model = new ModelAndView("employee/requestholiday");
		model.addObject("employee", this.employeeService.findById(employeeId)).addObject("holiday",
				Holiday.builder().build());
		return model;
	}

	@PostMapping("/requestholiday/{employeeId}")
	public String editEmployee(@Valid Holiday holiday, BindingResult result,
			@PathVariable("employeeId") long employeeId) {

		if (result.hasErrors()) {
			return "employee/requestHoliday/" + employeeId;
		} else {

			Employee employee = employeeService.findById(employeeId);

			if (holiday.validDateRange() && employee.getEntitlement() > 0) {
				holiday.setEmployee(employee);
				holidayService.save(holiday);
				return "redirect:/employee/details/{employeeId}";
			} else {
				return "employee/requestHoliday/" + employeeId;
			}

		}
	}

}