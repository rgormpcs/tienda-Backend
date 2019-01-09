package com.example.aplicacion.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.aplicacion.models.Categoria;
import com.example.aplicacion.repositories.CategoriaRepository;
@Service
public class CategoriaService {
	private CategoriaRepository categoriaRepository;

	
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}

	public Categoria guardar(Categoria categoria) {
		if(categoriaRepository.findByNombre(categoria.getNombre())!=null) {
			return null;
		}
		return categoriaRepository.save(categoria);
	}
	
	public List<Categoria> listar(){
		return (List<Categoria>) categoriaRepository.findAll();
	}
	
	public Categoria actualizar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria obtenerPorId(String id) {
		return categoriaRepository.findById(id).isPresent()?categoriaRepository.findById(id).get():null;
	}
	
	
}
