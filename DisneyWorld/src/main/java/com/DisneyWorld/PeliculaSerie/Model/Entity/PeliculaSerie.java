package com.DisneyWorld.PeliculaSerie.Model.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.DisneyWorld.Genero.Model.Entity.Genero;
import com.DisneyWorld.Personaje.Model.Entity.Personaje;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pelicula_serie")
public class PeliculaSerie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String titulo;
	
	@Lob
	@JsonIgnore
	public byte[] imagen;
	
	@Column(name = "fecha_creacion")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date fechaCreacion;
	
	public int calificacion;
	
	@JsonIgnoreProperties({"peliculasSeries"})
	@JoinTable(
			name="peliculaserie_personaje",
			joinColumns = @JoinColumn(name= "peliculaserie_id"),
			inverseJoinColumns = @JoinColumn (name="personaje_id"))
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	public List<Personaje> personajes;
	
	@JsonIgnoreProperties({"peliculasSeries"})
	@JoinTable(
			name="genero_peliculaserie",
			joinColumns = @JoinColumn(name= "genero_id"),
			inverseJoinColumns = @JoinColumn (name="peliculaserie_id"))
	@ManyToMany(cascade = CascadeType.MERGE)
	public List<Genero> genero;

	public Integer getImagenHashCode() {
		return (this.imagen != null) ? this.imagen.hashCode():null;
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

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
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
