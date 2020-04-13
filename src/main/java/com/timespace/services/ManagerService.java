package com.timespace.services;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.timespace.models.Manager;

@Service
public interface ManagerService extends CrudService<Manager, Long>
{
   
    Optional<Manager> findByEmplId(Integer emplId);
}