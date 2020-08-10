package com.timespace.services;

import java.awt.List;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import com.timespace.models.Holiday;

import org.springframework.stereotype.Service;


@Service
public interface HolidayService extends CrudService<Holiday, Long>
{

	Optional<Holiday> findAllByEmplId(Integer emplId);
	
	Set<Holiday> findAllByStartDate(LocalDate startDate);
}