package com.ejemplo.crud.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.crud.producto.entity.Producto;

/*
 * para indicar que es una interfaz de tipo repository.
 * Esta interfaz la utilizamos para las consultas a la base de datos
 * */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

	//Podemos agregar consultas personalizadas. recomiendo leer un poco de Jpa
}
