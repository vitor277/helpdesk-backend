package com.vitor.helpdesck.services;

import com.vitor.helpdesck.domain.Pessoa;
import com.vitor.helpdesck.domain.Tecnico;
import com.vitor.helpdesck.domain.tos.TecnicoDTO;
import com.vitor.helpdesck.repositories.PessoaRepository;
import com.vitor.helpdesck.repositories.TecnicoRepository;
import com.vitor.helpdesck.services.exceptions.DataIntegriyViolationException;
import com.vitor.helpdesck.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findALL() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return repository.save(newObj);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
    Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
        throw new DataIntegriyViolationException("CPF já cadastrado no sistema!");
    }
    obj = pessoaRepository.findByEmail((objDTO.getEmail()));
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegriyViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
