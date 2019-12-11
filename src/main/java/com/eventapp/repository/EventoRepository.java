package com.eventapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventapp.models.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, String>{
    
    Evento findByCodigo(long codigo);

}
