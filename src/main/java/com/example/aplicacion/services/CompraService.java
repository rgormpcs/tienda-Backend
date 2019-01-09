package com.example.aplicacion.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.aplicacion.models.Articulo;
import com.example.aplicacion.models.Compra;
import com.example.aplicacion.repositories.ArticuloRepository;
import com.example.aplicacion.repositories.CompraRepository;

@Service
public class CompraService {
	private CompraRepository compraRepository;
	private ArticuloRepository articuloRepository;
	public CompraService(CompraRepository compraRepository, ArticuloRepository articuloRepository) {
		this.articuloRepository =articuloRepository;
		this.compraRepository = compraRepository;
	}
	/*podria no funcionar por buscar por id*/
	public Compra guardar(Compra compra) {
		compra.setFecha(new Date());
		compra.setNro_documento("000-00"+compraRepository.count()+1);
		compra.getDetalleCompraList().forEach(detalle ->{
			Optional<Articulo> articulo = articuloRepository.findById(detalle.getArticulo().getId());
			if(articulo.isPresent()) {
				articulo.get().setCantidad(articulo.get().getCantidad()-detalle.getCantidad());
				articuloRepository.save(articulo.get());
			detalle.setCompra(compra);
			}
		});
		
		return compraRepository.save(compra);
	}
	
	public List<Compra> listar(){
		return (List<Compra>) compraRepository.findAll();
	}
	
	public Compra actualizar(Compra compra) {
		return compraRepository.save(compra);
	}
	public void eliminar(Integer id) {
		compraRepository.deleteById(id);
	}
	
	public Compra obtenerPorId(Integer id) {
		return compraRepository.findById(id).isPresent()?compraRepository.findById(id).get():null;
	}
	
	
}
