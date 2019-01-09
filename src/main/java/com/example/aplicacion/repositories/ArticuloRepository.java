package com.example.aplicacion.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.aplicacion.models.Articulo;

public interface ArticuloRepository extends CrudRepository<Articulo, Integer> {

	
	Articulo findByNombre(String nombre);
	
	
}
