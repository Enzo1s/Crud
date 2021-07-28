package com.DisneyWorld.usuario.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DisneyWorld.Security.dto.JwtDto;
import com.DisneyWorld.Security.dto.LoginUsuario;
import com.DisneyWorld.Security.dto.NuevoUsuario;
import com.DisneyWorld.Security.enums.RolNombre;
import com.DisneyWorld.Security.jwt.JwtProvider;
import com.DisneyWorld.Util.SendGridEmail;
import com.DisneyWorld.usuario.entity.Rol;
import com.DisneyWorld.usuario.entity.Usuario;
import com.DisneyWorld.usuario.service.RolServiceImpl;
import com.DisneyWorld.usuario.service.UsuarioServiceImpl;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/auth")
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
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
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
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		usuario.setRoles(roles);
		Content content = new Content("text/html", "Bienvenido/a a esta pequeña api sobre el mundo de Disney");
		Email from = new Email("ejemplo@ejemplomail.com");
		Email to = new Email(usuario.getEmail());
		String subject = "Registro DisneyWorld";
		try {
			SendGridEmail.sendEmail(from, to, subject, content);
		} catch (IOException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\":\"No fue posible registrarse error en Email\"}");
			
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
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
}
