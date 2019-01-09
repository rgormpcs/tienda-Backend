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
import com.example.aplicacion.models.Usuario;
import com.example.aplicacion.services.RolService;
import com.example.aplicacion.services.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	///Request Body para enviar un body con post
	public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
		Usuario newUsuario = usuarioService.guardar(usuario);
		
//		System.out.println("kajshdf"+rol.getNombre());
		if(newUsuario ==null) {
			throw new DataIntegrityViolationException("ya existe rol control con nombre: "+usuario.getNombres());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
	}
	@GetMapping
	public ResponseEntity<List<Usuario>> listar(){
		List<Usuario> lista =usuarioService.listar();
		return new ResponseEntity<List<Usuario>>(lista,HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<Usuario> actualizar(@RequestBody Usuario usuario){
		Usuario updateRol = usuarioService.actualizar(usuario);
		return new ResponseEntity<Usuario>(updateRol, HttpStatus.OK);
	}
//	@DeleteMapping("/{ids}")
//	public void eliminar(@PathVariable(value="ids")Integer id){
//		rolService.eliminar(id);
//	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerPorId(@PathVariable(value="id") Integer id){
		Usuario usuario=usuarioService.obtenerPorId(id);
		if(usuario==null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);//para mostrar que no se encontro
		}
		return ResponseEntity.ok(usuario);
	}

}
