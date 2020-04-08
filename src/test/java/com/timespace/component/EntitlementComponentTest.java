package com.timespace.component;


import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

class EntitlementComponentTest {
	
	EntitlementComponent entitlement;
	private final Integer CONTRACT = 25;
	LocalDate startDate = LocalDate.of(2020,04,05);

	@BeforeEach()
	void setup()
	{
		entitlement = EntitlementComponent.builder().build();
	}

	/**
	 *  given a start date of 05/04/2020, calculate that the 
	 *  days of entitlement returned will equal 19
	 */
    @Test
    public void calculateEntitlementTest() {
       
    	Integer value = entitlement.calculateEntitlement(CONTRACT,startDate);
    	Assertions.assertEquals(19 ,value );
    
    }

}
