package com.Cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cliente.entity.Cliente;
import com.Cliente.service.ClienteService;
import com.Commonsmicroservicios.controllers.CommonController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/cliente")
public class ClienteController extends CommonController<Cliente, ClienteService>{

	@GetMapping("/saludo")
	public ResponseEntity<?> saludar(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("{\"error\":\"Hola\"}");
	}
}
