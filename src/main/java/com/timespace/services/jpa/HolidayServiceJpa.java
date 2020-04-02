package com.timespace.services.jpa;


import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.timespace.models.Holiday;
import com.timespace.repositories.HolidayRepository;
import com.timespace.services.HolidayService;



@Service
public class HolidayServiceJpa implements HolidayService
{

	private final HolidayRepository holidayRepository;
	
	public HolidayServiceJpa(HolidayRepository holidayRepository)
	{
		this.holidayRepository = holidayRepository;
	}

	@Override
	public Set<Holiday> findAll() {
		Set<Holiday> holiday = new HashSet<>();
		this.holidayRepository.findAll().forEach(holiday::add);
		return holiday;
	}

	@Override
	public Holiday findById(Long id) {
		return this.holidayRepository.findById(id).orElse(null);
	}

	@Override
	public Holiday save(Holiday object) {
		return this.holidayRepository.save(object);
	}

	@Override
	public void delete(Holiday object) {
		this.holidayRepository.delete(object);
		
	}

	@Override
	public void deleteById(Long id) {
		this.holidayRepository.deleteById(id);
		
	}




}