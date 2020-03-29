package com.timespace.models;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "manager")
public class Manager extends Person 
{
	/**
	 * 
	 */
	@Column(name="manager_id")
	 Integer managerId;

	@Column(name ="dept" )
	String dept;
	

	
	
}