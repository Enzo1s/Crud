package com.Commonsmicroservicios.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonService<E> {

	public Iterable<E> findAll();
	
	public Optional<E> findById(Long id);
	
	public E save(E entity);
	
	public void deleteById(Long id);

	Page<E> findAll(Pageable pageable);
}