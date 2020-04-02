package com.timespace.component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.time.temporal.TemporalAdjusters;
import org.springframework.stereotype.Component;


/**
 * Given a employee start date and contractual holiday entitlement, 
 * calculate holiday for rest of year.
 * This would be used for new employee, as exiting employee holiday will 
 * default to 25 days.
 * @author Sheldon Kemper
 */
@Component

public class EntitlementComponent
{

    private String startdate;
    private LocalDate parseStartDate;
    private int entitlement;
    private int contractualEntitlment;
    
    
    /**
     * Accepts a start date and the contractual .
     * The date is then converted into the correct format, before the 
     * entitlement is calculated until the end of the given year.
     * @param localDate  LocalDate of the employees start date
     * @param contractual Integer of the contractual holiday entitlement
     */
    public EntitlementComponent(final String localDate, final int contractual) 
    {
	this.setContractualEntitlment(contractual);
        this.setStartdate(localDate);
        this.dateFormatter();
        this.calculateEntitlement();
    }

    public EntitlementComponent() 
    {
	
	 this.setContractualEntitlment(25);
	 }
    
    /**
     * Format a date into the expected pattern
     * @return String date
     */
     public String dateFormatter () 
     {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy"); 
         LocalDate parsedDate = LocalDate.parse(this.startdate, formatter);
         this.parseStartDate = parsedDate;
         return parsedDate.toString();
    }
     
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
    private long calculateDaysBetweenTwoDates() 
    {
	   	 long noOfDaysBetween = DAYS.between(parseStartDate, this.getYearEnd() );
	   	 return noOfDaysBetween;
    }
    
    //GETTERS AND SETTERS//
    
    public void setContractualEntitlment(int contractualEntitlment) 
    {
   	 this.contractualEntitlment = contractualEntitlment;
    }
   
    public void setStartdate(String startdate) 
    {
	 this.startdate = startdate;
    }

     public int getEntitlement() 
     {
         return entitlement;
     }
     
     String getStartdate() 
     {
    	 return startdate;
     }

     int getContractualEntitlment() 
     {
    	 return contractualEntitlment;
     }
     
    private LocalDate getYearEnd() 
    {
        return this.parseStartDate.with(TemporalAdjusters.lastDayOfYear());
     }
    
}
    