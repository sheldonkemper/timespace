package com.timespace.component;

import java.time.LocalDate;
import java.time.ZoneId;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Given a employee start date and contractual holiday entitlement, calculate
 * holiday for rest of year. This would be used for new employee, as exiting
 * employee holiday will default to 25 days.
 * 
 * @author Sheldon Kemper
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EntitlementComponent {
	private final LocalDate now = LocalDate.now(ZoneId.of("Europe/London"));
	private final LocalDate yearEnd = this.now.with(lastDayOfYear());
	private final LocalDate yearStart = this.now.with(firstDayOfYear());
	private LocalDate startdate;
	private Integer contract;
	/**
	 * Accepts a start date and the contractual . The date is then converted into
	 * the correct format, before the entitlement is calculated until the end of the
	 * given year.
	 * 
	 * @param localDate   LocalDate of the employees start date
	 * @param contractual Integer of the contractual holiday entitlement
	 */

	/**
	 * Get the number of days between, given day, and end of year, then calculate
	 * holiday entitle, rounding up to the nearest day.
	 * 
	 * @return
	 */
	public int calculateEntitlement(Integer contract, LocalDate startdate) {
		this.setStartdate(startdate);
		this.setContract(contract);
		
		long ytd = this.calculateDaysBetweenTwoDates();

		float v = (float) ytd / this.getsDaysInYear() * this.getContract();
		return (int) Math.ceil(v);

	}

	/**
	 * Given a start date calculate the number of days until end of year
	 * 
	 * @return Long the number of days between the employee start date and end of
	 *         year
	 * 
	 */
	private long calculateDaysBetweenTwoDates() {
		// check if the start date falls within the given year
		
		if (this.isDateinYear()) {
			return DAYS.between(this.getStartdate(), this.yearEnd);
		}
		return 0;
		// @TODO what to return is date is not in given year?
	}

	private Boolean isDateinYear() {
		if (this.getStartdate().isAfter(this.now) || this.getStartdate().isBefore(this.yearStart)
				|| this.getStartdate().isAfter(this.yearEnd)) {
			return false;

		} else {

			return true;
		}
	}

	private Integer getsDaysInYear() {
		return this.now.isLeapYear() ? 366 : 365;
	}

}
