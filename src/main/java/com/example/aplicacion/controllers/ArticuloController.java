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

import com.example.aplicacion.models.Articulo;
import com.example.aplicacion.services.ArticuloService;



@RestController
@RequestMapping("/api/Articulo")
public class ArticuloController {
	private ArticuloService articuloService;

	public ArticuloController(ArticuloService ArticuloService) {
		this.articuloService = ArticuloService;
	}

	// es post Mapping para obtener un dato desde la vista y guardarlo
	@PostMapping
	public ResponseEntity<Articulo> guardar(@RequestBody Articulo entidad) {
		Articulo newArticulo = articuloService.guardar(entidad);
		if (newArticulo == null) {
			throw new DataIntegrityViolationException("ya existe entidad");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newArticulo);
	}

	@GetMapping
	public ResponseEntity<List<Articulo>> listar() {
		List<Articulo> lista = articuloService.listar();
		return new ResponseEntity<List<Articulo>>(lista, HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Articulo> obtenerPorId(@PathVariable(value = "id") Integer id) {
		Articulo var = articuloService.obtenerPorId(id);
		if (var == null) {
			return new ResponseEntity<Articulo>(HttpStatus.NOT_FOUND);

		}
		return ResponseEntity.ok(var);
	}
	
	@PutMapping
	public ResponseEntity<Articulo> actualizar(@RequestBody Articulo articulo) {
		Articulo updateArticulo = articuloService.actualizar(articulo);
		return new ResponseEntity<Articulo>(updateArticulo, HttpStatus.OK);

	}

	
	
}
