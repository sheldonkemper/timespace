package com.timespace.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.timespace.models.Manager;

@Service
public interface ManagerService extends CrudService<Manager, Long>
{
    //Manager findByLastName(String lastName);
    Optional<Manager> findByEmplId(Integer emplId);
}