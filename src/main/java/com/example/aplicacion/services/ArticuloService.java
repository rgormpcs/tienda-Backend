package com.example.aplicacion.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.aplicacion.models.Articulo;
import com.example.aplicacion.repositories.ArticuloRepository;

@Service
public class ArticuloService {
	private ArticuloRepository articuloRepository;

	public ArticuloService(ArticuloRepository articuloRepository) {
		super();
		this.articuloRepository = articuloRepository;
	}
	
	public Articulo guardar(Articulo articulo) {
		articulo.setFecha(new Date());
		if(articuloRepository.findByNombre(articulo.getNombre())!=null) {
			return null;
		}
		return articuloRepository.save(articulo);
	}
	
	public List<Articulo> listar(){
		return (List<Articulo>) articuloRepository.findAll();
	}
	
	public Articulo actualizar(Articulo articulo) {
		return articuloRepository.save(articulo);
	}
	public void eliminar(Integer id) {
		articuloRepository.deleteById(id);
	}
	
	public Articulo obtenerPorId(Integer id) {
		return articuloRepository.findById(id).isPresent()?articuloRepository.findById(id).get():null;
	}
	
}
