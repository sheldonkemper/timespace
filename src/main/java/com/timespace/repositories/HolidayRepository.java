package com.timespace.repositories;

import java.awt.List;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import com.timespace.models.Holiday;

public interface HolidayRepository extends CrudRepository<Holiday,Long>
{
	Optional<Holiday> findAllByEmplId(Integer EmplId);
	
	Set<Holiday> findAllByStartDate(LocalDate startDate);

}