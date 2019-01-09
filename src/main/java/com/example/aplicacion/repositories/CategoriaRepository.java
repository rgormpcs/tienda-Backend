package com.example.aplicacion.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.aplicacion.models.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, String> {
	
	Categoria findByNombre(String nombre);

}
