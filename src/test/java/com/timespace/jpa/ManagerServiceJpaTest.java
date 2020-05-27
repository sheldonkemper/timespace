package com.timespace.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.timespace.models.Employee;
import com.timespace.models.Manager;
import com.timespace.repositories.ManagerRepository;
import com.timespace.services.EmployeeService;
import com.timespace.services.ManagerService;
import com.timespace.services.jpa.EmployeeServiceJpa;
import com.timespace.services.jpa.ManagerServiceJpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManagerServiceJpaTest {
	public static final String LAST_NAME = "Versey";

	@Mock
	private ManagerRepository managerRepository;

	@Mock
	private ManagerService managerService;

	@InjectMocks
	private ManagerServiceJpa service;

	private Manager returnedManager;

	@BeforeEach
	void setUp() {
		Employee employee = Employee.builder().id(1l).lastName(LAST_NAME).build();
		returnedManager = new Manager(employee);
	}

	@Test
	void findById() {
		when(managerRepository.findById(anyLong())).thenReturn(Optional.of(returnedManager));
		Manager manager = service.findById(1L);
		assertNotNull(manager);
	}
	
	 
	  @Test void save() { 
		  service.save(returnedManager);
		  verify(managerRepository, times(1)).save(any());
	  }

	
	  @Test void delete() { 
		  service.delete(returnedManager);
		  verify(managerRepository, times(1)).delete(any());
	  }
	  
	  //default is 1 times verify(managerRepository, times(1)).delete(any()); }
	  
	    @Test
	    void deleteById() {
	        service.deleteById(1L);

	        verify(managerRepository).deleteById(anyLong());
	    }
	 
}
