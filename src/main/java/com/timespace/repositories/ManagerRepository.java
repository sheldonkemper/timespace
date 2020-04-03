package com.timespace.repositories;

import org.springframework.data.repository.CrudRepository;

import com.timespace.models.Manager;

public interface ManagerRepository extends CrudRepository<Manager,Long>
{
	//Manager findByLastName(String lastName);
}