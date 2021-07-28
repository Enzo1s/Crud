package com.DisneyWorld.PeliculaSerie.Model.Dto;

import java.util.Date;
import java.util.List;

import com.DisneyWorld.Genero.Model.Entity.Genero;
import com.DisneyWorld.Personaje.Model.Entity.Personaje;

public class PeliculaSerieDetailDto {

	public Long id;
	
	public String titulo;

	public String imagen;

	public Date fechaCreacion;
	
	public int calificacion;

	public List<Personaje> personajes;
	
	public List<Genero> genero;

	public PeliculaSerieDetailDto(Long id, String titulo, String imagen, Date fechaCreacion, int calificacion,
			List<Personaje> personajes, List<Genero> genero) {
		this.id = id;
		this.titulo = titulo;
		this.imagen = imagen;
		this.fechaCreacion = fechaCreacion;
		this.calificacion = calificacion;
		this.personajes = personajes;
		this.genero = genero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}

	public List<Genero> getGenero() {
		return genero;
	}

	public void setGenero(List<Genero> genero) {
		this.genero = genero;
	}
}
