package com.timespace.controllers;


import java.time.LocalDate;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.timespace.models.Employee;
import com.timespace.models.Holiday;
import com.timespace.services.EmployeeService;
import com.timespace.services.HolidayService;


@RequestMapping("/account")
@Controller
public class AccountController {
	
	private final HolidayService holidayService;
	private final EmployeeService employeeService;
	
	public AccountController(EmployeeService employeeService,HolidayService holidayService) {
		this.employeeService = employeeService;
		this.holidayService = holidayService;	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@Autowired
	@PreAuthorize("hasRole('ACCOUNT')")
	@RequestMapping("/")
	public String home(HttpServletRequest request) throws Exception {
		 return "account/listEmployees";
	}
	
	@GetMapping({ "/searchDates" })
	public String getListOfEmployees( ModelMap model) {
		Set<Holiday> holidays = holidayService.findAll();
		model.addAttribute("holiday", holidays);
		return "account/searchDates";
	}
	
	@GetMapping({ "/listholidays" })
	public String findAllHolidays(ModelMap model) 
	{
		//@todo Need to capture a date input from the form
		LocalDate aDate = LocalDate.of(2020, 7, 19);
		LocalDate endDate = LocalDate.of(2020, 8, 01).plusDays(1);
		 Set<Holiday> aHolidayService = holidayService.findAllByStartDate(aDate);
		 
		 aHolidayService.forEach(pairs->{
			 if(pairs.getEndDate().isBefore(endDate)) {
				 //@todo need to insert these into a array. Only passing one value
				Employee aEmployeeService = employeeService.findById( pairs.getEmplId().getId());
				 model.addAttribute("holiday", aHolidayService)
				 	  .addAttribute("employee", aEmployeeService);
			 }
		 });
		
		return "account/listDates";
	}

}
