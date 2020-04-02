package com.timespace.services.jpa;


import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.timespace.models.Department;
import com.timespace.repositories.DepartmentRepository;
import com.timespace.services.DepartmentService;



@Service
public class DepartmentServiceJpa implements DepartmentService
{

	private final DepartmentRepository departmentRepository;
	
	public DepartmentServiceJpa(DepartmentRepository departmentRepository)
	{
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Set<Department> findAll() {
		Set<Department> department = new HashSet<>();
		this.departmentRepository.findAll().forEach(department::add);
		return department;
	}

	@Override
	public Department findById(Long id) {
		return this.departmentRepository.findById(id).orElse(null);
	}

	@Override
	public Department save(Department object) {
		return this.departmentRepository.save(object);
	}

	@Override
	public void delete(Department object) {
		this.departmentRepository.delete(object);
		
	}

	@Override
	public void deleteById(Long id) {
		this.departmentRepository.deleteById(id);
		
	}




}