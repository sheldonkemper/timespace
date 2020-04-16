package com.timespace.models;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

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
		@DateTimeFormat(pattern = "yyyy-MM-dd")
	    private LocalDate startDate;
		
		@Column(name="end_date")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
	    private LocalDate endDate;
		
		@Column(name="description")
	    private String description;
		
		@Column(name="granted")
		@Builder.Default
	    private  String granted = "Awaiting";
	    
		@ManyToOne
		@JoinColumn(name = "empl_id")
		private Employee emplId;
	    
		public Boolean validDateRange()
		{
			return this.startDate.isBefore(this.endDate);
		}
		
		public Long daysBetween()
		{
			return DAYS.between(this.startDate,this.endDate);
		}

	}

