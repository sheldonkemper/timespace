package com.timespace.repositories;

import org.springframework.data.repository.CrudRepository;
import com.timespace.models.Department;

public interface DepartmentRepository extends CrudRepository<Department,Long>
{
	
}