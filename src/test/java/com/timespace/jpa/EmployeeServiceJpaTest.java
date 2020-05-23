package com.timespace.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.timespace.models.Employee;
import com.timespace.repositories.EmployeeRepository;
import com.timespace.services.EmployeeService;
import com.timespace.services.jpa.EmployeeServiceJpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceJpaTest
{
	public static final String LAST_NAME = "Versey";
	
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeServiceJpa service;

   

	private Employee returnedEmployee;
	
	

	  @BeforeEach
	    void setUp() 
	  {
	      returnedEmployee = Employee.builder().id(1l).lastName(LAST_NAME).build();
	    }
	  
	@Test
	void findById()
	{
		when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(returnedEmployee));
		Employee employee =  service.findById(1L);
		assertNotNull(employee);
	}
	    
	  
	   @Test
	    void findAll() {
	        Set<Employee> returnEmployeeSet = new HashSet<>();
	        returnEmployeeSet.add(Employee.builder().id(1l).build());
	        returnEmployeeSet.add(Employee.builder().id(2l).build());

	        when(employeeRepository.findAll()).thenReturn(returnEmployeeSet);

	        Set<Employee> employee = service.findAll();

	        assertNotNull(employee);
	        assertEquals(2, employee.size());
	    }
	
	   
	    @Test
	    void save() {
	        Employee employeeToSave = Employee.builder().id(1L).build();

	        when(employeeRepository.save(any())).thenReturn(returnedEmployee);

	        Employee savedEmployee = service.save(employeeToSave);

	        assertNotNull(savedEmployee);

	        verify(employeeRepository).save(any());
	    }
	    
	    @Test
	    void delete() {
	        service.delete(returnedEmployee);

	        //default is 1 times
	        verify(employeeRepository, times(1)).delete(any());
	    }
	    
	    @Test
	    void deleteById() {
	        service.deleteById(1L);

	        verify(employeeRepository).deleteById(anyLong());
	    }
}
