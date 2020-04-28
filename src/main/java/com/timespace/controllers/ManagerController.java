package com.timespace.controllers;

import static java.time.temporal.ChronoUnit.DAYS;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
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
	private final HolidayService holidayService;
	
	public ManagerController(HolidayService holidayService,ManagerService managerService, EmployeeService employeeService) {
		this.employeeService = employeeService;
		this.managerService = managerService;
		this.holidayService = holidayService;
	}
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	@GetMapping("/listsubordinates")
	public String listStaff(@SessionAttribute("user")Employee user,ModelMap modelMap) {
		
		Manager manager = managerService.findByEmplId(user.getEmplId()).get();
		modelMap.addAttribute("employee",user).addAttribute("manager",manager);
		return "manager/listSubordinates";
	}
	
	@GetMapping("/grantholiday/{employeeId}/{holidayId}")
	public String grantHoliday(@PathVariable("employeeId") Long employeeId,@PathVariable("holidayId") Long holidayId,ModelMap modelMap) {
		
		//
		Employee employee = employeeService.findById(employeeId);
		Holiday holiday = this.holidayService.findById(holidayId);
		Integer requestedDays = (int) DAYS.between(holiday.getStartDate(), holiday.getEndDate());
		Integer entitlment = employee.getEntitlement();
		System.out.println("##########");
		System.out.println(requestedDays);
		System.out.println("##########");
		System.out.println(entitlment );
		
		if( entitlment >= requestedDays)
		{
			employee.setEntitlement(entitlment - requestedDays);
			
			holiday.setGranted("Approved");
			this.holidayService.save(holiday);
			return "redirect:/employee/details/"+ employeeId;
		}
		else
		{
			return "redirect:/employee/requestholiday";
		}
		
		
	}
	
	@GetMapping("/declineholiday/{employeeId}/{holidayId}")
	public String declineHoliday(@PathVariable("employeeId") Long employeeId,@PathVariable("holidayId") Long holidayId,ModelMap modelMap) {
		
		Holiday holiday = this.holidayService.findById(holidayId);
		holiday.setGranted("Declined");
		this.holidayService.save(holiday);
		return "redirect:/employee/details/"+ employeeId;
	}

}
