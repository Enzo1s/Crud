package com.Usuario.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Usuario.security.enums.RolNombre;
import com.Usuario.usuario.entity.Rol;
import com.Usuario.usuario.repository.RolRepository;

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
