package com.company.vehicles.response;

import java.util.List;

import com.company.vehicles.model.Vehicle;

import lombok.Data;

@Data
public class VehicleResponse {
	
	private List<Vehicle> vehicle;

}
