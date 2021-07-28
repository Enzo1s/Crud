package com.DisneyWorld.Personaje.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DisneyWorld.Personaje.Model.Entity.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long>{

	public Iterable<Personaje> findByNombre(String nombre);
	
	public Iterable<Personaje> findByEdad(int edad);
	
	public Iterable<Personaje> findByPeso(float peso);
}
