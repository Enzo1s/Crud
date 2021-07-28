package com.DisneyWorld.PeliculaSerie.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
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

import com.DisneyWorld.PeliculaSerie.Model.Dto.PeliculaSerieDetailDto;
import com.DisneyWorld.PeliculaSerie.Model.Dto.PeliculaSerieDto;
import com.DisneyWorld.PeliculaSerie.Model.Entity.PeliculaSerie;
import com.DisneyWorld.PeliculaSerie.Service.PeliculaSerieService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/movies")
public class PeliculaSerieController {

	@Autowired
	private PeliculaSerieService service;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<PeliculaSerieDto> ps = new ArrayList<>();
		service.findAll().forEach(peliculaSerie -> 
		ps.add(new PeliculaSerieDto(Base64.getEncoder().encodeToString(peliculaSerie.getImagen()), 
				peliculaSerie.getTitulo(), peliculaSerie.getFechaCreacion())));
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ps);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody PeliculaSerie peliculaSerie) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(peliculaSerie));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/create-img")
	public ResponseEntity<?> createImg(PeliculaSerie peliculaSerie, @RequestParam MultipartFile img)
			throws IOException {
		if (!img.isEmpty()) {
			peliculaSerie.setImagen(img.getBytes());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(peliculaSerie));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody PeliculaSerie peliculaSerie, @PathVariable Long id) {
		Optional<PeliculaSerie> o = service.finById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		PeliculaSerie ps = o.get();
		ps.setTitulo(peliculaSerie.getTitulo());
		ps.setCalificacion(peliculaSerie.getCalificacion());
		ps.setFechaCreacion(peliculaSerie.getFechaCreacion());
		ps.setPersonajes(peliculaSerie.getPersonajes());
		ps.setGenero(peliculaSerie.getGenero());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(ps));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update-img/{id}")
	public ResponseEntity<?> updateImg(PeliculaSerie peliculaSerie, @PathVariable Long id,
			@RequestParam MultipartFile img) throws IOException {
		Optional<PeliculaSerie> o = service.finById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		PeliculaSerie ps = o.get();
		ps.setTitulo(peliculaSerie.getTitulo());
		ps.setCalificacion(peliculaSerie.getCalificacion());
		ps.setFechaCreacion(peliculaSerie.getFechaCreacion());
		ps.setPersonajes(peliculaSerie.getPersonajes());
		ps.setGenero(peliculaSerie.getGenero());
		if (!img.isEmpty()) {
			ps.setImagen(img.getBytes());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(ps));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletebyId(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping(params = "name")
	public ResponseEntity<?> searchTitle(@RequestParam String name) {
		Iterable<PeliculaSerie> peliculasSeries = service.findByTitle(name);
		List<PeliculaSerieDetailDto> ps = convert((List<PeliculaSerie>) peliculasSeries);

		if (ps.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(peliculasSeries);
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ps);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping(params = "genre")
	public ResponseEntity<?> findByGenero(@RequestParam(name = "genre") Long idGenero) {
		Iterable<PeliculaSerie> peliculasSeries = service.findAll();
		List<PeliculaSerie> ps = new ArrayList<>();
		peliculasSeries.forEach(
				peliSerie -> peliSerie.getGenero().forEach(g -> ps.add(g.getId().equals(idGenero) ? peliSerie : null)));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(convert(ps));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping(params = "order")
	public ResponseEntity<?> findAllOrder(@RequestParam String order) {
		Iterable<PeliculaSerie> peliculasSeries = service.findAll();
		List<PeliculaSerieDetailDto> ps = convert((List<PeliculaSerie>) peliculasSeries);
		if (order.equals("ASC"))
			ps.sort(Comparator.comparing(PeliculaSerieDetailDto::getFechaCreacion));

		if (order.equals("DESC"))
			ps.sort(Comparator.comparing(PeliculaSerieDetailDto::getFechaCreacion).reversed());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ps);
	}
	
	private List<PeliculaSerieDetailDto> convert(List<PeliculaSerie> peliculasSeries) {
		List<PeliculaSerieDetailDto> ps = new ArrayList<>();
		peliculasSeries.forEach(e -> ps.add(
				new PeliculaSerieDetailDto(
						e.getId(), 
						e.getTitulo(), 
						Base64.getEncoder().encodeToString(e.getImagen()),
						e.getFechaCreacion(), 
						e.getCalificacion(), 
						e.getPersonajes(), 
						e.getGenero())));
		return ps;
	}
}
