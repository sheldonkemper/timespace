package com.timespace.models;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
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
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	 @Column(name="empl_id")
	 Integer emplId;

	@Column(name="start_date")
    LocalDate startDate;
	
	 @ManyToOne
	 @JoinColumn(name ="manager_id")
	 Employee manager;
	 
	
	 @ManyToOne
	 Department department;
	 
	@Builder.Default
	@OneToMany(mappedBy ="manager")
	 private List<Employee> subordinates  = new ArrayList<>();

	  @Builder
		public Employee(Long id, String firstName, String lastName, String emplType, LocalDate startDate,Integer emplId,Department department)
		{
			super(id, firstName, lastName);

			this.startDate = startDate;
			this.emplId = emplId;
			this.department = department;
		}
    
}