package com.Cliente.service;

import org.springframework.stereotype.Service;

import com.Cliente.entity.Cliente;
import com.Cliente.repository.ClienteRepository;
import com.Commonsmicroservicios.services.CommonServiceImpl;

@Service
public class ClienteServiceImpl extends CommonServiceImpl<Cliente, ClienteRepository> implements ClienteService{

}
