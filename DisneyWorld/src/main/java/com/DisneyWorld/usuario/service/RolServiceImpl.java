package com.DisneyWorld.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DisneyWorld.Security.enums.RolNombre;
import com.DisneyWorld.usuario.entity.Rol;
import com.DisneyWorld.usuario.repository.RolRepository;

@Service
@Transactional
public class RolServiceImpl {

	@Autowired
	private RolRepository rolRepository;
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
		return rolRepository.findByRolNombre(rolNombre);
	}
	
	public Rol save(Rol rol) {
		return rolRepository.save(rol);
	}
	
	public Iterable<Rol> getAll(){
		return rolRepository.findAll();
	}
}
