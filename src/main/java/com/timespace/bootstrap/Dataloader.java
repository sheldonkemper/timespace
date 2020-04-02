package com.timespace.bootstrap;

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
		
		Employee emp1 = new Employee();
		emp1.setFirstName("Paul");
		
		Employee emp2 = new Employee();
		emp2.setFirstName("Bryan");
		
		emp1.setManager(manager);
		
		emp2.setManager(manager);
		
		manager.getSubordinates().add(emp1);
		manager.getSubordinates().add(emp2);
		
		//employeeService.save(emp1);
		//employeeService.save(emp2);
		//employeeService.save(manager);
		
		
		manager.getSubordinates().forEach((n) -> System.out.print(n.getFirstName()));
		System.out.print("\n");
		manager.getSubordinates().forEach((n) -> System.out.print(n.getManager().getFirstName()));
		
	}


}
