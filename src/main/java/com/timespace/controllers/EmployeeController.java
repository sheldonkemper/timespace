package com.timespace.controllers;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import com.timespace.models.Employee;
import com.timespace.models.Holiday;
import com.timespace.services.EmployeeService;
import com.timespace.services.HolidayService;

@SessionAttributes("user")
@RequestMapping("/employee")
@Controller
public class EmployeeController {
	private final EmployeeService employeeService;
	private final HolidayService holidayService;
	
	public EmployeeController(HolidayService holidayService, EmployeeService employeeService) {
		this.employeeService = employeeService;
		this.holidayService = holidayService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	/**
	 * Return details for a specifid employee to the view
	 * 
	 * @param employeeId
	 * @param holiday
	 * @return View model
	 */
	@GetMapping("/details/{employeeId}")
	public ModelAndView getEmployeeDetails(
			@PathVariable("employeeId") Long employeeId,
			@ModelAttribute("holiday") Holiday holiday) 
	{
		ModelAndView model = new ModelAndView("employee/details");
		Employee employee = this.employeeService.findById(employeeId);
		model.addObject("employee", employee).addObject("holiday",holiday);
		return model;
	}
	
	@GetMapping("/details")
	public ModelAndView getEmployee(
			@ModelAttribute("user")Employee user,
			@ModelAttribute("holiday") Holiday holiday) 
	{
		ModelAndView model = new ModelAndView("employee/details");
		model.addObject("employee", user).addObject("holiday",holiday);
		return model;
	}
	
	/**
	 * Employee creates a new holiday request
	 * @param user
	 * @param model
	 * @return
	 */
	@GetMapping("/requestholiday")
	public String initNewHolidayRequest(@ModelAttribute("user")Employee user, Model model) 
	{
		model.addAttribute("holiday", Holiday.builder().build());
		return "employee/requestHoliday";
	}
	
	/**
	 * Allow employee to delete a holiday which has 
	 * not been authorised by the manager
	 * @return
	 */
	@GetMapping("/deleteholiday/{holidayId}")
	public  RedirectView  deleteHoliday(
			@PathVariable("holidayId") Long holidayId,
			@ModelAttribute("user")Employee user,
			@Valid Holiday holiday,
			Model model,
			RedirectAttributes attributes)
	{
		Holiday aHoliday = holidayService.findById(holidayId);
		
		if(aHoliday.getEmplId().getId()==user.getId() && aHoliday.getGranted()=="Awaiting"   )
		{
			attributes.addFlashAttribute("message", "Holiday has been deleted");
			System.out.print(aHoliday.getGranted());
			holidayService.delete(aHoliday);
		}
		else
		{
			attributes.addFlashAttribute("message", "Holiday cannot be deleted. Please speak to your manager");
		}
		
		return new RedirectView("/employee/details");
	}
	
	/**
	 * Accepting a form post, and if the date range has been input correctly
	 * and the number of days is >= to an employees entitlment,
	 * thne save the holiday for the associated employee.
	 * @param user
	 * @param holiday
	 * @param model
	 * @param attributes
	 * @return Redirect to the details page
	 */
	@PostMapping ("/requestholiday")
	public RedirectView processHolidayRequest(
			@ModelAttribute("user")Employee user,
			@Valid Holiday holiday,
			Model model,
			RedirectAttributes attributes)
	{
		//the holiday is greater than today, and there is enough days to allocate
		if (holiday.validDateRange() && user.getEntitlement() >= holiday.daysBetween()) 
		{		
			user.getHolidays().add(holiday);
			holiday.setEmplId(user);
			holiday.setNumDays(holiday.daysBetween());
			holidayService.save(holiday);
			model.addAttribute("employee", user);
			return new RedirectView("details"); 
		}
		else
		{
			attributes.addFlashAttribute("message", "The date range is incorrect!");
			return new RedirectView("details"); 
		}
		
		
	}
	
}
	

