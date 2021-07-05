package com.ejemplo.crud.producto.service;

import org.springframework.stereotype.Service;

import com.Commonsmicroservicios.services.EntityServiceImpl;
import com.ejemplo.crud.producto.entity.Producto;
import com.ejemplo.crud.producto.repository.ProductoRepository;

@Service
public class ProductoServiceImpl extends EntityServiceImpl<Producto, ProductoRepository> implements ProductoService {


}
