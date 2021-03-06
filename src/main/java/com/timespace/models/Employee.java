package com.timespace.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@Column(name = "entitlement")
	Integer entitlement;
	
	@Column(name = "contracted")
	Integer contracted;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "emplId")
	private Set<Holiday> holidays = new HashSet<>();

	@Builder
	public Employee(Long id, String firstName, String lastName) {
		super(id, firstName, lastName);

	}
	
	/**
	 * 
	 * @param entitlementComponent
	 */
	public void calculateEntitlement(EntitlementComponent entitlementComponent) {
		Integer postedEntitlement = this.getEntitlement();
		int calculatedEntitlement = entitlementComponent.calculateEntitlement(postedEntitlement,
				this.getStartDate());
		
		if (calculatedEntitlement != -1) {
			this.setEntitlement(calculatedEntitlement);
			this.setContracted(postedEntitlement);
		}
		else
		{
			this.setEntitlement(this.getEntitlement());
			this.setContracted( this.getEntitlement());
		}
	}

}