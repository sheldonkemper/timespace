package com.timespace.services.jpa;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.timespace.models.Employee;
import com.timespace.repositories.EmployeeRepository;
import com.timespace.services.EmployeeService;


@Service
public class EmployeeServiceJpa implements EmployeeService
{

	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceJpa(EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Set<Employee> findAll()
	{
		Set<Employee> employee = new HashSet<>();
		this.employeeRepository.findAll().forEach(employee::add);
		return employee;
	}

	@Override
	public Employee findById(Long id)
	{
		return this.employeeRepository.findById(id).orElse(null);
	}

	@Override
	public Employee save(Employee object)
	{
		return this.employeeRepository.save(object);
	}

	@Override
	public void delete(Employee object)
	{
		this.employeeRepository.delete(object);
		
	}

	@Override
	public void deleteById(Long id)
	{
		this.employeeRepository.deleteById(id);
		
	}

	@Override
	public Employee findByLastName(String lastName)
	{
		return this.employeeRepository.findByLastName(lastName);
	}

	@Override
	public Optional<Employee> findByEmplId(Integer emplId) {
		return employeeRepository.findByEmplId(emplId);
	}

	@Override
	public Set<Employee> findAllById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


}