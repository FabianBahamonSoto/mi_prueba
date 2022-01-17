package com.bolsadeideas.spring.boot.backend.apirest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.spring.boot.backend.apirest.entity.Cliente;
import com.bolsadeideas.spring.boot.backend.apirest.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ClienteController 
{
	@Autowired
	ClienteService clienteService;
	
	/**
	 * @author Fabian Bahamon
	 * @return Retorna todos los clientes registrados
	 * @throws Exception
	 */
	@GetMapping("/")
	public ResponseEntity<List<Cliente>> findAllClients() throws Exception
	{
		List<Cliente> allClients = clienteService.findAll();
		
		return ResponseEntity.ok().body(allClients);
	}
	
	/**
	 * @param nombreCompleto: Compuesto por el nombre y apellido del usuario a buscar.
	 * @return Retorna un objeto que contiene los datos completos del cliente.
	 * @throws Exception
	 */
	@GetMapping("/nombrecompleto")
	public ResponseEntity<Cliente> findClientByName(@Valid @RequestBody Cliente nombreCompleto) throws Exception
	{
		Cliente cliente = clienteService.findByNombreAndApellido(nombreCompleto.getNombre(), nombreCompleto.getApellido());
		
		return ResponseEntity.ok().body(cliente);
	}
	
	/**
	 * 
	 * @param nuevoCliente hace referencia al objeto de entrada con los datos del nuevo cliente.
	 * @return retorna estado 200 en caso dado el almacenaje del cliente fuera exitoso.
	 * @throws Exception
	 */
	@PostMapping("/guardarcliente")
	public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody Cliente nuevoCliente) throws Exception
	{		
		return ResponseEntity.ok(clienteService.save(nuevoCliente));
	}
	
	/**
	 * @param nuevoCliente hace referencia al objeto de entrada con los datos del nuevo cliente.
	 * @return retorna estado 200 en caso dado el almacenaje del cliente fuera exitoso.
	 * @throws Exception
	 */
	@PutMapping("/actualizarcliente")
	public ResponseEntity<Cliente> updateCliente(@Valid @RequestBody Cliente nuevoCliente) throws Exception
	{		
		return ResponseEntity.ok(clienteService.update(nuevoCliente));
	}
	
	
	
	
}
