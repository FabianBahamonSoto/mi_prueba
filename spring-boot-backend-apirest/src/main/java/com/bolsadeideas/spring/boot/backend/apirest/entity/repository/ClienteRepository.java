package com.bolsadeideas.spring.boot.backend.apirest.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.spring.boot.backend.apirest.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>
{
	Cliente findByNombreAndApellido(String nombre, String apellido);
}
