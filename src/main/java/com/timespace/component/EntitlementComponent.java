package com.timespace.component;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.time.temporal.TemporalAdjusters;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Given a employee start date and contractual holiday entitlement, 
 * calculate holiday for rest of year.
 * This would be used for new employee, as exiting employee holiday will 
 * default to 25 days.
 * @author Sheldon Kemper
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class EntitlementComponent
{

    private LocalDate startdate;
    private LocalDate startDate;
    private int entitlement;
    private int contractualEntitlment;
    
    
    /**
     * Accepts a start date and the contractual .
     * The date is then converted into the correct format, before the 
     * entitlement is calculated until the end of the given year.
     * @param localDate  LocalDate of the employees start date
     * @param contractual Integer of the contractual holiday entitlement
     */

     
    /**
     * Get the number of days between, given day, and end of year,
     * then calculate holiday entitle, rounding up to the nearest day.
     */
    public void calculateEntitlement() 
    {
        float ytd = this.calculateDaysBetweenTwoDates();
        float v = (float)ytd / 365 * this.getContractualEntitlment();
        this.entitlement = (int)Math.ceil(v);
    }
    
    /**
     * Given a start and end date calculate the number of days.
     * @return Long the number of days between the employee start date and end of year
     */
    public long calculateDaysBetweenTwoDates() 
    {
	   	 long noOfDaysBetween = DAYS.between(this.getStartdate(), this.getYearEnd() );
	   	 return noOfDaysBetween;
    }
    
     
    public LocalDate getYearEnd() 
    {
    	LocalDate now = LocalDate.now();
        return now.with(lastDayOfYear());
     }
    
}
    