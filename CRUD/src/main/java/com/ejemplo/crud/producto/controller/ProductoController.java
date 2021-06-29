package com.ejemplo.crud.producto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.crud.producto.entity.Producto;
import com.ejemplo.crud.producto.service.ProductoServiceImpl;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping(path = "/api/v1/producto") //dirección utilizada en el front para acceder a los metodos
public class ProductoController {

	@Autowired
	private ProductoServiceImpl productoService;
	
	@GetMapping("/all") // indicamos que responde a una petición de tipo get a http://localhost:PORT/api/v1/producto
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productoService.findAll());
	}
	
	@GetMapping("/{id}") //En este caso se pasa el id del producto a buscar y el método lo lee por el PathVariable
	public ResponseEntity<?> findbyId(@PathVariable Long id){
		Optional<Producto> o = productoService.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Producto productoDb = o.get();
		return ResponseEntity.status(HttpStatus.OK).body(productoDb);
	}
	
	@PostMapping("/crear") //El objeto lo pasamos por el body
	public ResponseEntity<?> create(@RequestBody Producto producto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
	}
	
	@PutMapping("/{id}") // Como es una peticion de tipo put no hay conflicto con buscar por id
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Producto producto) {
		Optional<Producto> o = productoService.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Producto productoDb = o.get();
		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());
		productoDb.setDescripcion(producto.getDescripcion());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(productoDb));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		productoService.deleteById(id);
		return ResponseEntity.badRequest().body("{\"error\":\"Producto eliminado\"}");
	}
}
