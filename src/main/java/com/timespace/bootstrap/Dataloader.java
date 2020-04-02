package com.timespace.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.timespace.models.Employee;
import com.timespace.services.EmployeeService;



@Component
public class Dataloader  implements CommandLineRunner{

	private final EmployeeService employeeService;
	
	public Dataloader (EmployeeService employeeService)
	{
		this.employeeService = employeeService;
		
	}

	@Override
	public void run(String... args) throws Exception {
		this.loadData();
	}
	
	private void loadData()
	{
		
		Employee manager =new Employee();
		manager.setFirstName("Sheldon");
		manager.setLastName("Kemper");
		manager.setEmplId(12130);
		LocalDate startDate = LocalDate.of(2003, 03, 10);
		manager.setStartDate(startDate);
		
		Employee emp1 = new Employee();
		emp1.setFirstName("Paul");
		emp1.setLastName("Versey");
		emp1.setEmplId(12345);
		LocalDate startDate1 = LocalDate.of(2003, 03, 14);
		emp1.setStartDate(startDate1);
		employeeService.save(emp1);
		
		Employee emp2 = new Employee();
		emp2.setFirstName("Bryan");
		emp2.setLastName("Moore");
		LocalDate startDate2 = LocalDate.of(2004, 11, 24);
		emp2.setStartDate(startDate2);
		emp2.setEmplId(22346);
		employeeService.save(emp2);
		
		emp1.setManager(manager);
		emp2.setManager(manager);
		manager.getSubordinates().add(emp1);
		manager.getSubordinates().add(emp2);
		employeeService.save(manager);

		
		
		manager.getSubordinates().forEach((n) -> System.out.print(n.getFirstName()));
		System.out.print("\n");
		manager.getSubordinates().forEach((n) -> System.out.print(n.getManager().getFirstName()));
		
	}


}
