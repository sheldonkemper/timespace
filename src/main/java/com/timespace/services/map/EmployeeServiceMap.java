package com.timespace.services.map;

import java.util.Optional;
import java.util.Set;
import com.timespace.models.Employee;
import com.timespace.services.EmployeeService;

public class EmployeeServiceMap extends AbstractMapService<Employee,Long> implements EmployeeService
{

	@Override
	public Employee findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee save(Employee object) {
		return super.save(object.getId(), object);
	}
	
	@Override
	public Set<Employee> findAll() {
		
		return super.findAll();

	}

	@Override
	public Employee findById(Long id) {
		return super.findById(id);

	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
	@Override
	public void delete(Employee object) {
		super.delete(object);
	}

	@Override
	public Optional<Employee> findByEmplId(Integer emplId) {
		// TODO Auto-generated method stub
		return null;
	}



	
}