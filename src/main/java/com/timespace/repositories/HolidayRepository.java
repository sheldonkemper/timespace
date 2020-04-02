package com.timespace.repositories;

import org.springframework.data.repository.CrudRepository;
import com.timespace.models.Holiday;

public interface HolidayRepository extends CrudRepository<Holiday,Long>
{
	
}