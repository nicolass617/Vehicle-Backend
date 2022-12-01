package com.company.vehicles.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.vehicles.dao.IBrandDao;
import com.company.vehicles.model.Brand;
import com.company.vehicles.response.BrandResponseRest;

@Service
public class BrandServiceImpl implements IBrandService {
	
	@Autowired
	private IBrandDao brandDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<BrandResponseRest> search() {
		
		BrandResponseRest response = new BrandResponseRest();
		
		try {
			
			List<Brand> brand = (List<Brand>) brandDao.findAll();
			
			response.getBrandResponse().setBrand(brand);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<BrandResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<BrandResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<BrandResponseRest> save(Brand brand) {
		
		BrandResponseRest response = new BrandResponseRest();
		List<Brand> list = new ArrayList<>();
		
		try {
			
			Brand brandSaved = brandDao.save(brand);
			
			if(brandSaved != null) {
				
				list.add(brandSaved);
				response.getBrandResponse().setBrand(list);
				response.setMetadata("Respuesta ok", "00", "Marca guardada");
				
			} else {
				
				response.setMetadata("Respuesta no ok", "-1", "Marca no guardada");
				return new ResponseEntity<BrandResponseRest>(response, HttpStatus.BAD_REQUEST);
				
			}
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al guardar la marca");
			e.getStackTrace();
			return new ResponseEntity<BrandResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<BrandResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<BrandResponseRest> searchById(Long id) {
		
		BrandResponseRest response = new BrandResponseRest();
		List<Brand> list = new ArrayList<>();
		
		try {
			
			Optional<Brand> brand = brandDao.findById(id);
			
			if(brand.isPresent()) {
				
				list.add(brand.get());
				response.getBrandResponse().setBrand(list);
				response.setMetadata("Respuesta ok", "00", "Marca encontrada");
				
			} else {
				
				response.setMetadata("Respuesta no ok", "-1", "Marca no encontrada");
				return new ResponseEntity<BrandResponseRest>(response, HttpStatus.NOT_FOUND);
				
			}
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta ok", "00", "Error al consultar marca por id");
			e.getStackTrace();
			return new ResponseEntity<BrandResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<BrandResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<BrandResponseRest> searchByName(String name) {
		
		BrandResponseRest response = new BrandResponseRest();
		List<Brand> list = new ArrayList<>();
		
		try {
			
			list = brandDao.findByNameContainingIgnoreCase(name);
			
			if(list.size() > 0) {
				
				response.getBrandResponse().setBrand(list);
				response.setMetadata("Respuesta ok", "00", "Marcas encontradas");
				
			} else {
				
				response.setMetadata("Respuesta no ok", "-1", "Marcas no encontradas");
				return new ResponseEntity<BrandResponseRest>(response, HttpStatus.NOT_FOUND);
				
			}
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Erros al consultar marcas por nombre");
			e.getStackTrace();
			return new ResponseEntity<BrandResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<BrandResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<BrandResponseRest> update(Brand brand, Long id) {
		
		BrandResponseRest response = new BrandResponseRest();
		List<Brand> list = new ArrayList<>();
		
		try {
			
			Optional<Brand> brandSearch = brandDao.findById(id);
			
			if(brandSearch.isPresent()) {
				
				brandSearch.get().setName(brand.getName());
				
				Brand brandUpdate = brandDao.save(brandSearch.get());
				
				if(brandUpdate != null) {
					
					list.add(brandUpdate);
					response.getBrandResponse().setBrand(list);
					response.setMetadata("Respuesta ok", "00", "Marca actualizada");
					
				} else {
					
					response.setMetadata("Respuesta ok", "00", "Marca no actualizada");
					return new ResponseEntity<BrandResponseRest>(response, HttpStatus.BAD_REQUEST);
					
				}
				
			} else {
				
				response.setMetadata("Respuesta ok", "00", "Marca no encontrada");
				return new ResponseEntity<BrandResponseRest>(response, HttpStatus.NOT_FOUND);
				
			}
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta ok", "00", "Error al actualizar la marca");
			e.getStackTrace();
			return new ResponseEntity<BrandResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<BrandResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<BrandResponseRest> delete(Long id) {
		
		BrandResponseRest response = new BrandResponseRest();
		
		try {
			
			brandDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Marca eliminada");
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al eliminar la marca");
			e.getStackTrace();
			return new ResponseEntity<BrandResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<BrandResponseRest>(response, HttpStatus.OK);
		
	}
	
	

}
