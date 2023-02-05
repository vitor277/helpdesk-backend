package com.vitor.helpdesck.services;

import com.vitor.helpdesck.domain.Chamado;
import com.vitor.helpdesck.repositories.ChamadoRepository;
import com.vitor.helpdesck.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
    }
}
