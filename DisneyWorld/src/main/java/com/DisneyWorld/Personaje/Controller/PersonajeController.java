package com.DisneyWorld.Personaje.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.DisneyWorld.Personaje.Model.Dto.PersonajeDetailDto;
import com.DisneyWorld.Personaje.Model.Dto.PersonajeDto;
import com.DisneyWorld.Personaje.Model.Entity.Personaje;
import com.DisneyWorld.Personaje.Service.PersonajeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/characters")
public class PersonajeController {

	@Autowired
	private PersonajeService service;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping
	public ResponseEntity<?> findAll() {
		Iterable<Personaje> personajes= service.findAll();
		List<PersonajeDto> res = new ArrayList<>();
		for (Personaje personaje : personajes) {
			res.add(new PersonajeDto(Base64.getEncoder().encodeToString(personaje.getImagen()),personaje.getNombre()));			
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Personaje personaje){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(personaje));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/create-img")
	public ResponseEntity<?> createImg(Personaje personaje, @RequestParam MultipartFile img) throws IOException{
		if(!img.isEmpty()) {
			personaje.setImagen(img.getBytes());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(personaje));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Personaje personaje, @PathVariable Long id) {
		Optional<Personaje> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Personaje personajeDb = o.get();
		personajeDb.setNombre(personaje.getNombre());
		personajeDb.setEdad(personaje.getEdad());
		personajeDb.setHistoria(personaje.getHistoria());
		personajeDb.setPeso(personaje.getPeso());
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(personajeDb));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update-img/{id}")
	public ResponseEntity<?> updateImg(Personaje personaje, @PathVariable Long id, @RequestParam MultipartFile img) throws IOException{
		Optional<Personaje> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Personaje personajeDb = o.get();
		personajeDb.setNombre(personaje.getNombre());
		personajeDb.setEdad(personaje.getEdad());
		personajeDb.setHistoria(personaje.getHistoria());
		personajeDb.setPeso(personaje.getPeso());
		if(!img.isEmpty()) {
			personajeDb.setImagen(img.getBytes());
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(personajeDb));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping(params = "name")
	public ResponseEntity<?> searchByName(@RequestParam(name = "name") String nombre){
		Iterable<Personaje> personajes = service.findByName(nombre);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(convert((List<Personaje>) personajes));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping(params = "age")
	public ResponseEntity<?> findByEdad(@RequestParam(name = "age") Integer edad){
		Iterable<Personaje> personajes = service.findByAge(edad);
	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(convert((List<Personaje>) personajes));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping(params = "weight")
	public ResponseEntity<?> findByPeso(@RequestParam(name = "weight") Float peso) {
		Iterable<Personaje> personajes = service.findByWeight(peso);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(convert((List<Personaje>) personajes));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping(params = "idMovie")
	public ResponseEntity<?> findByIdPelicula(@RequestParam Long idMovie) {
		Iterable<Personaje> pjs = service.findAll();
		List<Personaje> pjsMovie = new ArrayList<>();
		pjs.forEach(pj -> pj.getPeliculasSeries().forEach(ps -> pjsMovie.add(ps.getId().equals(idMovie)?pj:null)));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(convert(pjsMovie));
	}
	
	private List<PersonajeDetailDto> convert(List<Personaje> personajes) {
		List<PersonajeDetailDto> ps = new ArrayList<>();
		personajes.forEach(e -> ps.add(new PersonajeDetailDto(
				e.getId(), 
				e.getNombre(), 
				Base64.getEncoder().encodeToString(e.getImagen()), 
				e.getEdad(),
				e.getPeso(), 
				e.getHistoria(), 
				e.getPeliculasSeries())));
		return ps;
	}
}
