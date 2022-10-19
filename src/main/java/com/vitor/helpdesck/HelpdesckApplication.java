package com.vitor.helpdesck;

import ch.qos.logback.core.net.server.Client;
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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdesckApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdesckApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "77202818884", "valdir@email.com", "123");
		tec1.addPerfil(Perfil.TECNICO);

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "15451314810", "Linus@email.com", "000");
		tec1.addPerfil(Perfil.CLIENTE);

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));

	}
}
