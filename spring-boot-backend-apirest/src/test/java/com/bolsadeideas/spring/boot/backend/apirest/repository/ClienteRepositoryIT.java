package com.bolsadeideas.spring.boot.backend.apirest.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bolsadeideas.spring.boot.backend.apirest.entity.Cliente;
import com.bolsadeideas.spring.boot.backend.apirest.entity.repository.ClienteRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ClienteRepositoryIT 
{
	@Autowired
	ClienteRepository clienteRepository; 
	
	@Test
	@Order(1)
	void debeCrearUnCliente() 
	{
		//Arrange(Arreglar los datos, Setearlos ....)
		String nombre = "Juan Sebastian";
		String apellido = "Bahamon Soto";
		String email = "Sebastian@prueba.com";
		Date createAt = new Date(122,01,11);
					
		//Se hidrata el objeto para que se guarde en la BD.
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setEmail(email);
		cliente.setCreateAt(createAt);
				
		//ACT (Probar el método)
		cliente = clienteRepository.save(cliente);
				
		//Assert(Se afirma el resultado esperado.)
		assertNotNull(cliente, "No fue posible crear el cliente");
	}
	
	@Test
	@Order(2)
	void debeActualizarCliente()
	{
		//Arrange(Arreglar los datos, Setearlos ....)
		String nombreBusq = "Juan Sebastian";
		String apellidoBusq = "Bahamon Soto";
				
		Cliente busqCliente = clienteRepository.findByNombreAndApellido(nombreBusq, apellidoBusq);
		
		String nombre = "Juan";
		String apellido = "Bahamon";
		String email = "sebastianbahamon@prueba.com";
		Date createAt = new Date(122,01,12);
		
		if(busqCliente != null)
		{
			busqCliente.setNombre(nombre);
			busqCliente.setApellido(apellido);
			busqCliente.setEmail(email);
			busqCliente.setCreateAt(createAt);
		}
		
		//ACT
		busqCliente = clienteRepository.save(busqCliente);
		
		//ASSERT
		assertNotNull(busqCliente, "EL cliente no registra en la base de datos");
	}
	
	@Order(3)
	@Test
	void debeConsultarClientePorNombreCompleto()
	{
		//ARRANGE
		String nombre = "Juan";
		String apellido = "Bahamon";
		
		//ACT
		Cliente busqCliente = clienteRepository.findByNombreAndApellido(nombre, apellido);
		
		//ASSERT
		assertNotNull(busqCliente, "EL usuario consultado no existe.");
	}
	
	@Order(4)
	@Test
	void debeBorrarCliente()
	{
		//ARRANGE
		String nombre = "Juan";
		String apellido = "Bahamon";
		
		Cliente clienteBusq = clienteRepository.findByNombreAndApellido(nombre, apellido);
		
		//ACT
		boolean myFlag = (clienteBusq != null) ? true : false;
		
		if(myFlag == true)
		{
			clienteRepository.delete(clienteBusq);
			clienteBusq = clienteRepository.findByNombreAndApellido(nombre, apellido);
			myFlag = (clienteBusq == null) ? true : false;
		}
		else
		{
			assertFalse(myFlag, "El usuario no existe");
		}
		
		//ASSERT
		assertTrue(myFlag, "El usuario se elimino satisfactoriamente");
	}

}
