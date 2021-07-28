package com.DisneyWorld.Personaje.Model.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.DisneyWorld.PeliculaSerie.Model.Entity.PeliculaSerie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "personaje")
public class Personaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	
	@Lob
	@JsonIgnore
	private byte[] imagen;
	
	private int edad;
	
	private float peso;
	
	@Column(length = 1500)
	private String historia;
	
	@JsonIgnoreProperties({"personajes"})
	@ManyToMany(mappedBy="personajes",fetch = FetchType.LAZY)
	private List<PeliculaSerie> peliculasSeries;

	public Integer getImagenHashCode() {
		return (this.imagen != null) ? this.imagen.hashCode():null;
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

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public List<PeliculaSerie> getPeliculasSeries() {
		return peliculasSeries;
	}

	public void setPeliculasSeries(List<PeliculaSerie> peliculasSeries) {
		this.peliculasSeries = peliculasSeries;
	}
	
}
