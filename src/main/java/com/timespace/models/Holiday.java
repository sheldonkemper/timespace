package com.timespace.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="holiday")
public class Holiday extends BaseEntity{
	    /**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		@Id
	    private Long id;
	    
		@Column(name="start_date")
	    private String startDate;
		
		@Column(name="end_date")
	    private String endDate;
	    
		
	    

	}

