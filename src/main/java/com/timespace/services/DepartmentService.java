package com.timespace.services;

import org.springframework.stereotype.Service;

import com.timespace.models.Department;

@Service
public interface DepartmentService extends CrudService<Department, Long>
{
   
}