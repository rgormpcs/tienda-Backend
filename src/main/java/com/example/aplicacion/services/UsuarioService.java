package com.example.aplicacion.services;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.aplicacion.repositories.UsuarioRepository;
import com.example.aplicacion.models.Usuario;

@Service
public class UsuarioService {
  private UsuarioRepository usuarioRepository;

public UsuarioService(UsuarioRepository usuarioRepository) {
	super();
	this.usuarioRepository = usuarioRepository;
}
public Usuario guardar(Usuario usuario) {
	if(usuarioRepository.findByEmail(usuario.getEmail())!=null) {
		return null;
	}
	return usuarioRepository.save(usuario);
}

public List<Usuario> listar(){
	return (List<Usuario>) usuarioRepository.findAll();
}

public Usuario actualizar(Usuario usuario) {
	return usuarioRepository.save(usuario);
}
public void eliminar(Integer id) {
	usuarioRepository.deleteById(id);
}

public Usuario obtenerPorId(Integer id) {
	return usuarioRepository.findById(id).isPresent()?usuarioRepository.findById(id).get():null;
}

}
