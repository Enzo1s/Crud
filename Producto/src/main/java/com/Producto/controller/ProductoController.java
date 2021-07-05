package com.Producto.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Commonsmicroservicios.controllers.CommonController;
import com.Producto.entity.Producto;
import com.Producto.service.ProductoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/producto")
public class ProductoController extends CommonController<Producto, ProductoService>{

}
