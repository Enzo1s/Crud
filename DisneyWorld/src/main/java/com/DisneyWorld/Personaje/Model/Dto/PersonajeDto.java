package com.DisneyWorld.Personaje.Model.Dto;

public class PersonajeDto {

	private String imagen;
	
	private String nombre;

	public PersonajeDto(String imagen, String nombre) {
		this.imagen = imagen;
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
