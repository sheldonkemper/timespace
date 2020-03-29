package com.timespace.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name="employee")
public class Employee extends Person
{



	@Column(name="type_id")
	String emplType;
	
	@Column(name="start_date")
    LocalDate startDate;
	
	 @Column(name="empl_id")
	 Integer emplId;
	 
	 @ManyToOne
	 @JoinColumn(name ="manager_id")
	 Manager manager;

	  @Builder
		public Employee(Long id, String firstName, String lastName, String emplType, LocalDate startDate, Manager manager,
				Integer emplId)
		{
			super(id, firstName, lastName);
			this.emplType = emplType;
			this.startDate = startDate;
			this.manager = manager;
			this.emplId = emplId;
		}
    
}