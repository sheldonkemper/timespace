package com.timespace.services;

import org.springframework.stereotype.Service;
import com.timespace.models.Holiday;

@Service
public interface HolidayService extends CrudService<Holiday, Long>
{
   
}