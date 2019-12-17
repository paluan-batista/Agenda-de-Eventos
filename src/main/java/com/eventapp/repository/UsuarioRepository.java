package com.eventapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventapp.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    
    Usuario findByLogin(String login);

}
