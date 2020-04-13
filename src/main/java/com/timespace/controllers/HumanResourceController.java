package com.timespace.controllers;


import java.util.Set;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.timespace.component.EntitlementComponent;
import com.timespace.models.Employee;
import com.timespace.models.Manager;
import com.timespace.services.EmployeeService;
import com.timespace.services.ManagerService;

@RequestMapping("/humanresource")
@Controller
public class HumanResourceController 
{

	private final EmployeeService employeeService;
	private final EntitlementComponent entitlementComponent;
	private final ManagerService managerService;
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "employee/details/";
	
	public HumanResourceController(ManagerService managerService,EmployeeService employeeService,EntitlementComponent entitlementComponent)
	{
		this.employeeService = employeeService;
		this.entitlementComponent = entitlementComponent;
		this.managerService = managerService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder)
	{
		dataBinder.setDisallowedFields("id");
	}
	@RequestMapping({"/listemployee"})
	public String listEmployee(ModelMap model)
	{
		model.addAttribute("employees",employeeService.findAll())
		.addAttribute("manager", managerService.findAll());
		return "humanresource/listEmployees";
	}
	
	@GetMapping("/addemployee")
	public String addEmployee(Model model) 
	{
		model.addAttribute("employee",Employee.builder().build());
		
		return "humanresource/addEmployee";
	}
	
	@GetMapping("/editemployee/{employeeId}")
	public String  addEmployee(@PathVariable("employeeId") Long employeeId,@ModelAttribute("employee") Employee employee,@ModelAttribute("manager") Manager manager,
		      BindingResult result, ModelMap model) 
	{	
		 Employee empl = this.employeeService.findById(employeeId);
		 Set<Manager> manager1 = this.managerService.findAll();
		 model.addAttribute("employee",empl).addAttribute("manager",manager1);
		 return "humanresource/editEmployee";
	}
	
	/*
	 * @PostMapping("/editemployee/{employeeId}")
	 * 
	 * @ResponseBody public String editEmployee(@RequestParam Map <String,String>
	 * allParams) { System.out.print("Parameters are " + allParams.entrySet());
	 * return null;
	 * 
	 * }
	 */
	
	
	  @PostMapping("/editemployee/{employeeId}") 
	  public String editEmployee(@Valid Employee employee,BindingResult result, 
			  @PathVariable("employeeId") long employeeId ,
			  @RequestParam("manager") Manager  manager) 
	  { 
		  if (result.hasErrors()) 
		  { 
			  return VIEWS_OWNER_CREATE_OR_UPDATE_FORM; 
			  } 
		  else { 
			  employee.setId(employeeId);
			  this.employeeService.save(employee); 
			  
			  Manager aManager = managerService.findById(manager.getId());
			  aManager.getSubordinates().add(employee); 
			  this.managerService.save(aManager);
			  
			  return "redirect:/employee/details/{employeeId}"; 
			  } 
		  }
	 
	
	@PostMapping("/saveemployee")
	public String processNewEmployeeForm(@Valid Employee employee,BindingResult result)
	{
			if(result.hasErrors())
			{
				return "humanresource/addEmployee";
			}
			else
			{
				employee.calculateEntitlement(entitlementComponent);
				Employee savedEmployee = employeeService.save(employee);
				return "redirect:/employee/details/"+savedEmployee.getId();
				
			}

	}
	
}
