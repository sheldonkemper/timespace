package com.timespace.controllers;


import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.timespace.component.EntitlementComponent;
import com.timespace.models.Employee;
import com.timespace.services.EmployeeService;

@RequestMapping("/humanresource")
@Controller
public class HumanResourceController 
{

	private final EmployeeService employeeService;
	private final EntitlementComponent entitlementComponent;
	
	public HumanResourceController(EmployeeService employeeService,EntitlementComponent entitlementComponent)
	{
		this.employeeService = employeeService;
		this.entitlementComponent = entitlementComponent;
	}
	
	@RequestMapping({"/listemployee"})
	public String listEmployee(Model model)
	{
		model.addAttribute("employees",employeeService.findAll());
		return "humanresource/listEmployees";
	}
	
	@GetMapping("/addemployee")
	public String addEmployee(Model model) 
	{
		model.addAttribute("employee",Employee.builder().build());
		
		return "humanresource/addEmployee";
	}
	
	@PostMapping("/saveemployee")
	public String processNewEmployeeForm(@Valid Employee employee)
	{
			employee.calculateEntitlement(entitlementComponent);
			Employee savedEmployee = employeeService.save(employee);
			return "redirect:/employee/details/"+savedEmployee.getId();
	}
	
}
