package com.timespace.bootstrap;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.timespace.component.EntitlementComponent;
import com.timespace.models.Department;
import com.timespace.models.Employee;
import com.timespace.models.Holiday;
import com.timespace.models.Manager;
import com.timespace.services.EmployeeService;
import com.timespace.services.HolidayService;
import com.timespace.services.ManagerService;



@Component
public class Dataloader  implements CommandLineRunner{
	

	private final EmployeeService employeeService;
	private final ManagerService managerService;
	private final EntitlementComponent entitlementComponent;
	private HolidayService holidayService;
	
	public Dataloader (HolidayService holidayService,EmployeeService employeeService,ManagerService managerService,EntitlementComponent entitlementComponent)
	{
		this.employeeService = employeeService;
		this.managerService = managerService;
		this.entitlementComponent = entitlementComponent;
		this.holidayService = holidayService;
		
	}

	@Override
	public void run(String... args) throws Exception {
		this.loadData();
	}
	
	private void loadData()
	{
		
	
		Employee emp1 = new Employee();
		LocalDate startDate1 = LocalDate.of(2003, 03, 14);
		emp1.setFirstName("Paul");
		emp1.setLastName("Versey");
		emp1.setEmplId(12345);
		emp1.setStartDate(startDate1);
		emp1.setEntitlement(25);
		emp1.calculateEntitlement(entitlementComponent);
		employeeService.save(emp1);
		
		Holiday verseyHoliday = new Holiday();
		verseyHoliday.setEmplId(emp1);
		verseyHoliday.setStartDate(LocalDate.of(2020, 02, 11));
		verseyHoliday.setEndDate(LocalDate.of(2020, 02, 18));
		holidayService.save(verseyHoliday);
		
		Employee emp2 = new Employee();
		LocalDate startDate2 = LocalDate.of(2004, 11, 24);
		emp2.setFirstName("Bryan");
		emp2.setLastName("Moore");
		emp2.setStartDate(startDate2);
		emp2.setEmplId(22346);
		emp2.setEntitlement(25);
		emp2.calculateEntitlement(entitlementComponent);
		employeeService.save(emp2);
		
		Employee emp3 =new Employee();
		LocalDate startDate3 = LocalDate.of(2003, 03, 10);
		emp3.setFirstName("Sheldon");
		emp3.setLastName("Kemper");
		emp3.setEmplId(12130);
		emp3.setStartDate(startDate3);
		emp3.setEntitlement(25);
		emp3.calculateEntitlement(entitlementComponent);
		employeeService.save(emp3);
	
		Employee emp4 = new Employee();
		LocalDate startDate4 = LocalDate.of(1995, 12, 24);
		emp4.setFirstName("Hayley");
		emp4.setLastName("Mallet");
		emp4.setEmplId(12345);
		emp4.setStartDate(startDate4);
		emp4.setEntitlement(25);
		emp4.calculateEntitlement(entitlementComponent);
		employeeService.save(emp4);
		
		Employee emp5 = new Employee();
		LocalDate startDate5 = LocalDate.of(2020, 02, 04);
		emp5.setFirstName("Josh");
		emp5.setLastName("Stone");
		emp5.setStartDate(startDate5);
		emp5.setEmplId(43210);
		emp5.setEntitlement(25);
		emp5.calculateEntitlement(entitlementComponent);
		employeeService.save(emp5);
		
		Employee emp6 = new Employee();
		LocalDate startDate6 = LocalDate.of(2019, 04, 05);
		emp6.setFirstName("Clare");
		emp6.setLastName("Child");
		emp6.setStartDate(startDate6);
		emp6.setEmplId(12230);
		emp6.setEntitlement(25);
		emp6.calculateEntitlement(entitlementComponent);
		employeeService.save(emp6);
		
		
		Manager manager =new Manager(emp3);
		Department department = new Department("Risk & Claims");
		manager.setDepartment(department);
		manager.getSubordinates().add(emp1);
		manager.getSubordinates().add(emp2);
		managerService.save(manager);
		
		Manager manager1 =new Manager(emp6);
		Department department1 = new Department("Human Resource");
		manager1.setDepartment(department1);
		manager1.getSubordinates().add(emp4);
		manager1.getSubordinates().add(emp5);
		managerService.save(manager1);
		

	}


}
