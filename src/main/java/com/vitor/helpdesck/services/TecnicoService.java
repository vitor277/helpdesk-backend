package com.vitor.helpdesck.services;

import com.vitor.helpdesck.domain.Pessoa;
import com.vitor.helpdesck.domain.Tecnico;
import com.vitor.helpdesck.domain.dtos.TecnicoDTO;
import com.vitor.helpdesck.repositories.PessoaRepository;
import com.vitor.helpdesck.repositories.TecnicoRepository;
import com.vitor.helpdesck.services.exceptions.DataIntegriyViolationException;
import com.vitor.helpdesck.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findALL() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return repository.save(newObj);
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        oldObj = new Tecnico(objDTO);
        return repository.save(oldObj);

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


    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegriyViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }else{
            repository.deleteById(id);
        }
    }
}
