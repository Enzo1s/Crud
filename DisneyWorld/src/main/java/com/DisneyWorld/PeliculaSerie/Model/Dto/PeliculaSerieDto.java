package com.DisneyWorld.PeliculaSerie.Model.Dto;

import java.util.Date;

public class PeliculaSerieDto {

	private String imagen;
	
	private String titulo;
	
	private Date fechaCreacion;

	public PeliculaSerieDto(String imagen, String titulo, Date fechaCreacion) {
		this.imagen = imagen;
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
