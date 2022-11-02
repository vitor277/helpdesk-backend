package com.vitor.helpdesck.services;

import com.vitor.helpdesck.domain.Tecnico;
import com.vitor.helpdesck.repositories.TecnicoRepository;
import com.vitor.helpdesck.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findALL() {
        return repository.findAll();
    }
}
