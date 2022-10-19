package com.vitor.helpdesck.repositories;

import com.vitor.helpdesck.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
