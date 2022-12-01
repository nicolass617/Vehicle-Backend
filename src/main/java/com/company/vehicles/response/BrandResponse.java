package com.company.vehicles.response;

import java.util.List;

import com.company.vehicles.model.Brand;

import lombok.Data;

@Data
public class BrandResponse {
	
	private List<Brand> brand;

}
