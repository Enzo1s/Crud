package com.Commonsmicroservicios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Commonsmicroservicios.services.CommonService;


public class CommonController<E, S extends CommonService<E>> {

	@Autowired
	protected S service;

	
	@GetMapping("/all") // indicamos que responde a una petición de tipo get a http://localhost:PORT/api/v1/producto
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
	}
	
	@GetMapping("/{id}") //En este caso se pasa el id del producto a buscar y el método lo lee por el PathVariable
	public ResponseEntity<?> findbyId(@PathVariable Long id){
		Optional<E> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		E entityDb = o.get();
		return ResponseEntity.status(HttpStatus.OK).body(entityDb);
	}
	
	@PostMapping("/crear") //El objeto lo pasamos por el body
	public ResponseEntity<?> create(@RequestBody E entity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.badRequest().body("{\"error\":\"Eliminado\"}");
	}

}
