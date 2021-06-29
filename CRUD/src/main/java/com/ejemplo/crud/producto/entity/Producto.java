package com.ejemplo.crud.producto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor //permite utilizar el constructor sin argunmento
@AllArgsConstructor //genera constructor con todos los argumentos
@Getter //permite utilizar los getter
@Setter //permite utilizar los setter
@Entity //Indicamos que esta clase es una entidad y que debe persistirse. Debe tener un Id
@Table(name = "productos") //asigna nombre a la tabla
public class Producto {

	@Id // Indica que este atributo va a ser el id de la entidad
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Como se genera el valor del id
	private Long id;
	
	@Column(name = "nombre") //Nombre con el que tendra el atributo en la base de datos
	private String nombre;
	
	@NotNull //Este valor no puede ser nulo
	@Column(scale = 2) //Cantidad de decimales solo lo utilizamos cuando Jpa crea las tablas
	private double precio;
	
	@Column(name = "NAME", nullable = false, length = 150)
	/* nullable se utiliza cuando se crea la tabla para que esta columna pueda ser o no nula
	 * length me permite indicar cantidad de caracteres*/
	private String descripcion;
}
