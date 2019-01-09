package com.example.aplicacion.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.aplicacion.models.Rol;

public interface RolRepository extends CrudRepository<Rol, Integer> {
	
	Rol findByNombre (String nombre);

}
