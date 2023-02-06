package com.vitor.helpdesck.services;

import com.vitor.helpdesck.domain.Chamado;
import com.vitor.helpdesck.domain.Cliente;
import com.vitor.helpdesck.domain.Tecnico;
import com.vitor.helpdesck.domain.enums.Perfil;
import com.vitor.helpdesck.domain.enums.Prioridade;
import com.vitor.helpdesck.domain.enums.Status;
import com.vitor.helpdesck.repositories.ChamadoRepository;
import com.vitor.helpdesck.repositories.ClienteRepository;
import com.vitor.helpdesck.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "77202818884", "valdir@email.com", encoder.encode("123"));
        tec1.addPerfil(Perfil.TECNICO);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "15451314810", "Linus@email.com", encoder.encode("000"));
        tec1.addPerfil(Perfil.CLIENTE);

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
