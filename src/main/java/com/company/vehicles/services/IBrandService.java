package com.company.vehicles.services;

import org.springframework.http.ResponseEntity;

import com.company.vehicles.model.Brand;
import com.company.vehicles.response.BrandResponseRest;

public interface IBrandService {

	public ResponseEntity<BrandResponseRest> search();
	public ResponseEntity<BrandResponseRest> searchById(Long id);
	public ResponseEntity<BrandResponseRest> searchByName(String name);
	public ResponseEntity<BrandResponseRest> save(Brand brand);
	public ResponseEntity<BrandResponseRest> update(Brand brand, Long id);
	public ResponseEntity<BrandResponseRest> delete(Long id);
	
}
