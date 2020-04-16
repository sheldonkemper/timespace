package com.timespace.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.timespace.models.Manager;

public interface ManagerRepository extends CrudRepository<Manager,Long>
{
	Optional<Manager> findByEmplId(Integer emplId);
}