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
	
	/**
	 * Accepting a get url request, with parameters for the employee id,holiday id,
	 * and an int if granted or declined.
	 * If number of days is <= to allowed entitlment, then set employee entitlment
	 * 
	 * @param employeeId
	 * @param holidayId
	 * @param modelMap
	 * @return
	 */
	@GetMapping("/authholiday/{employeeId}/{holidayId}/{id}")
	public String authoriseEmployeeHoliday(@PathVariable("id") Integer id,@PathVariable("employeeId") Long employeeId,@PathVariable("holidayId") Long holidayId,ModelMap modelMap) 
	{
		
		Employee employee = employeeService.findById(employeeId);
		Holiday holiday = this.holidayService.findById(holidayId);
		Integer requestedDays = (int) DAYS.between(holiday.getStartDate(), holiday.getEndDate());
		Integer entitlment = employee.getEntitlement();
		
		if(id == 1)
		{
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
		else if(id == 0)
		{
			holiday.setGranted("Declined");
			this.holidayService.save(holiday);
			return "redirect:/employee/details/"+ employeeId;
		}
		else
		{
			return "redirect:/employee/requestholiday";
		}
	
	}


}
