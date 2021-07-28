package com.DisneyWorld.Personaje.Model.Dto;

import java.util.List;

import com.DisneyWorld.PeliculaSerie.Model.Entity.PeliculaSerie;

public class PersonajeDetailDto {

	private long id;
	
	private String nombre;

	private String imagen;
	
	private int edad;
	
	private float peso;
	
	private String historia;
	
	private List<PeliculaSerie> peliculasSeries;

	public PersonajeDetailDto(long id, String nombre, String imagen, int edad, float peso, String historia,
			List<PeliculaSerie> peliculasSeries) {
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.peliculasSeries = peliculasSeries;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public List<PeliculaSerie> getPeliculasSeries() {
		return peliculasSeries;
	}

	public void setPeliculasSeries(List<PeliculaSerie> peliculasSeries) {
		this.peliculasSeries = peliculasSeries;
	}
}
