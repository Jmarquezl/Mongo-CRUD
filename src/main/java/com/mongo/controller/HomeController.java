package com.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.dao.MongoDAO;
import com.mongo.document.Cliente;

@RestController
public class HomeController {
	@Autowired
	private MongoDAO dao;
	
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Cliente> home() {
		return dao.getClientes();
	}
	
	@GetMapping(value = "/obtenerCliente")
	public Cliente getCliente(@RequestParam String id) {
		return dao.getCliente(id);
	}
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean save(@RequestBody Cliente cliente) {
		return dao.save(cliente);
	}
	
	@PutMapping(value = "/actualizar", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean update(@RequestBody Cliente cliente) {
		return dao.update(cliente);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return dao.delete(id);
	}
	
}
