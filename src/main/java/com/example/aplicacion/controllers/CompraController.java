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
import com.example.aplicacion.models.Compra;
import com.example.aplicacion.services.CompraService;

@RestController
@RequestMapping("/api/compra")
public class CompraController {

	private CompraService compraService;

	public CompraController(CompraService compraService) {
		super();
		this.compraService = compraService;
	}
	
	
	// es post Mapping para obtener un dato desde la vista y guardarlo
		@PostMapping
		public ResponseEntity<Compra> guardar(@RequestBody Compra compra) {
			Compra newCategoria = compraService.guardar(compra);
			if (newCategoria == null) {
				throw new DataIntegrityViolationException("ya existe entidad");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(newCategoria);
		}

		@GetMapping
		public ResponseEntity<List<Compra>> listar() {
			List<Compra> lista = compraService.listar();
			return new ResponseEntity<List<Compra>>(lista, HttpStatus.OK);
		}
		
//
//		@GetMapping("/{idC}")
//		public ResponseEntity<Compra> obtenerPorId(@PathVariable(value = "idC") String id) {
//			Compra var = compraService.obtenerPorId(idC);
//			if (var == null) {
//				return new ResponseEntity<Compra>(HttpStatus.NOT_FOUND);
//
//			}
//			return ResponseEntity.ok(var);
//		}

		@PutMapping
		public ResponseEntity<Compra> actualizar(@RequestBody Compra compra) {
			Compra updateCompra = compraService.actualizar(compra);
			return new ResponseEntity<Compra>(updateCompra, HttpStatus.OK);

		}
//		

}
