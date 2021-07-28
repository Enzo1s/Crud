package com.DisneyWorld.Genero.Model.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.transaction.Transactional;

import com.DisneyWorld.PeliculaSerie.Model.Entity.PeliculaSerie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Transactional
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String nombre;
	
	public String imagen;
	
	@JsonIgnoreProperties({"genero", "peliculasSeries", "personajes"})
	//@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToMany(mappedBy = "genero")
	public List<PeliculaSerie> peliculasSeries;
	
	public Genero() {}

	public Genero(String nombre, String imagen, List<PeliculaSerie> peliculasSeries) {
		this.nombre = nombre;
		this.imagen = imagen;
		this.peliculasSeries = peliculasSeries;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<PeliculaSerie> getPeliculasSeries() {
		return peliculasSeries;
	}

	public void setPeliculasSeries(List<PeliculaSerie> peliculasSeries) {
		this.peliculasSeries = peliculasSeries;
	}
	
}
