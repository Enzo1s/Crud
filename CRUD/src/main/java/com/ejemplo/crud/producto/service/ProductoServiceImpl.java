package com.ejemplo.crud.producto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.crud.producto.entity.Producto;
import com.ejemplo.crud.producto.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired //Permite la inyección de dependencias
	private ProductoRepository productoRepository;
	
	@Override
	public Iterable<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public Optional<Producto> findById(Long id) {
		return productoRepository.findById(id);
	}

	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public void deleteById(Long id) {
		productoRepository.deleteById(id);
	}

}
