package com.DisneyWorld.Personaje.Service;

import java.util.Optional;

import com.DisneyWorld.Personaje.Model.Entity.Personaje;

public interface PersonajeService {

	public Iterable<Personaje> findAll();
	
	public Optional<Personaje> findById(Long id);
	
	public Personaje save(Personaje personaje);
	
	public void deleteById(Long id);
	
	public Iterable<Personaje> findByName(String nombre);
	
	public Iterable<Personaje> findByAge(int edad);
	
	public Iterable<Personaje> findByWeight(float peso);
	
}
