package com.DisneyWorld.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.DisneyWorld.usuario.entity.Usuario;
import com.DisneyWorld.usuario.entity.UsuarioPrincipal;
import com.DisneyWorld.usuario.entity.UsuarioPrincipalFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		Usuario usuario = usuarioServiceImpl.findByUsuario(nombreUsuario).get();
		return UsuarioPrincipal.build(usuario);
	}
	
    public UserDetails loadUserByUsernameEmail(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioServiceImpl.getByEmail(email).orElseThrow(()-> new UsernameNotFoundException("email no encontrado"));
        return UsuarioPrincipalFactory.build(usuario);
    }

}
