package com.company.vehicles.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.vehicles.model.Brand;

public interface IBrandDao extends CrudRepository<Brand, Long> {
	
	List<Brand> findByNameContainingIgnoreCase(String name);

}
