package com.example.aplicacion.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.aplicacion.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

	Usuario findByEmail(String email);
	Usuario findByEmailAndNombres(String email, String nombres);
	
	}
