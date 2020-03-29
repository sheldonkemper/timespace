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
		
		Employee employee1 = new Employee();
		employee1.setFirstName("Sheldon");
		employee1.setLastName("Kemper");
		employee1.setId(12130L);
		LocalDate startDate1 = LocalDate.of(2020,03,28);
		employee1.setStartDate(startDate1);
		employeeService.save(employee1);
		
		System.out.print("Employee 1 created..");
		
		Employee employee2 = new Employee();
		employee2.setFirstName("Paul");
		employee2.setLastName("Hennigan");
		employee2.setId(140L);
		LocalDate startDate2 = LocalDate.of(2020,03,28);
		employee1.setStartDate(startDate2);
		employeeService.save(employee2);
		
		System.out.print("Employee 2 created..");
		
	}


}
