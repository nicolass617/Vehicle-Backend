package com.company.vehicles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.vehicles.model.Brand;
import com.company.vehicles.response.BrandResponseRest;
import com.company.vehicles.services.IBrandService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class BrandRestController {
	
	@Autowired
	private IBrandService service;
	
	@GetMapping("/brands")
	public ResponseEntity<BrandResponseRest> serachBrands() {
		
		ResponseEntity<BrandResponseRest> response = service.search();
		return response;
		
	}
	
	@PostMapping("/brands")
	public ResponseEntity<BrandResponseRest> saveBrand(@RequestBody Brand brand) {
		
		ResponseEntity<BrandResponseRest> response = service.save(brand);
		return response;
		
	}
	
	@GetMapping("/brands/{id}")
	public ResponseEntity<BrandResponseRest> searchById(@PathVariable Long id) {
		
		ResponseEntity<BrandResponseRest> response = service.searchById(id);
		return response;
		
	}
	
	@GetMapping("/brands/filter/{name}")
	public ResponseEntity<BrandResponseRest> searchByName(@PathVariable String name) {
		
		ResponseEntity<BrandResponseRest> response = service.searchByName(name);
		return response;
		
	}
	
	@PutMapping("/brands/{id}")
	public ResponseEntity<BrandResponseRest> updateBrand(@RequestBody Brand brand, @PathVariable Long id) {
		
		ResponseEntity<BrandResponseRest> response = service.update(brand, id);
		return response;
		
	}
	
}
