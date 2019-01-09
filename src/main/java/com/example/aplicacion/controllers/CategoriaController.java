package com.example.aplicacion.controllers;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aplicacion.models.Categoria;
import com.example.aplicacion.services.CategoriaService;



@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
	private CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	// es post Mapping para obtener un dato desde la vista y guardarlo
	@PostMapping
	public ResponseEntity<Categoria> guardar(@RequestBody Categoria entidad) {
		Categoria newCategoria = categoriaService.guardar(entidad);
		if (newCategoria == null) {
			throw new DataIntegrityViolationException("ya existe entidad");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newCategoria);
	}

	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		List<Categoria> lista = categoriaService.listar();
		return new ResponseEntity<List<Categoria>>(lista, HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obtenerPorId(@PathVariable(value = "id") String id) {
		Categoria var = categoriaService.obtenerPorId(id);
		if (var == null) {
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);

		}
		return ResponseEntity.ok(var);
	}

	@PutMapping
	public ResponseEntity<Categoria> actualizar(@RequestBody Categoria categoria) {
		Categoria updateCategoria = categoriaService.actualizar(categoria);
		return new ResponseEntity<Categoria>(updateCategoria, HttpStatus.OK);

	}
	
}
