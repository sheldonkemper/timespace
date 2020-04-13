package com.timespace.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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

	@ManyToOne(fetch = FetchType.EAGER)
	Manager manager;

	@Column(name = "empl_id")
	Integer emplId;

	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate startDate;
	
	
	@Column(name="entitlement")
	Integer entitlement;
	
	Long managerEmplId;
	
	
	  @Builder 
	  public Employee(Long id, String firstName, String lastName) { 
		  super(id,firstName, lastName); 
	  
	  }
	  
	  void setManagerId()
	  {
		  this.managerEmplId = manager.getId();
	  }
	 
	
	public void calculateEntitlement(EntitlementComponent entitlementComponent)
	{
		int calculatedEntitlement = entitlementComponent.calculateEntitlement(this.getEntitlement(), this.getStartDate());
		if(calculatedEntitlement !=0)
		{
			this.entitlement = calculatedEntitlement;
		}
	}

	

}