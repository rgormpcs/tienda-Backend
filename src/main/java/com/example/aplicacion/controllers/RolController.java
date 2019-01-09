package com.example.aplicacion.controllers;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.aplicacion.models.Rol;
import com.example.aplicacion.services.RolService;

@RestController
@RequestMapping("/api/roles")
public class RolController {

	private RolService rolService;

	public RolController(RolService rolService) {
		this.rolService = rolService;
	}
	@PostMapping
	///Request Body para enviar un body con post
	public ResponseEntity<Rol> guardar(@RequestBody Rol rol) {
		Rol newRol = rolService.guardar(rol);
		
//		System.out.println("kajshdf"+rol.getNombre());
		if(newRol ==null) {
			throw new DataIntegrityViolationException("ya existe rol control con nombre: "+rol.getNombre());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newRol);
	}
	@GetMapping
	public ResponseEntity<List<Rol>> listar(){
		List<Rol> lista = rolService.listar();
		return new ResponseEntity<List<Rol>>(lista,HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<Rol> actualizar(@RequestBody Rol rol){
		Rol updateRol = rolService.actualizar(rol);
		return new ResponseEntity<Rol>(updateRol, HttpStatus.OK);
	}
	@DeleteMapping("/{ids}")
	public void eliminar(@PathVariable(value="ids")Integer id){
		rolService.eliminar(id);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Rol> obtenerPorId(@PathVariable(value="id") Integer id){
		Rol rol=rolService.obtenerPorId(id);
		if(rol==null) {
			return new ResponseEntity<Rol>(HttpStatus.NOT_FOUND);//para mostrar que no se encontro
		}
		return ResponseEntity.ok(rol);
	}
	
	
}
