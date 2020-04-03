package com.timespace.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.timespace.component.EntitlementComponent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Sheldon Kemper
 */

@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee extends Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	Manager manager;

	@Column(name = "empl_id")
	Integer emplId;

	@Column(name = "start_date")
	LocalDate startDate;
	
	
	@Column(name="entitlement")
	Integer entitlement;

	@Builder
	public Employee(Long id, String firstName, String lastName, LocalDate startDate, Integer emplId) {
		super(id, firstName, lastName);
		this.startDate = startDate;
		this.emplId = emplId;
	
	}
	
	public void calculateEmployeeEntitlement(EntitlementComponent entitle)
	{
		  LocalDate start =  this.startDate;
		  entitle.setStartdate(start);
		  entitle.calculateEntitlement();
		  int yearEnd = entitle.getEntitlement();
		  this.entitlement = yearEnd;
		
	}
	

}