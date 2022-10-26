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
public class HelpdesckApplication {



	public static void main(String[] args) {
		SpringApplication.run(HelpdesckApplication.class, args);
	}


}
