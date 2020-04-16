package com.timespace.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.timespace.models.Holiday;
import com.timespace.models.Manager;

public interface HolidayRepository extends CrudRepository<Holiday,Long>
{
	Optional<Holiday> findAllByEmplId(Long id);
}