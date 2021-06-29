package com.ejemplo.crud.producto.service;

import java.util.Optional;

import com.ejemplo.crud.producto.entity.Producto;

//Usamos na interfaz por si el método se trabaja de forma distinta en otra implementacio
public interface ProductoService {

	public Iterable<Producto> findAll();
	
	public Optional<Producto> findById(Long id);
	
	public Producto save(Producto producto);
	
	public void deleteById(Long id);
}
