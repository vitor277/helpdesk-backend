package com.vitor.helpdesck.services;

import com.vitor.helpdesck.domain.Cliente;
import com.vitor.helpdesck.domain.Pessoa;
import com.vitor.helpdesck.domain.Tecnico;
import com.vitor.helpdesck.domain.dtos.ClienteDTO;
import com.vitor.helpdesck.domain.dtos.TecnicoDTO;
import com.vitor.helpdesck.repositories.ClienteRepository;
import com.vitor.helpdesck.repositories.PessoaRepository;
import com.vitor.helpdesck.repositories.TecnicoRepository;
import com.vitor.helpdesck.services.exceptions.DataIntegriyViolationException;
import com.vitor.helpdesck.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findALL() { return repository.findAll(); }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        ValidaPorCpfEEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return repository.save(newObj);
    }




    private void ValidaPorCpfEEmail(ClienteDTO objDTO){
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegriyViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail((objDTO.getEmail()));
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegriyViolationException("E-mail já cadastrado no sistema!");
        }
    }

    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
        objDTO.setId(id);
        Cliente oldObj = findById(id);
        oldObj = new Cliente(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegriyViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }else{
            repository.deleteById(id);
        }
    }
}
