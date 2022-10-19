package com.vitor.helpdesck.repositories;

import com.vitor.helpdesck.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
