package com.Usuario.usuario.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Usuario.security.dto.JwtDto;
import com.Usuario.security.dto.LoginUsuario;
import com.Usuario.security.dto.NuevoUsuario;
import com.Usuario.security.dto.TokenDto;
import com.Usuario.security.enums.RolNombre;
import com.Usuario.security.jwt.JwtProvider;
import com.Usuario.usuario.entity.Rol;
import com.Usuario.usuario.entity.Usuario;
import com.Usuario.usuario.service.RolServiceImpl;
import com.Usuario.usuario.service.UsuarioServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/usuario/auth")
public class UsuarioController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private RolServiceImpl rolService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Value("${secretPsw}")
	private String secretPsw;
	
	@PostMapping("/crear")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseEntity.badRequest().build();
		if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
			return ResponseEntity.badRequest().build();
		Usuario usuario = new Usuario();
		usuario.setNombre(nuevoUsuario.getNombre());
		usuario.setNombreUsuario(nuevoUsuario.getNombreUsuario());
		usuario.setEmail(nuevoUsuario.getEmail());
		usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
		Set<Rol> roles = new HashSet<>();
		if(nuevoUsuario.getRoles().contains("cliente"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_CLIENTE).get());
		if(nuevoUsuario.getRoles().contains("jefe"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		usuario.setRoles(roles);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	@PostMapping("/crear-cliente")
	public ResponseEntity<?> nuevoCliente(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseEntity.badRequest().build();
		if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
			return ResponseEntity.badRequest().build();
		Usuario usuario = new Usuario();
		usuario.setNombre(nuevoUsuario.getNombre());
		usuario.setNombreUsuario(nuevoUsuario.getNombreUsuario());
		usuario.setEmail(nuevoUsuario.getEmail());
		usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
		//usuario.setCliente(nuevoUsuario.getCliente());
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_CLIENTE).get());
		usuario.setRoles(roles);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	/*Puede que no se implemente*/
	@PostMapping("/cargar")
	public ResponseEntity<?> cargar(@RequestBody Usuario usuario) {
		Optional<Usuario> o = usuarioService.getByEmail(usuario.getEmail());
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Usuario usuarioDb = o.get();
		usuarioDb.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioDb.setNombre(usuario.getNombre());
		usuarioDb.setNombreUsuario(usuario.getNombreUsuario());
		usuarioDb.setEmail(usuario.getEmail());
		//usuarioDb.setCliente(usuario.getCliente());
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDb));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return ResponseEntity.badRequest().build();
		
		Authentication authentication = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsuario(), loginUsuario.getPassword()));
		System.out.println(authentication.toString());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		System.out.println(authentication.toString());
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updatePassword(@RequestBody LoginUsuario usuario) {
		Optional o = usuarioService.findByUsuario(usuario.getUsuario());
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		usuarioService.updatePassword(usuario.getPassword(), usuario.getUsuario());
		return ResponseEntity.ok().build();
		/*LoginUsuario usuarioDb = new LoginUsuario();
		usuarioDb.setUsuario(usuario.getUsuario());
		usuarioDb.setPassword(usuario.getPassword());
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDb);*/
	}
	
	private TokenDto login(Usuario usuario) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuario.getNombreUsuario(), secretPsw));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		TokenDto tokenDto = new TokenDto();
		tokenDto.setValue(jwt);
		return tokenDto;
	}

	private Usuario saveUsuario(String email) {
		Usuario usuario = new Usuario(email, email, passwordEncoder.encode(secretPsw));
		usuario.setNombreUsuario(email);
		Set<Rol> roles = new HashSet<>();
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_CLIENTE).get());
		usuario.setRoles(roles);
		return usuarioService.save(usuario);
	}
}
