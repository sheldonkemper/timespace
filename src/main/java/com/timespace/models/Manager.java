package com.timespace.models;


import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;
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
@Table(name="manager")
public class Manager extends BaseEntity
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="empl_id")
	Integer emplId;
	 
	@OneToMany()
	 @JoinColumn(name = "manager_id")
	 private Set<Employee> subordinates  = new HashSet<>();
	
	 @OneToOne(cascade=CascadeType.ALL)
	 @JoinColumn(name = "department_id")
	 private Department department;
 
	 @Builder
	public Manager(Employee emp)
	 {
		 this.emplId = emp.getEmplId();
	 }

	 
    
}