package com.timespace.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="department")
public class Department extends BaseEntity{
	    /**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		@Id
	    private Long id;
	    
		@Column(name="name")
	    private String name;
	    
		@Builder.Default
	    @OneToMany(mappedBy="department")
	    private List<Employee> employees = new ArrayList<>();
	    

	}

