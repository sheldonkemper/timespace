package com.timespace.services;

import java.util.Set;

public interface CrudService<T,ID> {

	Set<T> findAll();
	
	Set<T> findAllById(ID id);
	
	T findById(ID id);
	
	T save(T object);
	
	void delete(T object);
	
	void deleteById(ID id);



	


}