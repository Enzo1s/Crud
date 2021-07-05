package com.ejemplo.crud.producto.service;

import org.springframework.stereotype.Service;

import com.Commonsmicroservicios.services.CommonServiceImpl;
import com.ejemplo.crud.producto.entity.Producto;
import com.ejemplo.crud.producto.repository.ProductoRepository;

@Service
public class ProductoServiceImpl extends CommonServiceImpl<Producto, ProductoRepository> implements ProductoService {


}
