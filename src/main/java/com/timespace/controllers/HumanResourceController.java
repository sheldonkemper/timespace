package com.timespace.controllers;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.timespace.models.Department;
import com.timespace.models.Employee;
import com.timespace.models.Manager;
import com.timespace.services.DepartmentService;
import com.timespace.services.EmployeeService;
import com.timespace.services.ManagerService;

@RequestMapping("/humanresource")
@Controller
public class HumanResourceController {

	private final EmployeeService employeeService;
	private final DepartmentService departmentService;
	private final EntitlementComponent entitlementComponent;
	private final ManagerService managerService;
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "employee/details/";

	public HumanResourceController(DepartmentService departmentService, ManagerService managerService,
			EmployeeService employeeService, EntitlementComponent entitlementComponent) {
		this.employeeService = employeeService;
		this.entitlementComponent = entitlementComponent;
		this.managerService = managerService;
		this.departmentService = departmentService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@Autowired
	@PreAuthorize("hasRole('HR')")
	@RequestMapping("/")
	public String home(HttpServletRequest request) throws Exception {
		 return "humanresource/listEmployees";
	}

	@RequestMapping({ "/listemployee" })
	public String listEmployee(ModelMap model) {
		model.addAttribute("employees", employeeService.findAll()).addAttribute("manager", managerService.findAll());
		return "humanresource/listEmployees";
	}

	@GetMapping("/addemployee")
	public String addEmployee(Model model) {
		model.addAttribute("employee", Employee.builder().build());

		return "humanresource/addEmployee";
	}
	
	@PostMapping("/saveemployee")
	public String processNewEmployeeForm(@Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) {
			return "humanresource/addEmployee";
		} else {
			employee.calculateEntitlement(entitlementComponent);
			Employee savedEmployee = employeeService.save(employee);
			return "redirect:/employee/details/" + savedEmployee.getId();

		}
	}

	@GetMapping("/editemployee/{employeeId}")
	public String addEmployee(@PathVariable("employeeId") Long employeeId,
			@ModelAttribute("employee") Employee employee, @ModelAttribute("manager") Manager manager,
			BindingResult result, ModelMap model) {
		Employee empl = this.employeeService.findById(employeeId);
		Set<Manager> manager1 = this.managerService.findAll();
		model.addAttribute("employee", empl).addAttribute("manager", manager1);
		return "humanresource/editEmployee";
	}

	@PostMapping("/editemployee/{employeeId}")
	public String editEmployee(@Valid Employee employee, BindingResult result,
			@PathVariable("employeeId") long employeeId, @RequestParam("manager") Manager manager) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			employee.setId(employeeId);
			this.employeeService.save(employee);

			Manager aManager = managerService.findById(manager.getId());
			aManager.getSubordinates().add(employee);
			this.managerService.save(aManager);

			return "redirect:/employee/details/{employeeId}";
		}
	}

	@GetMapping("/addmanager/{employeeId}")
	public String addManager(@PathVariable("employeeId") Long employeeId, ModelMap model) {
		model.addAttribute("employee", this.employeeService.findById(employeeId)).addAttribute("department",
				this.departmentService.findAll());

		return "humanresource/addManager";
	}

	@PostMapping("/addmanager/{employeeId}")
	public String editManager(@Valid Employee employee, @PathVariable("employeeId") long employeeId,
			@RequestParam("manager") Department department) {

		boolean aManager = managerService.findByEmplId(employee.getEmplId()).isPresent();
		// Check if manager exists
		if (aManager) {
			Manager aExistingManager = managerService.findByEmplId(employee.getEmplId()).get();
			aExistingManager.setDepartment(department);
			this.managerService.save(aExistingManager);
		} else {

			Manager aNewManager = Manager.builder().emp(employee).build();
			aNewManager.setDepartment(department);
			managerService.save(aNewManager);
		}

		return "redirect:/employee/details/{employeeId}";
	}
	
	@GetMapping("/newdepartment/{employeeId}")
	public String addDepartment(@PathVariable("employeeId") long employeeId,Model model) {
		model.addAttribute("department", Department.builder().build());

		return "humanresource/addDepartment";
	}
	
	@PostMapping("/newdepartment/{employeeId}")
	public String processNewDepartmentForm(@PathVariable("employeeId") long employeeId,@Valid Department department) {
	
			
			departmentService.save(department);
			return "redirect:/humanresource/addmanager/"+employeeId;

		
	}

}
