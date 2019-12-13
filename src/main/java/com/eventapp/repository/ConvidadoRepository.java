package com.eventapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventapp.models.Convidado;
import com.eventapp.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
    
    Iterable<Convidado> findByEvento(Evento evento);
    Convidado findByRg (String rg);

}


