package com.timespace.services.jpa;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.timespace.models.Manager;
import com.timespace.repositories.ManagerRepository;
import com.timespace.services.ManagerService;


@Service
public class ManagerServiceJpa implements ManagerService
{

	private final ManagerRepository managerRepository;
	
	public ManagerServiceJpa(ManagerRepository managerRepository)
	{
		this.managerRepository = managerRepository;
	}

	@Override
	public Set<Manager> findAll()
	{
		Set<Manager> manager = new HashSet<>();
		this.managerRepository.findAll().forEach(manager::add);
		return manager;
	}

	@Override
	public Manager findById(Long id)
	{
		return this.managerRepository.findById(id).orElse(null);
	}

	@Override
	public Manager save(Manager object)
	{
		return this.managerRepository.save(object);
	}

	@Override
	public void delete(Manager object)
	{
		this.managerRepository.delete(object);
		
	}

	@Override
	public void deleteById(Long id)
	{
		this.managerRepository.deleteById(id);
		
	}


	@Override
	public Optional<Manager> findByEmplId(Integer emplId) {
		return managerRepository.findByEmplId(emplId);
	}


	
	
	


}