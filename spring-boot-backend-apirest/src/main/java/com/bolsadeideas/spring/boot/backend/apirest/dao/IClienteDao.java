package com.bolsadeideas.spring.boot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.spring.boot.backend.apirest.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>
{

}
