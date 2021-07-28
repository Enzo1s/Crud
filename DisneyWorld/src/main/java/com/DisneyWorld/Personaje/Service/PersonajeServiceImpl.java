package com.DisneyWorld.Personaje.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DisneyWorld.Personaje.Model.Entity.Personaje;
import com.DisneyWorld.Personaje.Model.Repository.PersonajeRepository;

@Service
public class PersonajeServiceImpl implements PersonajeService{

	@Autowired
	private PersonajeRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Personaje> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Personaje> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Personaje save(Personaje personaje) {
		return repository.save(personaje);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);		
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Personaje> findByName(String nombre) {
		return repository.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Personaje> findByAge(int edad) {
		return repository.findByEdad(edad);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Personaje> findByWeight(float peso) {
		return repository.findByPeso(peso);
	}

}
