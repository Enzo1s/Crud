package com.Producto.service;

import org.springframework.stereotype.Service;

import com.Commonsmicroservicios.services.CommonServiceImpl;
import com.Producto.entity.Producto;
import com.Producto.repository.ProductoRepository;

@Service
public class ProductoServiceImpl extends CommonServiceImpl<Producto, ProductoRepository> implements ProductoService {

}
