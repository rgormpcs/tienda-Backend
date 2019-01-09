package com.example.aplicacion.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.aplicacion.models.Compra;

public interface CompraRepository extends CrudRepository<Compra, Integer> {
	
}
